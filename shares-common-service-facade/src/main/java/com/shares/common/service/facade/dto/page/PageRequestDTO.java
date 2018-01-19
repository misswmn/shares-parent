package com.shares.common.service.facade.dto.page;

import java.io.Serializable;

/**
 * @author wangmn
 * @description
 * @date 2018/1/19 16:48
 */
public class PageRequestDTO<T> extends PageParamDTO implements Serializable {
    private static final long serialVersionUID = 2751558944075372713L;
    private T param;

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}
