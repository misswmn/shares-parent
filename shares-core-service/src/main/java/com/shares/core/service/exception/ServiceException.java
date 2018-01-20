package com.shares.core.service.exception;

import com.shares.common.service.facade.enums.ResponseEnum;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -2009543102000237915L;
    private int code;

    public ServiceException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public ServiceException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.code = responseEnum.getCode();
    }

    public ServiceException(ResponseEnum responseEnum, String msg) {
        super(msg);
        this.code = responseEnum.getCode();
    }

    public ServiceException(ResponseEnum responseEnum, Throwable cause) {
        super(responseEnum.getMsg(), cause);
        this.code = responseEnum.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceException that = (ServiceException) o;

        if (code != that.code) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
