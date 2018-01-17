package com.shares.core.service.exception;

import com.shares.common.util.StringUtils;

/**
 * 内部系统响应
 *
 * @author ex-wangmengnan
 * @date 2017/9/28
 */
public enum ResponseEnum {
    SUCCESS(0, "成功"),
    WARN(1, "警告"),
    UNKNOWN_EXCEPTION(2, "未知异常"),
    SYSTEM_ERROR(3, "系统异常"),
    CACHE_ERROR(4, "缓存错误"),
    ILLEGAL_TOKEN(5, "非法的token"),
    ILLEGAL_PARAM(6, "参数错误"),
    ILLEGAL_IP(7, "ip非法"),
    SESSION_TIMEOUT(8, "session超时"),
    VISIT_OVER_LIMITED(9, "访问次数超出限制"),
    NO_RESULT(10, "查询无结果"),
    AUTH_ERROR(11, "认证错误"),
    ENCRYPT_ERROR(12, "加码异常"),
    DECRYPT_ERROR(13, "解码异常"),
    CALLBACK_ERROR(14, "回调异常"),
    ILLEGAL_REQUEST(15, "请求非法"),
    INTERFACE_TIMEOUT(16, "接口超时"),

    ACCOUNT_LOCK(17, "账户锁定"),
    USERNAME_PWD_ERROR(18, "用户名密码错误"),;

    private int code;
    private String msg;

    ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getMessage(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (ResponseEnum responseEnum : ResponseEnum.values()) {
            if (code.equals(responseEnum.getCode())) {
                return responseEnum.getMsg();
            }
        }
        return null;
    }

    /**
     * @param code
     * @author wangmn
     * @date 2017/9/29 10:46
     */
    public static ResponseEnum getResponseEnum(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (ResponseEnum responseEnum : ResponseEnum.values()) {
            if (code.equals(responseEnum.getCode() + "")) {
                return responseEnum;
            }
        }
        return null;
    }
}