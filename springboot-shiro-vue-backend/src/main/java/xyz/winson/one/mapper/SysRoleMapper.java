package xyz.winson.one.mapper;

import xyz.winson.one.model.entity.SysRole;
import xyz.winson.one.model.vo.SysRoleVo;

import java.util.List;
import java.util.Map;

/**
 * @author Winson One
 * @date 2019年09月27日 19:10
 */
public interface SysRoleMapper {
    /**
     * 根据主键删除用户信息
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param roleId 角色ID
     * @return 成功删除记录数
     */
    int deleteByPrimaryKey(Long roleId);

    /**
     * 新增角色
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param record
     * @return 成功新增记录数
     */
    int insert(SysRole record);

    /**
     * 新增角色，字段非空
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param record
     * @return 成功新增记录数
     */
    int insertSelective(SysRole record);

    /**
     * 根据主键查询角色信息
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param roleId
     * @return 角色信息
     */
    SysRole selectByPrimaryKey(Long roleId);

    /**
     * 更新角色信息
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param record 角色信息
     * @return 成功修改记录数
     */
    int updateByPrimaryKeySelective(SysRole record);

    /**
     * 更新角色信息
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param record 角色信息
     * @return 成功修改记录数
     */
    int updateByPrimaryKey(SysRole record);

    /**
     * 系统角色列表查询方法
     * @param query
     * @return
     */
    List<SysRoleVo> list(Map query);

    /**
     * 逻辑删除系统角色，修改为已删除
     * @param map
     * @return
     */
    int logicalDelete(Map<String, Object> map);
}