package common.common.constant;

import common.util.PropertyPlaceholder;

import java.math.BigDecimal;

public final class Constant {

    public static final BigDecimal ADMIN_ROLE_ID=BigDecimal.ONE;

    public static final int PHONE_TIMES_EXPIRYTIME = 3600;

    public static final int PHONE_TIMES = 30;

    public static final int PASSWORD_ERROR_EXPIRED_SECONDS=600;


    public static final int MAX_PASSWORD_ERROR_TIMES = Integer.MAX_VALUE;

    public static final int SUCCESS_CODE = 0;

    public static final String HEADER_TOKEN_KEY = "Authorization";

    public static final String JWT_SECRET = "ndt2017";

    public static final String JWT_CLAIM_KEY = "USER_INFO";

    public static final int TOKEN_EXPIRY_MINUTES = 24*60;

    public static final int REFRESH_TOKEN_EXPIRY_MINUTES =7*24*60;

    public static final int REDIS_MENU_EXPIRY_MINUTES=24*60;

    public static final int VERIFICATION_CODE_EXPIRY_TIME = 10*60;

    public static final String DEFAULT_PASSWORD="123456";

    public static final int STATISCS_EXPIRY_MINUTES = 60;

    public  static final Integer MAX_EXPORT_SIZE = Integer.valueOf(PropertyPlaceholder.getProperty("export.maxsize", "5000"));

    /**
     * 短信接口URL
     */
    public static final String SMS_URL = "https://sdk2.028lk.com/sdk2/BatchSend2.aspx?CorpId=CDJS001441&Pwd=zm0513@";

    public static final String WX_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";

    //中介机构管理员的角色ID
    public static final  BigDecimal AGENCY_ADMIN_ROLE_ID=BigDecimal.valueOf(37);
    //中介机构业务员的角色ID
    public static final  BigDecimal AGENCY_CLERK_ROLE_ID=BigDecimal.valueOf(38);


}
