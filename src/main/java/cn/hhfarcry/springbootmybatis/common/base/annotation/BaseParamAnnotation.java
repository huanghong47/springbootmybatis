package cn.hhfarcry.springbootmybatis.common.base.annotation;

import java.lang.annotation.*;

/**
 * @program: boccasecurityofproductionplatformbackside
 * @description: 请求参数校验注解(query,header)
 * @author: huanghong
 * @date: 2019-03-29 13:16
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseParamAnnotation {
    String[] queryParams() default {};
    String[] headerParams() default {};
}
