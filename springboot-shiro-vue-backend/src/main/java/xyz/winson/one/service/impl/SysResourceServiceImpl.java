package xyz.winson.one.service.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.winson.one.constant.Constants;
import xyz.winson.one.mapper.SysResourceMapper;
import xyz.winson.one.mapper.SysRoleResourceMapper;
import xyz.winson.one.model.dto.SysResourceDto;
import xyz.winson.one.model.entity.SysResource;
import xyz.winson.one.model.vo.ApiResult;
import xyz.winson.one.model.vo.ApiResultCodeEnum;
import xyz.winson.one.model.vo.SysResourceVo;
import xyz.winson.one.service.SysResourceService;
import xyz.winson.one.shiro.SysUserContext;
import xyz.winson.one.util.ApiResultUtil;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 温伟聪
 * @Description: 系统资源服务接口实现类
 * @date Date : 2019年09月30日 11:41
 */
@Service
@Log4j2
public class SysResourceServiceImpl implements SysResourceService {

    @Override
    public ApiResult<List<SysResourceVo>> listAll() {
        return ApiResultUtil.success(sysResourceMapper.listAll());
    }

    @Override
    public ApiResult<Void> add(SysResourceDto sysResourceDto) {
        SysResource sysResource = new SysResource();
        BeanUtils.copyProperties(sysResourceDto, sysResource);
        if (Constants.BTN == sysResource.getResourceType()) {
            // 按钮，不需要保存访问路径
            sysResource.setPath(null);
        } else {
            // 不是按钮需要检查访问路径
            if (StringUtils.isBlank(sysResource.getPath())) {
                return ApiResultUtil.buildResult(ApiResultCodeEnum.PARAM_ERROR, "资源类型为目录或者菜单时，访问路径不能为空");
            }
        }
        sysResource.setIsDelete(false);
        /**
         * 充创建人
         */
        sysResource.setCreateUserId(SysUserContext.getCurrentUser().getUserId());
        sysResource.setCreateTime(Calendar.getInstance().getTime());
        try {
            sysResourceMapper.insert(sysResource);
        } catch (Exception e) {
            log.error("新增系统资源出错", e);
            return ApiResultUtil.buildResult(ApiResultCodeEnum.ADD_ERROR, "新增系统资源出错");
        }
        return ApiResultUtil.success(null);
    }

    @Override
    public ApiResult<Void> update(SysResourceDto sysResourceDto) {
        SysResource sysResource = new SysResource();
        BeanUtils.copyProperties(sysResourceDto, sysResource);
        /**
         * 修改人
         */
        sysResource.setUpdateUserId(SysUserContext.getCurrentUser().getUserId());
        sysResource.setUpdateTime(Calendar.getInstance().getTime());
        try {
            sysResourceMapper.updateByPrimaryKey(sysResource);
        } catch (Exception e) {
            log.error("修改系统资源出错", e);
            return ApiResultUtil.buildResult(ApiResultCodeEnum.UPDATE_ERROR, "修改系统资源出错");
        }
        return ApiResultUtil.success(null);
    }

    @Override
    public ApiResult<Void> delete(List<Long> ids) {
        if (ids == null || ids.size() == 0) {
            return ApiResultUtil.buildResult(ApiResultCodeEnum.LOGICAL_DELETE_ERROR, "待删除数据不能为空");
        }
        Map<String, Object> map = new HashMap<>(3);
        map.put("ids", ids);
        map.put("updateTime", Calendar.getInstance().getTime());
        /**
         * 修改人
         */
        map.put("updateUserId", SysUserContext.getCurrentUser().getUserId());
        try {
            sysResourceMapper.logicalDelete(map);
            // 删除角色资源关联
            sysRoleResourceMapper.deleteByResourceIds(ids);
        } catch (Exception e) {
            log.error("删除系统资源出错", e);
            return ApiResultUtil.buildResult(ApiResultCodeEnum.LOGICAL_DELETE_ERROR, "删除系统资源出错");
        }
        return ApiResultUtil.success(null);
    }

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;
}
