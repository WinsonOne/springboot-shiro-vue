package xyz.winson.one.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author : 温伟聪
 * @Description: 系统资源数据转换对象模型
 * @date Date : 2019年09月30日 14:04
 */
@Data
public class SysResourceDto {
    /**
     * 资源ID
     */
    private Long resourceId;

    /**
     * 资源名称
     */
    @NotNull(message = "资源名称不能为空")
    private String resourceName;

    /**
     * 资源类型
     * 1：目录，2：菜单，3：按钮
     */
    @NotNull(message = "资源类型不能为空")
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
     * 创建人ID
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人ID
     */
    private Long updateUserId;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 资源排序
     */
    private Integer sort;
}
