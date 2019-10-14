package xyz.winson.one.shiro;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.winson.one.constant.Constants;
import xyz.winson.one.model.vo.ApiResult;
import xyz.winson.one.model.vo.ApiResultCodeEnum;
import xyz.winson.one.model.vo.UserPerm;
import xyz.winson.one.util.ApiResultUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : 温伟聪
 * @Description: token 拦截器
 * @date Date : 2019年10月12日 13:56
 */
@Log4j2
public class JwtAuthenticationFilter extends BasicHttpAuthenticationFilter {

    /**
     * 检测Header里token字段
     * 判断是否登录
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        /**
         * 根据是否请求头是否有token参数
         */
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(Constants.AUTH_TOKEN);
        return token != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(Constants.AUTH_TOKEN);
        JwtToken jwtToken = JwtToken.builder().token(token).build();
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        try {
            Subject subject = getSubject(request, response);
            subject.login(jwtToken);
            UserPerm userPerm = (UserPerm) subject.getPrincipal();
            SysUserContext context = new SysUserContext(userPerm);
        } catch (AuthenticationException e) {
            ApiResult apiResult = ApiResultUtil.fail(ApiResultCodeEnum.INVALID_TOKEN);
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
            try {
                httpServletResponse.getWriter().write(JSONObject.toJSONString(apiResult));
            } catch (IOException ex) {
                log.error("响应登录失败出错", ex);
            }
            return false;
        }
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(Constants.AUTH_TOKEN);
        if (token == null) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            ApiResult apiResult = ApiResultUtil.fail("缺少必要参数");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
            try {
                httpServletResponse.getWriter().write(JSONObject.toJSONString(apiResult));
            } catch (IOException ex) {
                log.error("响应登录失败出错", ex);
            }
            return false;
        }
        return executeLogin(request, response);
    }

    /**
     * 对跨域提供支持
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
