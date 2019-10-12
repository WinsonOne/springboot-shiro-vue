package xyz.winson.one.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.winson.one.model.vo.UserPerm;
import xyz.winson.one.util.JwtTokenUtil;

/**
 * @author : 温伟聪
 * @Description: JWT Realm
 * @date Date : 2019年10月10日 17:47
 */
public class JwtRealm extends AuthorizingRealm {
    @Override
    public boolean supports(AuthenticationToken token) {
        //表示此Realm只支持JwtToken类型
        return token instanceof JwtToken;
    }

    /**
     * 校验权限时调用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserPerm userPerm = (UserPerm) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(userPerm.getPerms());
        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证时调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        // 获取token
        String token = jwtToken.getToken();
        UserPerm userPerm = jwtTokenUtil.retrieveUserPerm(token);
        if (userPerm == null) {
            // 解析token出错
            throw new AuthenticationException("解析token出错");
        }
        return new SimpleAuthenticationInfo(userPerm, token, getName());
    }

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
}
