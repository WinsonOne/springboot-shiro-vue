package xyz.winson.one.rest;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import xyz.winson.one.model.vo.ApiResult;
import xyz.winson.one.model.vo.ApiResultCodeEnum;
import xyz.winson.one.util.ApiResultUtil;

/**
 * @author : 温伟聪
 * @Description: 基础 restful controller 类
 * @date Date : 2019年10月03日 10:41
 */
public class BaseRestController {

    protected final ApiResult error(BindingResult bindingResult) {
        // 直接返回提示
        StringBuilder errorMsgBuilder = new StringBuilder();
        for (ObjectError error : bindingResult.getAllErrors()) {
            errorMsgBuilder.append(error.getDefaultMessage());
            errorMsgBuilder.append(";");
        }
        return ApiResultUtil.buildResult(ApiResultCodeEnum.PARAM_ERROR, errorMsgBuilder.toString());
    }
}
