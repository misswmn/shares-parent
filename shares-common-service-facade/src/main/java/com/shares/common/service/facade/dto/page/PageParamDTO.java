package com.shares.common.service.facade.dto.page;

import java.io.Serializable;

/**
 * @author wangmn
 * @description
 * @date 2018/1/19 16:40
 */
public class PageParamDTO implements Serializable {
    private static final long serialVersionUID = -4129349135133115781L;
    private Integer page;
    private Integer count;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
