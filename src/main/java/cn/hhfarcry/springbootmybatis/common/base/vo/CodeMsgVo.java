package cn.hhfarcry.springbootmybatis.common.base.vo;

/**
 * @program: boccasecurityofproductionplatformbackside
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-04-01 13:32
 */
public enum  CodeMsgVo {
    SUCCESS(1,"操作成功"),
    SERVICE_ABNORMAL(-1,"业务异常"),//包括用户名校验，登录校验，权限校验等异常
    QUERY_PARAMETER_LACK(-2,"query请求参数缺失"),
    BODY_PARAMETER_LACK(-3,"body请求参数缺失"),
    HEADER_PARAMETER_LACK(-4,"header请求参数缺失"),
//    PARAMETER_ERROR(-5,"参数不在指定范围"),
    HTTP_ERROR(-6,"HTTP请求错误"),
    SYSTEM_ERROR(-7,"系统错误");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    CodeMsgVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
