package com.shares.web.home.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.shares.core.service.exception.ResponseEnum;
import com.shares.core.service.exception.ResultBean;
import com.shares.core.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.MessageFormat;

@ControllerAdvice
public class CommonControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonControllerAdvice.class);

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResultBean handleHttpException(HttpRequestMethodNotSupportedException exception) {
        LOGGER.error("HttpRequestMethodNotSupportedException >>>>> ", exception);
        return ResultBean.format(new ServiceException(ResponseEnum.ILLEGAL_PARAM, "不支持的HTTP '" + exception.getMethod() + "' 请求"));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public ResultBean handleHttpException(HttpMessageNotReadableException exception) {
        LOGGER.error("HttpMessageNotReadableException >>>>> ", exception);
        return ResultBean.format(new ServiceException(ResponseEnum.ILLEGAL_PARAM, "参数不合法"));
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public ResultBean handleHttpException(HttpMediaTypeNotSupportedException exception) {
        LOGGER.error("HttpMediaTypeNotSupportedException >>>>> ", exception);
        MediaType mediaType = exception.getContentType();
        String contentType = mediaType.getType() + "/" + mediaType.getSubtype();
        return ResultBean.format(new ServiceException(ResponseEnum.ILLEGAL_PARAM, "不支持的Content-Type'" + contentType));
    }

    @ExceptionHandler(value = HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    public ResultBean handleHttpException(HttpMediaTypeNotAcceptableException exception) {
        LOGGER.error("HttpMediaTypeNotAcceptableException >>>>> ", exception);
        return ResultBean.format(new ServiceException(ResponseEnum.ILLEGAL_PARAM, "异常原因'" + exception.getMessage()));
    }

    @ExceptionHandler(value = JsonParseException.class)
    @ResponseBody
    public ResultBean handleIOException(JsonParseException exception) {
        LOGGER.error("JsonParseException:::", exception);
        return ResultBean.format(new ServiceException(ResponseEnum.SYSTEM_ERROR, exception.getMessage()));
    }

    @ExceptionHandler(value = IOException.class)
    @ResponseBody
    public ResultBean handleIOException(IOException exception) {
        LOGGER.error("IOException:::", exception);
        return ResultBean.format(new ServiceException(ResponseEnum.UNKNOWN_EXCEPTION, exception.getMessage()));
    }

    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public ResultBean handleException(ServiceException serviceException) {
        LOGGER.error(MessageFormat.format("ServiceException==>  errorCode:{0} message:{1}", serviceException.getCode(), serviceException.getMessage()), serviceException);
        return ResultBean.format(serviceException);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBean handleException(Exception exception) {
        LOGGER.error("unknown exception >>>>>> ", exception);
        return ResultBean.format(new ServiceException(ResponseEnum.SYSTEM_ERROR, "系统内部错误"));
    }

}
