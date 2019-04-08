package cn.hhfarcry.springbootmybatis.common.security.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-16 14:42
 */
public class Base64ConvertUtil {

    /**
     * 加密
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(String str) throws UnsupportedEncodingException {
        byte[] encodeBytes = Base64.getEncoder().encode(str.getBytes("utf-8"));
        return new String(encodeBytes);
    }

    /**
     * 解密
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decode(String str) throws UnsupportedEncodingException {
        byte[] decodeBytes = Base64.getDecoder().decode(str.getBytes("utf-8"));
        return new String(decodeBytes);
    }

}
