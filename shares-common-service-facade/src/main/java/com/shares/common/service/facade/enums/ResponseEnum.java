package com.shares.common.service.facade.enums;

/**
 * 内部系统响应
 *
 * @author ex-wangmengnan
 * @date 2017/9/28
 */
public enum ResponseEnum {
    SUCCESS(0, "success", "成功"),
    WARN(1, "warn", "警告"),
    UNKNOWN_EXCEPTION(2, "unknown exception", "未知异常"),
    SYSTEM_ERROR(3, "system error", "系统异常"),
    CACHE_ERROR(4, "cache error", "缓存错误"),
    ILLEGAL_TOKEN(5, "illegal token", "非法的token"),
    ILLEGAL_PARAM(6, "illegal param", "参数错误"),
    ILLEGAL_IP(7, "illegal ip", "ip非法"),
    SESSION_TIMEOUT(8, "session timeout", "session超时"),
    VISIT_OVER_LIMITED(9, "visit over limited", "访问次数超出限制"),
    NO_RESULT(10, "no result", "查询无结果"),
    AUTH_ERROR(11, "auth error", "认证错误"),
    ENCRYPT_ERROR(12, "encrypt error", "加码异常"),
    DECRYPT_ERROR(13, "decrypt error", "解码异常"),
    CALLBACK_ERROR(14, "callback error", "回调异常"),
    ILLEGAL_REQUEST(15, "illegal request", "请求非法"),
    INTERFACE_TIMEOUT(16, "interface timeout", "接口超时"),

    ACCOUNT_LOCKED(17, "account locked", "账户锁定"),
    USERNAME_PWD_ERROR(18, "username or pwd error", "用户名或密码错误"),;

    private int code;
    private String tip;
    private String msg;

    ResponseEnum(int code, String tip, String msg) {
        this.code = code;
        this.tip = tip;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getTip() {
        return tip;
    }

    public static String getMessage(String code) {
        if (code == null) {
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
        if (code == null) {
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