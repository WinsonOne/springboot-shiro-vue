package xyz.winson.one.exception;

import lombok.extern.log4j.Log4j2;
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

    @ExceptionHandler(value = GlobalException.class)
    public ApiResult<Void> handleGlobalException(GlobalException exception) {
        log.error(exception);
        int code = exception.getCode();
        String msg = exception.getMsg();
        return ApiResultUtil.buildResult(code, msg);
    }

    @ExceptionHandler(value = Exception.class)
    public ApiResult<Void> handleException(Exception exception) {
        log.error(exception);
        return ApiResultUtil.fail(ApiResultCodeEnum.DEFAULT);
    }
}
