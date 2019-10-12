package xyz.winson.one.exception;

import lombok.Data;
import xyz.winson.one.model.vo.ApiResultCodeEnum;

/**
 * @author : 温伟聪
 * @Description: 全局异常
 * @date Date : 2019年10月04日 10:41
 */
@Data
public class GlobalException extends RuntimeException {

    private int code;

    private String msg;

    public GlobalException(String message) {
        super(message);
        this.code = ApiResultCodeEnum.DEFAULT.getCode();
        this.msg = message;
    }

    public GlobalException(int code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public GlobalException(ApiResultCodeEnum apiResultCodeEnum) {
        super(apiResultCodeEnum.getMsg());
        this.code = apiResultCodeEnum.getCode();
        this.msg = apiResultCodeEnum.getMsg();
    }

    public GlobalException(ApiResultCodeEnum apiResultCodeEnum, String msg) {
        super(apiResultCodeEnum.getMsg());
        this.code = apiResultCodeEnum.getCode();
        this.msg = msg;
    }
}
