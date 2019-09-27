package xyz.winson.one.mapper;

import xyz.winson.one.model.entity.SysUserRole;

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

}