package xyz.winson.one.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import xyz.winson.one.group.UpdateGroup;
import xyz.winson.one.valid.ValidatorRegexp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

/**
 * @author : 温伟聪
 * @Description: 系统用户数据转换对象模型
 * @date Date : 2019年09月30日 10:42
 */
@Data
public class SysUserDto {
    /**
     * 用户ID，自增主键
     */
    @NotNull(groups = UpdateGroup.class, message = "修改数据时，用户ID不能为空")
    private Long id;

    /**
     * 用户名，登录名称
     */
    @NotNull(message = "用户名不能为空")
    @Length(max = 64, message = "用户名不能超过64位字符")
    @Pattern(regexp = "^[a-zA-Z_]\\w{4,19}$", message = "用户名必须以字母下划线开头，可由字母数字下划线组成")
    private String username;

    /**
     * 用户昵称
     */
    @Length(max = 64, message = "用户昵称不能超过64位字符")
    private String nickname;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 加密盐值
     */
    private String salt;

    /**
     * 手机号码
     */
    @NotNull(message = "手机号码不能为空")
    @Pattern(regexp = ValidatorRegexp.REGEX_MOBILE, message = "手机号码格式不正确")
    private String mobile;

    /**
     * 电子邮箱
     */
    @NotNull(message = "电子邮箱不能为空")
    @Length(max = 64, message = "电子邮箱长度不能超过64位")
    @Email(message = "电子邮箱格式不正确")
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
     * 用户拥有的角色ID
     */
    private List<Long> roleIds;
}
