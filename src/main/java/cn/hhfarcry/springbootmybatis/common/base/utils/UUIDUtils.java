package cn.hhfarcry.springbootmybatis.common.base.utils;

import java.util.UUID;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-14 15:34
 */
public class UUIDUtils {
    public static String newUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    public static void main(String[] args) {
        System.out.println(newUUID());
    }
}
