package cn.hhfarcry.springbootmybatis.common.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-16 09:07
 */
public class LoggerUtils {

    /**
     * 快速创建Logger对象
     * @param
     * @return
     */
    public static <T> Logger build(Class<T> clz){
        return LoggerFactory.getLogger(clz);
    }

}
