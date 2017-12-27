package common.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class JSONUtil {

    public static String toJsonString (Object obj){
       return JSON.toJSONString(obj);
    }

    public static <T> T parse(String text,Class<T> clazz){
        return JSON.parseObject(text,clazz);
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz){
        return JSON.parseArray(text,clazz);
    }

}
