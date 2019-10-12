package xyz.winson.one.shiro;

import lombok.Builder;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author : 温伟聪
 * @Description: jwt
 * @date Date : 2019年10月10日 17:30
 * 实现shiro的AuthenticationToken接口的类
 */
@Data
@Builder
public class JwtToken implements AuthenticationToken {

    private String token;

    private Long principal;

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
