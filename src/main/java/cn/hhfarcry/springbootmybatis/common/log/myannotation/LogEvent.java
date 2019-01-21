package cn.hhfarcry.springbootmybatis.common.log.myannotation;

import cn.hhfarcry.springbootmybatis.common.log.myenum.EventType;
import cn.hhfarcry.springbootmybatis.common.log.myenum.ModuleType;

import java.lang.annotation.*;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-18 10:29
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface LogEvent {
    ModuleType module() default ModuleType.DEFAULT; // 日志所属的模块
    EventType event() default EventType.DEFAULT; // 日志事件类型
    String desc() default  ""; // 描述信息

}
