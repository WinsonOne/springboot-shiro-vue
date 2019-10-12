package xyz.winson.one.util;

import xyz.winson.one.model.vo.ApiResult;
import xyz.winson.one.model.vo.ApiResultCodeEnum;

/**
 * @author : 温伟聪
 * @Description: API 返回结果工具类
 * @date Date : 2019年09月29日 18:36
 */
public class ApiResultUtil {

    /**
     * 成功
     * @param data 成功返回数据
     * @return
     */
    public static final ApiResult success(Object data) {
        ApiResult apiResult = new ApiResult(ApiResultCodeEnum.SUCCESS);
        apiResult.setData(data);
        return apiResult;
    }

    /**
     * 失败
     * @param apiResultCodeEnum 错误提示
     * @return
     */
    public static final ApiResult fail(ApiResultCodeEnum apiResultCodeEnum) {
        return new ApiResult(apiResultCodeEnum);
    }

    public static final ApiResult fail(String msg) {
        return buildResult(ApiResultCodeEnum.DEFAULT, msg);
    }

    public static final ApiResult buildResult(ApiResultCodeEnum apiResultCodeEnum, String msg, Object data) {
        ApiResult apiResult = new ApiResult(apiResultCodeEnum, msg);
        apiResult.setData(data);
        return apiResult;
    }

    public static final ApiResult buildResult(ApiResultCodeEnum apiResultCodeEnum, String msg) {
        return buildResult(apiResultCodeEnum, msg, null);
    }

    public static final ApiResult buildResult(int code, String msg) {
        return new ApiResult(code, msg);
    }
}
