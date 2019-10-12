package xyz.winson.one.shiro;

import lombok.Data;

import java.util.Date;

/**
 * @author : 温伟聪
 * @Description: JWT 账户模型
 * @date Date : 2019年10月10日 18:24
 */
@Data
public class JwtAccount {

    /**
     * expiration time
     * 过期时间
     */
    private Date exp;

    /**
     * Issued At
     * 签发时间
     */
    private Date iat;

    /**
     * token
     */
    private String token;

    /**
     * refresh token
     */
    private String refreshToken;
}
