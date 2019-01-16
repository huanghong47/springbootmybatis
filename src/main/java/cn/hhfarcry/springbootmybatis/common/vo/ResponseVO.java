package cn.hhfarcry.springbootmybatis.common.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-10 10:21
 */
public class ResponseVO {

    public static final String MESSAGE_OK = "ok";
    public static final String MESSAGE_FAILED = "操作失败";
    public static final String MESSAGE_LAKE_PARAMETER = "缺少参数";
    public static final String MESSAGE_PARAMETER_ERROR = "参数值/类型错误";
    public static final String MESSAGE_SYSTEM_ERROR = "系统错误";
    public static final String MESSAGE_USERNAME_LAKE = "缺少用户名";
    public static final String MESSAGE_PASSWORD_LAKE = "缺少密码";
    public static final String MESSAGE_USER_AUTHENTICATION_FAILED = "用户账户认证失败";
    public static final String MESSAGE_USER_PERMISSION_FAILED = "用户权限认证失败";

    public static final int CODE_OK = 0;
    public static final int CODE_FAILED = -1;
    public static final int CODE_LAKE_PARAMETER = 1001;
    public static final int CODE_PARAMETER_ERROR = 1002;
    public static final int CODE_SYSTEM_ERROR = 9000;
    public static final int CODE_USERNAME_LAKE = 2001;
    public static final int CODE_PASSWORD_LAKE = 2002;
    public static final int CODE_USER_AUTHENTICATION_FAILED = 2100;
    public static final int CODE_USER_PERMISSION_FAILED = 2101;

    @Getter @Setter private Integer code;
    @Getter @Setter private String msg;
    @Getter @Setter private Long count;
    @Getter @Setter private Object data;

    public ResponseVO() {}

    public ResponseVO(Integer code, String msg, Long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public ResponseVO(List<? extends Object> list){
        this.code = CODE_OK;
        this.msg = MESSAGE_OK;
        this.count = CollectionUtils.isEmpty(list)?0L:list.size();
        this.data = list;
    }

    public ResponseVO(String msg){
        switch (msg){
            case MESSAGE_OK:
                this.code = CODE_OK;
                this.msg = MESSAGE_OK;
                break;
            case MESSAGE_FAILED:
                this.code = CODE_FAILED;
                this.msg = MESSAGE_FAILED;
                break;
            case MESSAGE_LAKE_PARAMETER:
                this.code = CODE_LAKE_PARAMETER;
                this.msg = MESSAGE_LAKE_PARAMETER;
                break;
            case MESSAGE_PARAMETER_ERROR:
                this.code = CODE_PARAMETER_ERROR;
                this.msg = MESSAGE_PARAMETER_ERROR;
                break;
            case MESSAGE_SYSTEM_ERROR:
                this.code = CODE_SYSTEM_ERROR;
                this.msg = MESSAGE_SYSTEM_ERROR;
                break;
            case MESSAGE_USERNAME_LAKE:
                this.code = CODE_USERNAME_LAKE;
                this.msg = MESSAGE_USERNAME_LAKE;
                break;
            case MESSAGE_PASSWORD_LAKE:
                this.code = CODE_PASSWORD_LAKE;
                this.msg = MESSAGE_PASSWORD_LAKE;
                break;
            case MESSAGE_USER_AUTHENTICATION_FAILED:
                this.code = CODE_USER_AUTHENTICATION_FAILED;
                this.msg = MESSAGE_USER_AUTHENTICATION_FAILED;
                break;
            case MESSAGE_USER_PERMISSION_FAILED:
                this.code = CODE_USER_PERMISSION_FAILED;
                this.msg = MESSAGE_USER_PERMISSION_FAILED;
                break;
            default:
                this.code = CODE_FAILED;
                this.msg = msg;
        }
    }
}
