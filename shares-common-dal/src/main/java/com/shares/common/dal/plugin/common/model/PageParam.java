package com.shares.common.dal.plugin.common.model;

public interface PageParam {
	int DEFAULT_PAGE = 1;
	int DEFAULT_COUNT = 10;

	/**
	 * 请求分页数据大小
	 * 
	 * @return 分页大小
	 */
	Integer getCount();

	/**
	 * 请求页数
	 * 
	 * @return 页数
	 */
	Integer getPage();

}
