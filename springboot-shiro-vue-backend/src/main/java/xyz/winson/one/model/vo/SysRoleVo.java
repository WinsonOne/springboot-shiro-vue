package xyz.winson.one.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author : 温伟聪
 * @Description: 系统角色视图模型
 * @date Date : 2019年09月30日 10:35
 */
@Data
public class SysRoleVo {
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
}
