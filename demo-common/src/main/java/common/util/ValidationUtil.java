package common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    public static Boolean isPhoneNumber(String mobile){
        if(StringUtils.isEmpty(mobile)){
            return false;
        }
        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }
    public static boolean isEmptyOrNull(Object object){
        return object==null || "".equals(object);
    }

    public static Boolean isIdentityId(String identityId){
        //进行正则表达式的身份证验证
        // 身份证验证规则
        String regEx = "\\d{15}|\\d{18}";//15和18位数字
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(identityId);
        return matcher.matches();
    }
}
