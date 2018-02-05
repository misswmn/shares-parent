package com.shares.core.model.enums;

/**
 * @author wangmn
 * @description
 * @date 2018/2/5 11:36
 */
public enum ResourceCodeEnum {
    C0("0", "顶级菜单父编号");
    private String code;
    private String desc;

    ResourceCodeEnum(String code, String desc) {
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
