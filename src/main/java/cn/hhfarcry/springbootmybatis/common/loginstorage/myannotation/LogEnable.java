package cn.hhfarcry.springbootmybatis.common.loginstorage.myannotation;

import java.lang.annotation.*;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-18 10:27
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface LogEnable {
    boolean logEnable() default true;
}
