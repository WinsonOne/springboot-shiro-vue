package xyz.winson.one.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.winson.one.exception.GlobalException;
import xyz.winson.one.mapper.SysRoleMapper;
import xyz.winson.one.mapper.SysRoleResourceMapper;
import xyz.winson.one.mapper.SysUserRoleMapper;
import xyz.winson.one.model.dto.SysRoleDTO;
import xyz.winson.one.model.entity.SysRole;
import xyz.winson.one.model.entity.SysRoleResource;
import xyz.winson.one.model.vo.ApiResult;
import xyz.winson.one.model.vo.ApiResultCodeEnum;
import xyz.winson.one.model.vo.PageQuery;
import xyz.winson.one.model.vo.SysRoleVO;
import xyz.winson.one.service.SysRoleService;
import xyz.winson.one.shiro.SysUserContext;
import xyz.winson.one.util.ApiResultUtil;

import java.util.*;

/**
 * @author : 温伟聪
 * @Description: 系统角色服务接口实现类
 * @date Date : 2019年09月30日 10:16
 */
@Service
@Log4j2
public class SysRoleServiceImpl implements SysRoleService {
    @Override
    public ApiResult<PageInfo<SysRoleVO>> list(PageQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        List<SysRoleVO> sysRoleVOList = sysRoleMapper.list(pageQuery.getQuery());
        PageInfo<SysRoleVO> pageInfo = new PageInfo<>(sysRoleVOList);
        return ApiResultUtil.success(pageInfo);
    }

    @Override
    @Transactional(rollbackFor = GlobalException.class)
    public ApiResult<Void> add(SysRoleDTO sysRoleDto) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleDto, sysRole);
        sysRole.setIsDelete(false);
        Date createTime = Calendar.getInstance().getTime();
        try {
            sysRole.setCreateUserId(SysUserContext.getCurrentUser().getUserId());
            sysRole.setCreateTime(createTime);
            sysRoleMapper.insert(sysRole);
            // 保存角色资源
            saveRoleResource(sysRole.getRoleId(), sysRoleDto.getResourceIds(), createTime, sysRole.getCreateUserId());
        } catch (Exception e) {
            log.error("新增系统角色出错", e);
            throw new GlobalException(ApiResultCodeEnum.ADD_ERROR, "新增系统角色出错");
        }
        return ApiResultUtil.success(null);
    }

    /**
     * 保存角色资源关系，先删除角色关联的ID，然后保存新的关联
     * @param roleId
     * @param resourceIds
     */
    private void saveRoleResource(Long roleId, List<Long> resourceIds, Date createTime, Long createUserId) {
        sysRoleResourceMapper.deleteByRoleId(roleId);
        if (resourceIds != null && resourceIds.size() > 0) {
            List<SysRoleResource> sysRoleResourceList = new ArrayList<>();
            for (Long resourceId : resourceIds) {
                SysRoleResource sysRoleResource = new SysRoleResource();
                sysRoleResource.setRoleId(roleId);
                sysRoleResource.setResourceId(resourceId);
                sysRoleResource.setCreateTime(createTime);
                sysRoleResource.setCreateUserId(createUserId);
                sysRoleResourceList.add(sysRoleResource);
            }
            sysRoleResourceMapper.insertList(sysRoleResourceList);
        }
    }

    @Override
    @Transactional(rollbackFor = GlobalException.class)
    public ApiResult<Void> update(SysRoleDTO sysRoleDto) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleDto, sysRole);
        Date updateTime = Calendar.getInstance().getTime();
        /**
         * 修改人
         */
        sysRole.setUpdateUserId(SysUserContext.getCurrentUser().getUserId());
        sysRole.setUpdateTime(updateTime);
        try {
            sysRoleMapper.updateByPrimaryKey(sysRole);
            // 保存角色资源
            saveRoleResource(sysRole.getRoleId(), sysRoleDto.getResourceIds(), updateTime, sysRole.getUpdateUserId());
        } catch (Exception e) {
            log.error("修改系统角色出错", e);
            throw new GlobalException(ApiResultCodeEnum.UPDATE_ERROR, "修改系统角色出错");
        }
        return ApiResultUtil.success(null);
    }

    @Override
    public ApiResult<Void> delete(List<Long> ids) {
        /**
         * 修改系统角色为已删除
         */
        Map<String, Object> map = new HashMap<>(3);
        map.put("ids", ids);
        /**
         * 修改人
         */
        map.put("updateUserId", SysUserContext.getCurrentUser().getUserId());
        map.put("updateTime", Calendar.getInstance().getTime());
        try {
            sysRoleMapper.logicalDelete(map);
            // 删除用户角色关联关系
            sysUserRoleMapper.deleteByRoleIds(ids);
            // 删除角色关联的角色资源
            sysRoleResourceMapper.deleteByRoleIds(ids);
        } catch (Exception e) {
            log.error("删除系统角色出错", e);
            throw new GlobalException(ApiResultCodeEnum.DELETE_ERROR, "删除系统角色出错");
        }
        return ApiResultUtil.success(null);
    }

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;
}
