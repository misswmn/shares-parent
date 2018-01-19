package com.shares.common.service.facade.dto.page;

import java.io.Serializable;

/**
 * @author wangmn
 * @description
 * @date 2018/1/19 16:40
 */
public class PageParamDTO implements Serializable {
    private static final long serialVersionUID = -4129349135133115781L;
    private int page;
    private int count;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
