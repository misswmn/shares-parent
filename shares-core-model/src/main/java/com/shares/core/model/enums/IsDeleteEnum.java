package com.shares.core.model.enums;

/**
 * @author wangmn
 * @description
 * @date 2018/1/24 17:52
 */
public enum IsDeleteEnum {
    Y("Y", "已删除"),
    N("N", "未删除");
    private String code;
    private String desc;

    IsDeleteEnum(String code, String desc) {
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
