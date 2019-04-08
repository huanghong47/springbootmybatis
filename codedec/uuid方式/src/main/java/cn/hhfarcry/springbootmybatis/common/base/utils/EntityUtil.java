package cn.hhfarcry.springbootmybatis.common.base.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-16 08:57
 */
public class EntityUtil {
    private static Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);

    //返回值类型为Map<String, Object>
    public static Map<String, Object> arraymapTobjectmap(Map<String, String[]> properties) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Iterator<Map.Entry<String, String[]>> iter = properties.entrySet().iterator();
        String name = "";
        String value = "";
        while (iter.hasNext()) {
            Map.Entry<String, String[]> entry = iter.next();
            name = entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) { //用于请求参数中有多个相同名称
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();//用于请求参数中请求参数名唯一
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    //返回值类型为Map<String, String>
    public static Map<String, String> arraymapTstringmap(Map<String, String[]> properties) {
        Map<String, String> returnMap = new HashMap<String, String>();
        String name = "";
        String value = "";
        for (Map.Entry<String, String[]> entry : properties.entrySet()) {
            name = entry.getKey();
            String[] values = entry.getValue();
            if (null == values) {
                value = "";
            } else if (values.length>1) {
                for (int i = 0; i < values.length; i++) { //用于请求参数中有多个相同名称
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = values[0];//用于请求参数中请求参数名唯一
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }


    public static Map<String, Object> entityTmap(Object entity){
        if(entity == null){
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!key.equals("class")) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(entity);
                    if ("pageDotCurrentPage".equals(key)){
                        map.put("page.currentPage", value);
                    }
                    else if ("pageDotRecPerPage".equals(key)){
                        map.put("page.recPerPage", value);
                    }
                    else{
                        map.put(key, value);
                    }
                }
            }
        } catch (RuntimeException e) {
            logger.error("EntityUtil entityTmap --> {}", e);
        } finally {
            return map;
        }
    }

    public static <T> T mapTentity(Map<String, ? extends Object> map, Class<T> entityClass){
        try {
            ConvertUtils.register(dateConverter, Date.class);
            Map<String, Object> newMap = new HashMap<>();
            newMap.putAll(map);
            if (entityClass == null){
                return null;
            }
            if (!ParamUtils.isNotBlank(newMap)){
                return entityClass.newInstance();
            }
            T t;
            t = entityClass.newInstance();
            BeanUtils.populate(t, newMap);
            return t;
        } catch (Exception e) {
            logger.error("EntityUtil mapTentity --> {}", e);
            return null;
        }
    }

    private static Converter dateConverter = new Converter() {
        @Override
        public Object convert(Class type, Object value){
            if(ParamUtils.isBlank(value)){
                return null;
            }
            try {
                return new Date(Long.parseLong((String) value));
            } catch (IllegalArgumentException e1){
                Date date = DateTimeUtils.string2Date((String) value, DateTimeUtils.DATETIME_FORMAT_01);
                if(null == date){
                    date = DateTimeUtils.string2Date((String) value, DateTimeUtils.DATETIME_FORMAT_03);
                }
                if(null == date){
                    date = DateTimeUtils.string2Date((String) value, DateTimeUtils.DATETIME_FORMAT_04);
                }
                return date;
            }
        }
    };


    public static Map<String,Object> getParams(String keyvalues){
        String []params = keyvalues.split(",");
        Map<String,Object> maps = new HashMap<>();
        for (int i=0;i<params.length;i++) {
            maps.put(params[i],params[i+1]);
            i++;
        }
        return maps;
    }

    public static void main(String[] args) {
        String keyvalues = "a,b,c,d,e,f";
        Map<String,Object> maps = getParams(keyvalues);
        for (Map.Entry<String, Object> stringObjectEntry : maps.entrySet()) {
            System.out.println(stringObjectEntry.getKey());
            System.out.println(stringObjectEntry.getValue());
            System.out.println("--------------------");
        }
    }
}
