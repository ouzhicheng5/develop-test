package common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class StringUtil extends StringUtils {

    public static String mkString(Collection<String> strList,String sep){
        StringBuilder sb=new StringBuilder();
        for(String str:strList){
            if(isEmpty(str)){
                continue;
            }
            sb.append(str).append(sep);
        }
        if(sb.length()>=sep.length()){
            return sb.substring(0,sb.length()-sep.length());
        }
        return "";

    }
}
