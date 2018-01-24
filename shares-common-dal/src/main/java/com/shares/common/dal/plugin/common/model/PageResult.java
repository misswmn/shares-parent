package com.shares.common.dal.plugin.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageResult<E> implements Serializable {
	private static final long serialVersionUID = -831949827706950781L;
	/** 第几页 */
	private Integer page = 1;
	/** 每页记录数 */
	private Integer count = 10;
	/** 当前页记录数 */
	private Integer number;
	/** 总页数 */
	private Integer totalPages;
	/** 总记录数 */
	private Integer total;
	/** 是否第一页 */
	private Boolean first = true;
	/** 是否最后一页 */
	private Boolean last = true;
	private List<E> rows = new ArrayList<>();

	public PageResult() {
	}

	public PageResult(List<E> rows, int total) {
		this.total = total;
		this.rows = rows;
	}

	public PageResult(List<E> rows, int total, int page, int count) {
		this(rows, total);
		this.page = page;
		this.count = count;
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
