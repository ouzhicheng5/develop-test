package common.util;

import java.sql.Timestamp;

/**
 * Creater: wangxiang
 * Email: wx_iter@163.com
 * Date: 2017/9/22.
 */
public class CommonUtil {

    public static Timestamp now(){
        return new Timestamp(System.currentTimeMillis());
    }

}
