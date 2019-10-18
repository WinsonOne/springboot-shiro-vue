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
import xyz.winson.one.model.dto.SysUserDTO;
import xyz.winson.one.model.entity.SysResource;
import xyz.winson.one.model.entity.SysUser;
import xyz.winson.one.model.entity.SysUserRole;
import xyz.winson.one.model.vo.*;
import xyz.winson.one.service.SysUserService;
import xyz.winson.one.shiro.JwtAccount;
import xyz.winson.one.shiro.SysUserContext;
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
    public ApiResult<PageInfo<SysUserVO>> list(PageQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        List<SysUserVO> sysUserVOList = sysUserMapper.list(pageQuery.getQuery());
        PageInfo<SysUserVO> pageInfo = new PageInfo<>(sysUserVOList);
        return ApiResultUtil.success(pageInfo);
    }

    @Override
    @Transactional(rollbackFor = GlobalException.class)
    public ApiResult<Void> add(SysUserDTO sysUserDto) {
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
        sysUser.setCreateUserId(SysUserContext.getCurrentUser().getUserId());
        sysUser.setCreateTime(Calendar.getInstance().getTime());
        sysUser.setIsDelete(false);
        try {
            sysUserMapper.insert(sysUser);
            /**
             * 保存用户角色
             */
            saveUserRoles(sysUser.getId(), sysUserDto.getRoleIds(), SysUserContext.getCurrentUser().getUserId(), sysUser.getCreateTime());
        } catch (Exception e) {
            log.error("新增系统用户出错", e);
            throw new GlobalException(ApiResultCodeEnum.ADD_ERROR, "新增系统用户出错");
        }
        return ApiResultUtil.success(null);
    }

    /**
     * 保存用户角色关系
     * @param userId
     * @param roleIds
     * @param createUserId
     * @param createTime
     */
    private void saveUserRoles(Long userId, List<Long> roleIds, Long createUserId, Date createTime) {
        // 首先删除之前关联的角色
        sysUserRoleMapper.deleteByUserId(userId);
        if (roleIds != null && roleIds.size() > 0) {
            // 新增用户角色关联
            List<SysUserRole> sysUserRoleList = new ArrayList<>();
            for (Long roleId : roleIds) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(roleId);
                sysUserRole.setUserId(userId);
                sysUserRole.setCreateUserId(createUserId);
                sysUserRole.setCreateTime(createTime);
                sysUserRoleList.add(sysUserRole);
            }
            sysUserRoleMapper.insertBatch(sysUserRoleList);
        }
    }

    @Override
    @Transactional(rollbackFor = GlobalException.class)
    public ApiResult<Void> update(SysUserDTO sysUserDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDto, sysUser);
        Long currentUserId = SysUserContext.getCurrentUser().getUserId();
        Date now = Calendar.getInstance().getTime();
        sysUser.setUpdateUserId(currentUserId);
        sysUser.setUpdateTime(now);
        try {
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
            saveUserRoles(sysUser.getId(), sysUserDto.getRoleIds(), currentUserId, now);
        } catch (Exception e) {
            throw new GlobalException(ApiResultCodeEnum.UPDATE_ERROR, "修改系统用户信息出错");
        }
        return ApiResultUtil.success(null);
    }

    @Override
    public ApiResult<JwtAccount> login(SysUser loinUser) {
        if (loinUser == null || StringUtils.isEmpty(loinUser.getUsername()) || StringUtils.isEmpty(loinUser.getPassword())) {
            return ApiResultUtil.fail("缺少必要参数");
        }
        SysUser sysUser = sysUserMapper.selectByUsername(loinUser.getUsername());
        if (sysUser == null) {
            return ApiResultUtil.fail("用户名或密码错误");
        }
        EncryptUtil encryptUtil = EncryptUtil.getInstance();
        String pass = encryptUtil.MD5(loinUser.getPassword(), sysUser.getSalt());
        if (!pass.equals(sysUser.getPassword())) {
            return ApiResultUtil.fail("用户名或密码错误");
        }
        UserPerm userPerm = new UserPerm();
        userPerm.setUserId(sysUser.getId());
        List<SysResourceVO> resourceList = findUserResources(sysUser.getId());
        userPerm.setResourceList(resourceList);
        userPerm.setPerms(retrievePermissions(resourceList));
        JwtAccount account = new JwtAccount();
        account.setIat(Calendar.getInstance().getTime());
        JwtAccount jwtAccount = jwtTokenUtil.issueJwt(userPerm);
        return ApiResultUtil.success(jwtAccount);
    }

    @Override
    public ApiResult<List<SysResourceVO>> getUserResources() {
        UserPerm userPerm = SysUserContext.getCurrentUser();
        return ApiResultUtil.success(userPerm.getResourceList());
    }

    /**
     * 根据用户ID查找用户拥有的资源
     * @param id
     * @return
     */
    private List<SysResourceVO> findUserResources(Long id) {
        Set<Long> resourceIds = new HashSet<>();
        List<SysResourceVO> sysResourceVOList;
        if (Constants.SUPER_ADMIN_USER_ID.equals(id)) {
            // 超级管理员拥有所有的权限
            sysResourceVOList = sysResourceMapper.listAll();
        } else {
            // 不是超级管理员，需要根据用户拥有的角色去查询拥有的资源
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
            sysResourceVOList = sysResourceMapper.findByIds(resourceIds);
        }
        return sysResourceVOList;
    }

    /**
     * 抽取资源中关联的权限
     * @param sysResourceVOList
     * @return
     */
    private Set<String> retrievePermissions(List<SysResourceVO> sysResourceVOList) {
        Set<String> permissions = new HashSet<>();
        String perm;
        String[] perms;
        for (SysResourceVO sysResourceVo : sysResourceVOList) {
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
