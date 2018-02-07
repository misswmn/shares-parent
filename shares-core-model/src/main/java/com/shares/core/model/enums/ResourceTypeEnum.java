package com.shares.core.model.enums;

/**
 * @author wangmn
 * @description
 * @date 2018/2/6 14:05
 */
public enum ResourceTypeEnum {
    MENU("MENU","菜单"),
    BUTTON("BUTTON","按钮");

    private String code;
    private String desc;
    ResourceTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
