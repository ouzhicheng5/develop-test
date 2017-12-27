package common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Random;


public class SecurityUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    //SHA加密算法不可逆,类似MD5,比MD5更可靠
    public static String encryptAsSHA(String text,String salt) {
        try{
            text += salt;
            MessageDigest sha = MessageDigest.getInstance("SHA");
            byte[] byteArray = text.getBytes("UTF-8");
            byte[] md5Bytes = sha.digest(byteArray);
            return  parseByteToHexStr(md5Bytes);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return null;
        }
    }
    // 将 BASE64 编码的字符串 text 进行解码
    public static String base64Decode(String text) {
        if (text == null)
        {
            return "";
        }
        try {
            byte[] b = Base64.getDecoder().decode(text);
            return new String(b);
        } catch (Exception e) {
            return "";
        }
    }

/*    public static void main(String[] args) {
        System.out.println(base64Encode("1234"));
    }*/
    // 将 s 进行 BASE64 编码
    public static String base64Encode(String s) {
        if (StringUtil.isEmpty(s)) {
            return "";
        }
        return Base64.getEncoder().encodeToString(s.getBytes());
    }

    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    private static String parseByteToHexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 获取随机salt
     * @return
     */
    public static String getSalt(){
        String salt = "";
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        int length = 6;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        salt = sb.toString();
        return salt;
    }

    public static Boolean isRightPassword(String password,String salt,String dbPassword){
        if(StringUtils.isEmpty(password) || StringUtils.isEmpty(dbPassword)){
            return false;
        }
        return dbPassword.equals(encryptAsSHA(password,salt));
    }

    public static String urlEncode(String url){
        try {
            return URLEncoder.encode(url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static void main (String args[]){

    }
}
