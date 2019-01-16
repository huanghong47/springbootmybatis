package cn.hhfarcry.springbootmybatis.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * @program: springbootmybatis
 * @description: 校验参数,参数转换工具
 * @author: huanghong
 * @date: 2019-01-10 13:36
 */
public class ParamUtils {

    private final static Logger logger = LoggerFactory.getLogger(ParamUtils.class);

    /**
     * 验证map结果集是否含有有效内容
     * @return
     */
    public static boolean isBlank(Map<? extends Object, ? extends Object> mapResult){
        if(null == mapResult || mapResult.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 验证set结果集是否含有有效内容
     * @return
     */
    public static boolean isBlank(Set<? extends Object> oSet){
        if(null == oSet || oSet.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 验证是否为空参
     * @param stringObj
     * @return
     */
    public static boolean isBlank(Object stringObj){
        if(null == stringObj || "".equals(stringObj.toString().trim())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 验证结果集是否含有有效内容
     * @param mapResult
     * @return
     */
    public static boolean isNotBlank(Map<? extends Object, ? extends Object> mapResult){
        return !isBlank(mapResult);
    }

    /**
     * 验证结果集是否含有有效内容
     * @param oSet
     * @return
     */
    public static boolean isNotBlank(Set<? extends Object> oSet){
        return !isBlank(oSet);
    }

    /**
     * 验证非空参
     * @param stringObj
     * @return
     */
    public static boolean isNotBlank(Object stringObj){
        return !isBlank(stringObj);
    }

    public static Integer strTint(Object str){
        if(isBlank(str)){
            return 0;
        }
        try {
            return Integer.parseInt(str.toString());
        } catch (Exception e) {
            logger.debug("error at {} --> {}", ParamUtils.class.getName(), e);
            return 0;
        }
    }


}
