package common.util.thirdLogin;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Request工具
 */
public class RequestUtil {

    /**
     * 获取远程主机的ip
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    public static Map<String,Object> transRequestMapFull(Map<String,String[]> requestMap){
        Map<String,Object> resultMap = new HashMap<>();
        for(Object keyObject : requestMap.keySet()){
            String key = (String)keyObject;
            String[] values = requestMap.get(key);
            if( values.length< 1){
                resultMap.put(key,"");
            }else if(values.length == 1) {
                resultMap.put(key,values[0]);
            }else{
                resultMap.put(key,values);
            }
        }
        return resultMap;
    }

    public static Map<String,String> transRequestMap(Map<String,String[]> requestMap){
        Map<String,String> resultMap = new HashMap<>();
        for(Object keyObject : requestMap.keySet()){
            String key = (String)keyObject;
            String[] values = requestMap.get(key);
            if( values.length< 1){
                resultMap.put(key,"");
            }else if(values.length >= 1) {
                resultMap.put(key,values[0]);
            }
        }
        return resultMap;
    }

}
