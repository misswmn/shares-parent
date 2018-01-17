package com.shares.common.dal.plugin.common.repository;


import com.shares.common.dal.plugin.common.model.PageParam;
import com.shares.common.dal.plugin.common.model.PageResult;

import java.util.List;


public interface PageRepository {
	<E> PageResult<E> selectPaging(Class mapperClass, String sqlId, PageParam parameter);

	<E> PageResult<E> selectPaging(String statement, PageParam parameter);

	<E> PageResult<E> selectPaging(String statement, Object parameter, int page, int count);

	<E> List<E> selectRows(Class mapperClass, String sqlId, PageParam parameter);
}
