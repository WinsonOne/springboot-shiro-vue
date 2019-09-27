package xyz.winson.one.mapper;

import xyz.winson.one.model.entity.SysUser;

/**
 * @author Winson One
 * @date 2019年09月27日 19:10
 */
public interface SysUserMapper {
    /**
     * 根据用户ID删除用户
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param id 用户ID
     * @return 删除记录数
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增用户
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param record
     * @return 新增记录数
     */
    int insert(SysUser record);

    /**
     * 插入非空字段数据
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param record
     * @return 新增记录数
     */
    int insertSelective(SysUser record);

    /**
     * 根据主键查询用户
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param id
     * @return 用户信息
     */
    SysUser selectByPrimaryKey(Long id);

    /**
     * 更新非空字段的用户信息
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param record
     * @return 成功更新记录数
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * 根据主键更新用户信息
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param record
     * @return 成功更新记录数
     */
    int updateByPrimaryKey(SysUser record);
}