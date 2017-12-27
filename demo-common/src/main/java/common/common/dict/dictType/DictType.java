package common.common.dict.dictType;

/**
 * Created by wx on 2017/8/30.
 */
public enum DictType {
    ACCOUNT_STATE(14),  //账号状态
    ;

    private int value;
    DictType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

}
