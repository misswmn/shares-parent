package com.shares.common.dal.plugin.common.model;

/**
 * @author wangmn
 * @description
 * @date 2018/1/19 16:48
 */
public class PageRequest<T> implements PageParam {
    private T param;
    private Integer page;
    private Integer count;

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    @Override
    public Integer getCount() {
        return count == null ? DEFAULT_COUNT : count;
    }

    @Override
    public Integer getPage() {
        return page == null ? DEFAULT_PAGE : page;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
