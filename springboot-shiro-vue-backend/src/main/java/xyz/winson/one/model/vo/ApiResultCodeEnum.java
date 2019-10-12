package xyz.winson.one.model.vo;

/**
 * @author : 温伟聪
 * @Description: API 返回码枚举类
 * @date Date : 2019年09月29日 18:39
 */
public enum  ApiResultCodeEnum {

    SUCCESS(200,"成功"),

    ADD_ERROR(801, "新增数据出错"),

    UPDATE_ERROR(802, "修改数据出错"),

    DELETE_ERROR(803, "删除数据出错"),

    LOGICAL_DELETE_ERROR(804, "逻辑删除数据出错"),

    PARAM_ERROR(901, "参数错误"),

    NOT_UNIQUE_DATA(992, "数据唯一性校验不通过"),

    DEFAULT(999, "接口调用失败，请稍后再试");

    private int code;

    private String msg;

    ApiResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
