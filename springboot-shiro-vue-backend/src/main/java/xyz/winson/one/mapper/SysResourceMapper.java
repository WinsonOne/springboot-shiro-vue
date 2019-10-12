package xyz.winson.one.mapper;

import xyz.winson.one.model.entity.SysResource;
import xyz.winson.one.model.vo.SysResourceVo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Winson One
 * @date 2019年09月27日 19:10
 */
public interface SysResourceMapper {
    /**
     * 根据主键删除
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param resourceId
     * @return
     */
    int deleteByPrimaryKey(Long resourceId);

    /**
     * 新增资源
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param record
     * @return
     */
    int insert(SysResource record);

    /**
     * 新增资源
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param record
     * @return
     */
    int insertSelective(SysResource record);

    /**
     * 根据主键查询资源信息
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param resourceId
     * @return
     */
    SysResource selectByPrimaryKey(Long resourceId);

    /**
     * 根据主键更新资源信息
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysResource record);

    /**
     * 根据主键更新资源信息
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     * @param record
     * @return 资源信息
     */
    int updateByPrimaryKey(SysResource record);

    /**
     * 查询系统资源列表
     * @return
     */
    List<SysResourceVo> listAll();

    /**
     * 逻辑删除系统资源
     * @param map
     */
    void logicalDelete(Map<String, Object> map);

    /**
     * 根据资源ID集合获取资源信息集合
     * @param resourceIds
     * @return
     */
    List<SysResourceVo> findByIds(Collection<Long> resourceIds);

    /**
     * 根据用户ID朝招用户拥有的资源
     * @param id
     * @return
     */
    List<SysResource> findByUserId(Long id);
}