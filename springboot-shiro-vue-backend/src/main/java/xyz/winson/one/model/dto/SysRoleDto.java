package xyz.winson.one.model.dto;

import java.util.Date;
import java.util.List;

/**
 * @author : 温伟聪
 * @Description: 系统用户数据转换对象模型
 * @date Date : 2019年09月30日 10:34
 */
public class SysRoleDto {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色备注信息
     */
    private String remark;

    /**
     * 角色状态
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
     * 角色关联的资源
     */
    private List<Long> resourceIds;
}
