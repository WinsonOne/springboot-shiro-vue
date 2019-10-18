package xyz.winson.one.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author : 温伟聪
 * @Description: 系统用户视图模型，不用返回密码跟加密盐值
 * @date Date : 2019年09月29日 20:00
 */
@Data
public class SysUserVO {

    /**
     * 用户ID，自增主键
     */
    private Long id;

    /**
     * 用户名，登录名称
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 用户状态
     * 1：有效，0：禁用
     */
    private Integer state;

    /**
     * 是否已删除
     * 1：是，0否
     */
    private Boolean isDelete;

    /**
     * 创建人昵称
     */
    private Long createUserNickname;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人昵称
     */
    private Long updateUserNickname;

    /**
     * 修改时间
     */
    private Date updateTime;
}
