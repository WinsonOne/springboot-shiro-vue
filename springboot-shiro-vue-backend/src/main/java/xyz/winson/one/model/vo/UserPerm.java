package xyz.winson.one.model.vo;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author : 温伟聪
 * @Description: 用户权限模型
 * @date Date : 2019年10月11日 20:43
 */
@Data
public class UserPerm {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户拥有的权限
     */
    private Set<String> perms;

    /**
     * 用户拥有的资源
     */
    private List<SysResourceVO> resourceList;
}
