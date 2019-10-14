package xyz.winson.one.mapper;

import xyz.winson.one.model.entity.SysUserRole;

import java.util.List;

/**
 * @author Winson One
 * @date 2019年09月27日 19:10
 */
public interface SysUserRoleMapper {
    /**
     * 根据主键删除用户角色关系
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param userRoleId
     * @return
     */
    int deleteByPrimaryKey(Long userRoleId);

    /**
     * 新增用户角色关系
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param record
     * @return
     */
    int insert(SysUserRole record);

    /**
     * 新增用户角色关系
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param record
     * @return
     */
    int insertSelective(SysUserRole record);

    /**
     * 根据主键查询用户角色关系
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param userRoleId
     * @return
     */
    SysUserRole selectByPrimaryKey(Long userRoleId);

    /**
     * 根据用户ID删除用户关联的角色
     * @param id
     * @return
     */
    int deleteByUserId(Long id);

    /**
     * 批量新增用户关联角色
     * @param sysUserRoleList
     * @return
     */
    int insertBatch(List<SysUserRole> sysUserRoleList);

    /**
     * 根据角色集合删除用户角色关联
     * @param ids
     */
    void deleteByRoleIds(List<Long> ids);
}