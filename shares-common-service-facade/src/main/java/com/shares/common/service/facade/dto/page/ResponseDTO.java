package com.shares.common.service.facade.dto.page;

import com.shares.common.service.facade.enums.ResponseEnum;

import java.io.Serializable;

/**
 * @author wangmn
 * @description
 * @date 2018/1/19 17:51
 */
public class ResponseDTO<T> implements Serializable {
    private static final long serialVersionUID = -6146312880539909925L;
    /**
     * 响应码
     */
    private int code;
    /**
     * 业务失败原因
     */
    private String msg;
    /**
     * 建议操作
     */
    private String tip;

    /**
     * 接口响应对象
     */
    private T result;

    public void setResponse(ResponseEnum enums) {
        this.code = enums.getCode();
        this.msg = enums.getMsg();
        this.tip = enums.getTip();
    }

    public static <T> ResponseDTO<T> successResp() {
        ResponseDTO<T> dto = new ResponseDTO<T>();
        dto.setResponse(ResponseEnum.SUCCESS);
        return dto;
    }

    public ResponseDTO() {
        this.code = ResponseEnum.SUCCESS.getCode();
        this.msg = ResponseEnum.SUCCESS.getMsg();
        this.tip = ResponseEnum.SUCCESS.getTip();
    }

    /**
     * @param responseEnum
     * @dateTime 2017年2月16日 下午5:52:53
     */
    public ResponseDTO(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
        this.tip = responseEnum.getTip();
    }

    /**
     * @param responseEnum
     * @dateTime 2017年2月16日 下午5:52:53
     */
    public ResponseDTO(ResponseEnum responseEnum, String msg) {
        this.code = responseEnum.getCode();
        this.msg = msg;
        this.tip = responseEnum.getTip();
    }

    /**
     * @param code
     * @param msg
     * @param tip
     * @dateTime 2017年3月10日 下午3:51:44
     */
    public ResponseDTO(int code, String msg, String tip) {
        this.code = code;
        this.msg = msg;
        this.tip = tip;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return ResponseEnum.SUCCESS.getCode() == this.code;
    }
}
