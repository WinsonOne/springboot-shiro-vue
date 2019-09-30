package xyz.winson.one.service;

import com.github.pagehelper.PageInfo;
import xyz.winson.one.model.dto.SysRoleDto;
import xyz.winson.one.model.vo.ApiResult;
import xyz.winson.one.model.vo.PageQuery;
import xyz.winson.one.model.vo.SysRoleVo;

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
    ApiResult<PageInfo<SysRoleVo>> list(PageQuery pageQuery);

    /**
     * 新增系统角色
     * @param sysRoleDto
     * @return
     */
    ApiResult<Void> add(SysRoleDto sysRoleDto);

    /**
     * 修改系统角色
     * @param sysRoleDto
     * @return
     */
    ApiResult<Void> update(SysRoleDto sysRoleDto);
}
