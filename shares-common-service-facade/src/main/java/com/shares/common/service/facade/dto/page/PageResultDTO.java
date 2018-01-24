package com.shares.common.service.facade.dto.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageResultDTO<E> implements Serializable {
    private static final long serialVersionUID = -8431088974054875752L;
    /**
     * 第几页
     */
    private Integer page = 1;
    /**
     * 每页记录数
     */
    private Integer count = 10;
    /**
     * 当前页记录数
     */
    private Integer number;
    /**
     * 总页数
     */
    private Integer totalPages;
    /**
     * 总记录数
     */
    private Integer total;
    /**
     * 是否第一页
     */
    private Boolean first = true;
    /**
     * 是否最后一页
     */
    private Boolean last = true;
    private List<E> rows = new ArrayList<>();

    public PageResultDTO() {
    }

    public PageResultDTO(List<E> rows, int total) {
        this.total = total;
        this.rows = rows;
    }

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }
}
