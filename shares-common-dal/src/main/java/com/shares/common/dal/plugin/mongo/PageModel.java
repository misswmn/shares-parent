package com.shares.common.dal.plugin.mongo;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/6/15
 * @description 分页
 */
public class PageModel<T> implements Serializable {
    /**
     * 结果集
     */
    private List<T> rows;
    /**
     * 查询记录数
     */
    private int total;
    /**
     * 每页条数
     */
    private int pageSize = 20;
    /**
     * 第几页
     */
    private int page = 1;
    /**
     * 跳过条数
     */
    private int skip = 0;

    /**
     * 总页数
     *
     * @return
     */
    private int totalPages;

    public int getTotalPages() {
        return (total + pageSize - 1) / pageSize;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize < 10 ? 10 : pageSize;
    }

    public int getSkip() {
        skip = (page - 1) * pageSize;
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page < 1 ? 1 : page;
    }

    public void copy(PageModel src, PageModel result) {
        result.setTotalPages(src.getTotalPages());
        result.setTotal(src.getTotal());
        result.setPage(src.getPage());
        result.setPageSize(src.getPageSize());
        result.setSkip(src.getSkip());
    }
}
