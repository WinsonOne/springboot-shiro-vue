package xyz.winson.one.exception;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.winson.one.model.vo.ApiResult;
import xyz.winson.one.model.vo.ApiResultCodeEnum;
import xyz.winson.one.util.ApiResultUtil;

/**
 * @author : 温伟聪
 * @Description: 全局异常处理类
 * @date Date : 2019年10月04日 10:45
 */
@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ApiResult<Void> handleGlobalException(GlobalException exception) {
        log.error(exception);
        int code = exception.getCode();
        String msg = exception.getMsg();
        return ApiResultUtil.buildResult(code, msg);
    }

    /**
     * 认证失败，提示token已过期
     * @param exception
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    public ApiResult<Void> handleAuthenticationException(AuthenticationException exception) {
        return ApiResultUtil.fail(ApiResultCodeEnum.TOKEN_EXPIRE);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ApiResult<Void> handleAuthorizationException(AuthorizationException exception) {
        log.error("权限不足", exception);
        return ApiResultUtil.fail("权限不足");
    }

    @ExceptionHandler(Exception.class)
    public ApiResult<Void> handleException(Exception exception) {
        log.error(exception);
        return ApiResultUtil.fail(ApiResultCodeEnum.DEFAULT);
    }
}
