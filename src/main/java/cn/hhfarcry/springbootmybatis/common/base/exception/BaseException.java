package cn.hhfarcry.springbootmybatis.common.base.exception;

/**
 * @program: boccasecurityofproductionplatformbackside
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-03-29 10:25
 */
public class BaseException extends Exception{
    private Integer code;//自定义异常码
    private String msg;//自定义描述
    private String eMsg;//异常抛出提示

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String geteMsg() {
        return eMsg;
    }

    public void seteMsg(String eMsg) {
        this.eMsg = eMsg;
    }

    public BaseException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseException(Integer code, String msg, String eMsg) {
        super(eMsg);
        this.code = code;
        this.msg = msg;
        this.eMsg = eMsg;
    }

    public BaseException(String message, Integer code, String msg, String eMsg) {
        super(message);
        this.code = code;
        this.msg = msg;
        this.eMsg = eMsg;
    }

    public BaseException(String message, Throwable cause, Integer code, String msg, String eMsg) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
        this.eMsg = eMsg;
    }

    public BaseException(Throwable cause, Integer code, String msg, String eMsg) {
        super(cause);
        this.code = code;
        this.msg = msg;
        this.eMsg = eMsg;
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code, String msg, String eMsg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.msg = msg;
        this.eMsg = eMsg;
    }
}
