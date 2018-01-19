package com.shares.core.service.base;

import com.shares.common.util.BeanUtil;
import com.shares.core.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BeanServiceUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanServiceUtil.class);

    public static <S, R> List<R> copy(List<S> list, Class<R> clazz) throws ServiceException {
        try {
            return BeanUtil.copy(list, clazz);
        } catch (Exception e) {
            LOGGER.error("copy error" , e);
            throw new ServiceException(ResponseEnum.SYSTEM_ERROR, "copy error");
        }
    }
    public static <S, R> List<R> copy(List<S> list, Class<R> clazz, boolean emptyResult) throws ServiceException {
        try {
            if ((list == null || list.isEmpty()) && emptyResult) {
                return new ArrayList<>();
            } else {
                return BeanUtil.copy(list, clazz);
            }
        } catch (Exception e) {
            LOGGER.error("copy error" , e);
            throw new ServiceException(ResponseEnum.SYSTEM_ERROR, "copy error");
        }
    }
}
