package xyz.winson.one.model.vo;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

/**
 * @author : 温伟聪
 * @Description: API 返回结果模型
 * @date Date : 2019年09月29日 18:32
 */
@Data
public class ApiResult<T> {

    /**
     * 接口返回码
     */
    private int code;

    /**
     * 接口返回提示信息
     */
    private String msg;

    /**
     * 接口返回数据
     */
    private T data;

    public ApiResult(ApiResultCodeEnum apiResultCodeEnum) {
        this.code = apiResultCodeEnum.getCode();
        this.msg = apiResultCodeEnum.getMsg();
    }

    /**
     *
     * @param apiResultCodeEnum
     * @param msg 覆盖默认的提示
     */
    public ApiResult(ApiResultCodeEnum apiResultCodeEnum, String msg) {
        this.code = apiResultCodeEnum.getCode();
        if (StringUtils.isNotBlank(msg)) {
            this.msg = msg;
        } else {
            this.msg = apiResultCodeEnum.getMsg();
        }
    }

    public ApiResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
