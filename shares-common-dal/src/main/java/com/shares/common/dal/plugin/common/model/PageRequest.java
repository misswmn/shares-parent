package com.shares.common.dal.plugin.common.model;

/**
 * @author wangmn
 * @description
 * @date 2018/1/19 16:48
 */
public class PageRequest<T> implements PageParam {
    private T param;
    private int page;
    private int count;

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
