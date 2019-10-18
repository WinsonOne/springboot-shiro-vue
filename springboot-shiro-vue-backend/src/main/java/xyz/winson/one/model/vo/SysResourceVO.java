package xyz.winson.one.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author : 温伟聪
 * @Description: 系统资源视图模型
 * @date Date : 2019年09月30日 11:43
 */
@Data
public class SysResourceVO {
    /**
     * 资源ID
     */
    private Long resourceId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源类型
     * 1：目录，2：菜单，3：按钮
     */
    private Integer resourceType;

    /**
     * 上级资源ID
     */
    private Long parentId;

    /**
     * 所有上级资源ID，通过逗号分隔
     */
    private String parentIds;

    /**
     * 资源图标
     */
    private String icon;

    /**
     * 访问路径
     */
    private String path;

    /**
     * 资源对应的权限
     */
    private String perm;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 资源状态
     * 1：有效，0：禁用
     */
    private Integer state;

    /**
     * 是否已删除
     * 1：是，0否
     */
    private Boolean isDelete;

    /**
     * 创建人
     */
    private String createUserNickname;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateUserNickname;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 上级资源名称
     */
    private String parentName;

    /**
     * 资源排序
     */
    private Integer sort;
}
