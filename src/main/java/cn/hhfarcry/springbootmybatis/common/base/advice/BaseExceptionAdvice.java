package cn.hhfarcry.springbootmybatis.common.base.advice;

import cn.hhfarcry.springbootmybatis.common.base.exception.BaseException;
import cn.hhfarcry.springbootmybatis.common.base.vo.CodeMsgVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: boccasecurityofproductionplatformbackside
 * @description: 异常处理
 * @author: huanghong
 * @date: 2019-03-29 10:30
 */
@ControllerAdvice
public class BaseExceptionAdvice{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 业务异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BaseException.class)
    public Map errorHandler1(BaseException ex) {
        logger.error("Exception at {}", ex);
        Map map = new HashMap();
        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());
        map.put("errormsg",ex.geteMsg());
        map.put("count",-1L);
        map.put("data",new ArrayList<>());
        map.put("currenttime",System.currentTimeMillis());
        return map;
    }


    /**
     * post参数异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map errorHandler2(MethodArgumentNotValidException ex) {
        logger.error(ex.getMessage(), ex);
        BindingResult bindingResult = ex.getBindingResult();
        StringBuffer emsg = new StringBuffer();
        for (int i = 0; i < bindingResult.getFieldErrors().size(); i++) {
            FieldError fieldError = bindingResult.getFieldErrors().get(i);
            emsg.append(fieldError.getField());
            emsg.append(":");
            emsg.append(fieldError.getDefaultMessage());
            emsg.append(",");
        }
        emsg.deleteCharAt(emsg.length()-1);
        Map map = new HashMap();
        map.put("code", CodeMsgVo.BODY_PARAMETER_LACK.getCode());
        map.put("msg", CodeMsgVo.BODY_PARAMETER_LACK.getMsg());
        map.put("errormsg",emsg.toString());
        map.put("count",-1L);
        map.put("data",new ArrayList<>());
        map.put("currenttime",System.currentTimeMillis());
        return map;
    }

    /**
     * 请求方式异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Map errorHandler3(HttpRequestMethodNotSupportedException ex) {
        logger.error(ex.getMessage(), ex);
        Map map = new HashMap();
        map.put("code", CodeMsgVo.HTTP_ERROR.getCode());
        map.put("msg", CodeMsgVo.HTTP_ERROR.getMsg());
        map.put("errormsg",ex.getMessage());
        map.put("count",-1L);
        map.put("data",new ArrayList<>());
        map.put("currenttime",System.currentTimeMillis());
        return map;
    }

    /**
     * 请求body参数异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Map errorHandler4(HttpMessageNotReadableException ex) {
        logger.error(ex.getMessage(), ex);
        Map map = new HashMap();
        map.put("code", CodeMsgVo.HTTP_ERROR.getCode());
        map.put("msg", CodeMsgVo.HTTP_ERROR.getMsg());
        map.put("errormsg",ex.getMessage());
        map.put("count",-1L);
        map.put("data",new ArrayList<>());
        map.put("currenttime",System.currentTimeMillis());
        return map;

    }

    /**
     * 其他异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler5(Exception ex) {
        logger.error(ex.getMessage(), ex);
        Map map = new HashMap();
        map.put("code", CodeMsgVo.SYSTEM_ERROR.getCode());
        map.put("msg", CodeMsgVo.SYSTEM_ERROR.getMsg());
        map.put("errormsg",ex.getMessage());
        map.put("count",-1L);
        map.put("data",new ArrayList<>());
        map.put("currenttime",System.currentTimeMillis());
        return map;
    }

}
