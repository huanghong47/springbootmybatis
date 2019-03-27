package cn.hhfarcry.springbootmybatis.common.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-23 13:08
 */
public class ValidateUtil {
    /**
     * 验证手机号
     * @param phone
     * @return
     */
    public static boolean validatePhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {
                return false;
            }
            return true;
        }
    }

    /**
     * @Description: 验证密码格式  6-16 位字母、数字
     * @param pwd 密码
     * @return boolean
     */
    public static boolean validatePwd(String pwd) {
        if (ParamUtils.isBlank(pwd)){
            return Boolean.FALSE;
        }
        return Pattern.matches("^[0-9A-Za-z]{6,16}$", pwd);
    }
}
