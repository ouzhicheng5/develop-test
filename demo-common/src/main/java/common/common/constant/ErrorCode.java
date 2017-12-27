package common.common.constant;

public enum ErrorCode {
    NotExists(1000),
    Existed(1001),
    Invalid(1002),
    Expired(1003),
    IpLimited(1004),
    PhoneLimited(1005),
    AccountInvalid(1006),
    PasswordError(1007),
    PasswordError3Times(1008),
    LoginNameIsLocked(1009),
    TokenNotExists(1100),
    TokenExpired(1101),
    RefreshTokenNotExists(1102),
    RefreshTokenExpired(1103),
    ThirdRequestFailed(2000),
    BindPlatformAccount(2001),
    ThirdUserNotExists(2002),
    ContainsSensitiveWords(2100),
    ParamError(9998),
    UnknownError(9999);
    private int errorCode = 0;

    ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
