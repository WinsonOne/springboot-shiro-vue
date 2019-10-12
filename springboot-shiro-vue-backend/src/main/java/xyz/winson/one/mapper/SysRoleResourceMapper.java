package xyz.winson.one.mapper;

import xyz.winson.one.model.entity.SysRoleResource;

import java.util.List;

/**
 * @author Winson One
 * @date 2019年09月27日 19:10
 */
public interface SysRoleResourceMapper {
    /**
     * 根据主键删除角色资源关系
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param roleResourceId
     * @return
     */
    int deleteByPrimaryKey(Long roleResourceId);

    /**
     * 新增角色资源关系
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param record
     * @return
     */
    int insert(SysRoleResource record);

    /**
     * 新增角色资源关系
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param record
     * @return
     */
    int insertSelective(SysRoleResource record);

    /**
     * 根据主键查询角色资源关系
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param roleResourceId
     * @return
     */
    SysRoleResource selectByPrimaryKey(Long roleResourceId);

    /**
     * 根据角色ID删除角色关联资源
     * @param roleId 角色ID
     * @return
     */
    int deleteByRoleId(Long roleId);

    /**
     * 批量新增角色资源关联
     * @param sysRoleResourceList
     * @return
     */
    int insertList(List<SysRoleResource> sysRoleResourceList);

    /**
     * 删除角色关联的资源
     * @param ids
     * @return
     */
    int deleteByRoleIds(List<Long> ids);
}