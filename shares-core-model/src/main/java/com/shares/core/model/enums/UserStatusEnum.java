package com.shares.core.model.enums;

/**
 * @author wangmn
 * @description 用户状态
 * @date 2018/1/17 18:00
 */
public enum UserStatusEnum {
    NORMAL("1", "正常"),
    DISABLED("2", "停用");

    private String code;
    private String desc;

    UserStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getMessageByCode(String code) {
        if (code == null) {
            return null;
        }
        for (UserStatusEnum item : UserStatusEnum.values()) {
            if (item.getCode().equals(code)) {
                return item.getDesc();
            }
        }
        return null;
    }
}
