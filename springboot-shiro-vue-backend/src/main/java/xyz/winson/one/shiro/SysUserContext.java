package xyz.winson.one.shiro;

import xyz.winson.one.model.vo.UserPerm;

/**
 * @author : 温伟聪
 * @Description: 系统登录用户上下文
 * @date Date : 2019年10月14日 10:18
 */
public class SysUserContext implements AutoCloseable {

    private static final ThreadLocal<UserPerm> current = new ThreadLocal<>();

    public SysUserContext(UserPerm userPerm) {
        current.set(userPerm);
    }

    public static final UserPerm getCurrentUser() {
        return current.get();
    }

    @Override
    public void close() throws Exception {
        current.remove();
    }
}
