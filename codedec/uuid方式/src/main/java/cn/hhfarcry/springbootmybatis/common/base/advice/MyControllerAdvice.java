package cn.hhfarcry.springbootmybatis.common.base.advice;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-03-27 17:45
 */
import cn.hhfarcry.springbootmybatis.common.base.exception.MyException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

    /**
     * controller 增强器
     *
     * @author sam
     * @since 2017/7/17
     */
    @ControllerAdvice
    public class MyControllerAdvice {

        /**
         * 全局异常捕捉处理
         * @param ex
         * @return
         */
        @ResponseBody
        @ExceptionHandler(value = Exception.class)
        public Map errorHandler(Exception ex) {
            Map map = new HashMap();
            map.put("code", 100);
            map.put("msg", ex.getMessage());
            return map;
        }

        /**
         * 拦截捕捉自定义异常 MyException.class
         * @param ex
         * @return
         */
        @ResponseBody
        @ExceptionHandler(value = MyException.class)
        public Map myErrorHandler(MyException ex) {
            Map map = new HashMap();
            map.put("code", ex.getCode());
            map.put("msg", ex.getMsg());
            return map;
        }

    }

