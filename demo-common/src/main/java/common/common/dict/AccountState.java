package common.common.dict;

/**
 * Created by ozc on 2017/9/6
 */
public enum AccountState {
    //正常
    ACTIVE(1401),
    //临时锁定
    TEMPORARY_LOCKED(1402),
    //锁定
    //LOCKED(1403)
    ;

    private int value;
    AccountState(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
