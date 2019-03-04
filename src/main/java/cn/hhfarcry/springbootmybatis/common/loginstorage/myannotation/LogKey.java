package cn.hhfarcry.springbootmybatis.common.loginstorage.myannotation;

import java.lang.annotation.*;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-18 10:37
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
public @interface LogKey {
    String keyName() default ""; // key的名称
    boolean isUserId() default false; // 此字段是否是本次操作的userId，这里略
    boolean isLog() default true; // 是否加入到日志中
    boolean isClass() default false; // 是否类对象
}
