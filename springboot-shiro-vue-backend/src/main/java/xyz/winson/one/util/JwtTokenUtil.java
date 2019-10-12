package xyz.winson.one.util;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xyz.winson.one.model.vo.UserPerm;
import xyz.winson.one.shiro.JwtAccount;

import java.util.Calendar;
import java.util.Date;

/**
 * @author : 温伟聪
 * @Description: jwt token 工具类
 * @date Date : 2019年10月10日 17:35
 */
@Component
@Log4j2
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expire}")
    private Long expire;

    /**
     * 签发token
     * @param userPerm
     * @return
     */
    public JwtAccount issueJwt(UserPerm userPerm) {
        JwtAccount account = new JwtAccount();
        Date iat = Calendar.getInstance().getTime();
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTCreator.Builder builder = JWT.create();
        builder.withJWTId(StringUtil.generateUUID());
        builder.withIssuedAt(iat);
        Date expiration = Calendar.getInstance().getTime();
        expiration.setTime(iat.getTime() + expire * 60 * 1000);
        builder.withExpiresAt(expiration);
        builder.withSubject(JSONObject.toJSONString(userPerm));
        account.setIat(iat);
        account.setExp(expiration);
        account.setToken(builder.sign(algorithm));
        /**
         * refresh token 过期时间
         */
        Date refreshTokenExpiration = Calendar.getInstance().getTime();
        refreshTokenExpiration.setTime(expiration.getTime() + expire * 60 * 1000);
        builder.withExpiresAt(refreshTokenExpiration);
        String refreshToken = builder.sign(algorithm);
        account.setRefreshToken(refreshToken);
        return account;
    }

    /**
     * 解析token中的账户信息
     * @param token
     * @return
     */
    public UserPerm retrieveUserPerm(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            //效验TOKEN
            DecodedJWT jwt = jwtVerifier.verify(token);
            String subject = jwt.getSubject();
            UserPerm userPerm = JSONObject.parseObject(subject, UserPerm.class);
            return userPerm;
        } catch (Exception e) {
        }
        return null;
    }

}
