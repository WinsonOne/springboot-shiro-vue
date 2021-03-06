package xyz.winson.one.service;

import com.github.pagehelper.PageInfo;
import xyz.winson.one.model.dto.SysRoleDTO;
import xyz.winson.one.model.vo.ApiResult;
import xyz.winson.one.model.vo.PageQuery;
import xyz.winson.one.model.vo.SysRoleVO;

import java.util.List;

/**
 * @author : 温伟聪
 * @Description: 系统角色服务接口
 * @date Date : 2019年09月30日 10:15
 */
public interface SysRoleService {

    /**
     * 分页查询系统角色信息
     * @param pageQuery
     * @return
     */
    ApiResult<PageInfo<SysRoleVO>> list(PageQuery pageQuery);

    /**
     * 新增系统角色
     * @param sysRoleDto
     * @return
     */
    ApiResult<Void> add(SysRoleDTO sysRoleDto);

    /**
     * 修改系统角色
     * @param sysRoleDto
     * @return
     */
    ApiResult<Void> update(SysRoleDTO sysRoleDto);

    /**
     * 逻辑删除系统角色
     * @param ids
     * @return
     */
    ApiResult<Void> delete(List<Long> ids);
}
