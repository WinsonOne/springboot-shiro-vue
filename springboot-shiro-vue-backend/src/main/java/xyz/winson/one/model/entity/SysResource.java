package xyz.winson.one.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Winson One
 * @date 2019年09月27日 19:10
 * 系统资源实体模型
 */
@Data
public class SysResource {
    /**
     * 资源ID
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_resource.resource_id
     *
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     */
    private Long resourceId;

    /**
     * 资源名称
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_resource.resource_name
     *
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     */
    private String resourceName;

    /**
     * 资源类型
     * 1：目录，2：菜单，3：按钮
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_resource.resource_type
     *
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     */
    private Integer resourceType;

    /**
     * 上级资源ID
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_resource.parent_id
     *
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     */
    private Long parentId;

    /**
     * 所有上级资源ID，通过逗号分隔
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_resource.parent_ids
     *
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     */
    private String parentIds;

    /**
     * 资源图标
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_resource.icon
     *
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     */
    private String icon;

    /**
     * 访问路径
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_resource.path
     *
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     */
    private String path;

    /**
     * 资源对应的权限
     */
    private String perm;

    /**
     * 备注信息
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_resource.remark
     *
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     */
    private String remark;

    /**
     * 资源状态
     * 1：有效，0：禁用
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_resource.state
     *
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     */
    private Integer state;

    /**
     * 是否已删除
     * 1：是，0否
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_resource.is_delete
     *
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     */
    private Boolean isDelete;

    /**
     * 创建人ID
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_resource.create_user_id
     *
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     */
    private Long createUserId;

    /**
     * 创建时间
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_resource.create_time
     *
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     */
    private Date createTime;

    /**
     * 修改人ID
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_resource.update_user_id
     *
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     */
    private Long updateUserId;

    /**
     * 修改时间
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_resource.update_time
     *
     * @mbg.generated Fri Sep 27 13:31:36 CST 2019
     */
    private Date updateTime;
}