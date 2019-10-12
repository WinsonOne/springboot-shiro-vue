package xyz.winson.one.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import xyz.winson.one.constant.Constants;
import xyz.winson.one.exception.GlobalException;
import xyz.winson.one.mapper.SysResourceMapper;
import xyz.winson.one.mapper.SysUserMapper;
import xyz.winson.one.mapper.SysUserRoleMapper;
import xyz.winson.one.model.dto.SysUserDto;
import xyz.winson.one.model.entity.SysResource;
import xyz.winson.one.model.entity.SysUser;
import xyz.winson.one.model.entity.SysUserRole;
import xyz.winson.one.model.vo.*;
import xyz.winson.one.service.SysUserService;
import xyz.winson.one.shiro.JwtAccount;
import xyz.winson.one.util.ApiResultUtil;
import xyz.winson.one.util.EncryptUtil;
import xyz.winson.one.util.JwtTokenUtil;
import xyz.winson.one.util.StringUtil;

import java.util.*;

/**
 * @author : 温伟聪
 * @Description: 系统用户服务接口类实现
 * @date Date : 2019年09月29日 18:31
 */
@Service
@Log4j2
public class SysUserServiceImpl implements SysUserService {
    @Override
    public ApiResult<PageInfo<SysUserVo>> list(PageQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        List<SysUserVo> sysUserVoList = sysUserMapper.list(pageQuery.getQuery());
        PageInfo<SysUserVo> pageInfo = new PageInfo<>(sysUserVoList);
        return ApiResultUtil.success(pageInfo);
    }

    @Override
    @Transactional(rollbackFor = GlobalException.class)
    public ApiResult<Void> add(SysUserDto sysUserDto) {
        SysUser dbSysUser = sysUserMapper.selectByUsername(sysUserDto.getUsername());
        if (dbSysUser != null) {
            return ApiResultUtil.buildResult(ApiResultCodeEnum.NOT_UNIQUE_DATA, "用户名已存在，请修改。");
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDto, sysUser);
        String salt = StringUtil.generateUUID();
        sysUser.setSalt(salt);
        EncryptUtil encryptUtil = EncryptUtil.getInstance();
        sysUser.setPassword(encryptUtil.MD5(sysUser.getPassword(), salt));
        sysUser.setCreateTime(Calendar.getInstance().getTime());
        try {
            sysUserMapper.insert(sysUser);
            /**
             * 保存用户角色
             */
            saveUserRoles(sysUser, sysUserDto.getRoleIds());
        } catch (Exception e) {
            log.error("新增系统用户出错", e);
            throw new GlobalException(ApiResultCodeEnum.ADD_ERROR, "新增系统用户出错");
        }
        return ApiResultUtil.success(null);
    }

    private void saveUserRoles(SysUser sysUser, List<Long> roleIds) {
        // 首先删除之前关联的角色
        sysUserRoleMapper.deleteByUserId(sysUser.getId());
        if (roleIds != null && roleIds.size() > 0) {
            // 新增用户角色关联
            List<SysUserRole> sysUserRoleList = new ArrayList<>();
            for (Long roleId : roleIds) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(roleId);
                sysUserRole.setUserId(sysUser.getId());
                sysUserRole.setCreateUserId(sysUser.getCreateUserId());
                sysUserRole.setCreateTime(sysUser.getCreateTime());
                sysUserRoleList.add(sysUserRole);
            }
            sysUserRoleMapper.insertBatch(sysUserRoleList);
        }
    }

    @Override
    public ApiResult<Void> update(SysUserDto sysUserDto) {
        return null;
    }

    @Override
    public ApiResult<JwtAccount> login(String username, String password) {
        SysUser sysUser = sysUserMapper.selectByUsername(username);
        if (sysUser == null) {
            return ApiResultUtil.fail("用户名或密码错误");
        }
        EncryptUtil encryptUtil = EncryptUtil.getInstance();
        String pass = encryptUtil.MD5(password, sysUser.getSalt());
        if (!pass.equals(sysUser.getPassword())) {
            return ApiResultUtil.fail("用户名或密码错误");
        }
        UserPerm userPerm = new UserPerm();
        userPerm.setUserId(sysUser.getId());
        Set<String> perms = findUserPermissions(sysUser.getId());
        userPerm.setPerms(perms);
        JwtAccount account = new JwtAccount();
        account.setIat(Calendar.getInstance().getTime());
        JwtAccount jwtAccount = jwtTokenUtil.issueJwt(userPerm);
        return ApiResultUtil.success(jwtAccount);
    }

    /**
     * 根据用户ID，获取用户拥有的权限
     * @param id
     * @return
     */
    private Set<String> findUserPermissions(Long id) {
        List<SysResourceVo> sysResourceVoList;
        if (Constants.SUPER_ADMIN_USER_ID.equals(id)) {
            // 超级管理员拥有所有的权限
            sysResourceVoList = sysResourceMapper.listAll();
        } else {
            // 不是超级管理员，需要根据用户拥有的角色去查询拥有的资源
            sysResourceVoList = findUserResources(id);
        }
        return retrievePermissions(sysResourceVoList);
    }

    /**
     * 根据用户ID查找用户拥有的资源
     * @param id
     * @return
     */
    private List<SysResourceVo> findUserResources(Long id) {
        Set<Long> resourceIds = new HashSet<>();
        List<SysResource> sysResources = sysResourceMapper.findByUserId(id);
        for (SysResource resource : sysResources) {
            resourceIds.add(resource.getResourceId());
            if (StringUtils.isEmpty(resource.getParentIds())) {
                String[] resourceIdArray = resource.getParentIds().split(",");
                for (String parentId : resourceIdArray) {
                    resourceIds.add(Long.parseLong(parentId));
                }
            }
        }
        List<SysResourceVo> sysResourceVoList = sysResourceMapper.findByIds(resourceIds);
        return sysResourceVoList;
    }

    /**
     * 抽取资源中关联的权限
     * @param sysResourceVoList
     * @return
     */
    private Set<String> retrievePermissions(List<SysResourceVo> sysResourceVoList) {
        Set<String> permissions = new HashSet<>();
        String perm;
        String[] perms;
        for (SysResourceVo sysResourceVo : sysResourceVoList) {
            perm = sysResourceVo.getPerm();
            if (StringUtils.isEmpty(perm)) {
                continue;
            }
            /**
             * 一个资源可能关联多个权限，通过，分隔
             */
            perms = perm.split(",");
            permissions.addAll(Arrays.asList(perms));
        }
        return permissions;
    }

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
}
