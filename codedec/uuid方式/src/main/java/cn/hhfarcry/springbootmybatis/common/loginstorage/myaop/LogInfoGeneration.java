package cn.hhfarcry.springbootmybatis.common.loginstorage.myaop;

import cn.hhfarcry.springbootmybatis.common.loginstorage.myannotation.LogKey;
import cn.hhfarcry.springbootmybatis.common.loginstorage.mymanager.LogInfoEntity;
import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-18 11:16
 */
@Component
public class LogInfoGeneration {

    public void processingManagerLogMessage(ProceedingJoinPoint jp, LogInfoEntity logInfoEntity, Method method) {
        Object[] args = jp.getArgs();
        if(args.length > 0){
            JSONObject msgJson = new JSONObject();

            // 获取方法上参数的注解
            Annotation[][] methodAnnotations = method.getParameterAnnotations();
            for(int i = 0; i < args.length; i++){
                Object arg = args[i];
                // 如果参数被 LogKey 注解了，则直接返回内容
                if(checkArgAnnotationWithIsLogKey(arg, i, methodAnnotations, msgJson)){
                    continue;
                }

                Field[] fs = arg.getClass().getDeclaredFields();
                for (Field f : fs) {
                    Annotation[] ans = f.getAnnotations();
                    for (Annotation an : ans) {
                        if ((an instanceof LogKey) && (((LogKey) an).isLog())) {
                            String fieldName = f.getName();
                            // 如果注解的有定义keyName，则覆盖对象成员变量名称
                            String fieldNameLogkey = ((LogKey)an).keyName();
                            if(!ParamUtils.isBlank(fieldNameLogkey)){
                                fieldName = fieldNameLogkey;
                            }
                            try {
                                f.setAccessible(true);
                                Object fieldValue = f.get(arg);
                                msgJson.put(fieldName, fieldValue);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } finally {
                                f.setAccessible(false);
                            }
                        }
                    }
                }
                logInfoEntity.setOpContent(msgJson.toJSONString());
            }

        }
    }

    /**
     * 如果方法参数被Logkey注解，则将获取整个类的信息
     * @param index
     * @param methodAnnotations
     * @param msgJson
     * @return
     */
    private boolean checkArgAnnotationWithIsLogKey(Object arg, int index, Annotation[][] methodAnnotations, JSONObject msgJson) {
        for(Annotation annotation : methodAnnotations[index]){
            if(annotation instanceof LogKey){
                LogKey logKey = ((LogKey)annotation);
                if(logKey.isLog()){
                    String keyName = logKey.keyName();
                    msgJson.put(keyName, arg.toString());
                }
                break;
            }
        }
        return false;
    }
}
