package cn.hhfarcry.springbootmybatis.common.base.utils;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @program: emsog
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

    /**
     * 验证byte数组
     * @param bytes
     * @return
     */
    public static boolean isBlank(byte[] bytes) {
        // 根据byte数组长度为0判断
        return bytes.length == 0 || bytes == null;
    }


    public static boolean isNotBlank(byte[] bytes) {
        return !isBlank(bytes);
    }


    /**
     * String转Integer
     * @param str
     * @return
     */
    public static Integer strTIntger(Object str){
        if(isBlank(str)){
            return null;
        }
        try {
            return Integer.parseInt(str.toString());
        } catch (Exception e) {
            logger.debug("error at {} --> {}", ParamUtils.class.getName(), e);
            return null;
        }
    }


    /**
     * String转List<String>
     * @param param
     * @param reg
     * @return
     */
    public static List<String> strTListstr(String param, String reg){
        if(isBlank(param)){
            return null;
        }
        return new ArrayList<>(Arrays.asList(param.split(reg)));
    }

    /**
     * String转List<Integer>
     * @param param
     * @param reg
     * @return
     */
    public static List<Integer> strTListintger(String param,String reg){
        List<String> strlist = strTListstr(param,reg);
        List<Integer> result = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(strlist)){
            for (String s : strlist) {
                Integer i = strTIntger(s);
                result.add(i);
            }
        }
        return result;
    }

    public static List<Integer> strlisttointlist(List<String>strlist){
        List<Integer> result = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(strlist)){
            for (String s : strlist) {
                Integer i = strTIntger(s);
                result.add(i);
            }
        }
        return result;
    }
}
