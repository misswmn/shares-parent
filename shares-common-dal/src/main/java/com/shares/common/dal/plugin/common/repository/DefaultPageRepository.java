package com.shares.common.dal.plugin.common.repository;

import com.shares.common.dal.plugin.common.PaginationInterceptor;
import com.shares.common.dal.plugin.common.model.PageParam;
import com.shares.common.dal.plugin.common.model.PageRequest;
import com.shares.common.dal.plugin.common.model.PageResult;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.text.MessageFormat;
import java.util.List;


public class DefaultPageRepository<T extends SqlSession> implements PageRepository {

	private T sqlSession;

	public DefaultPageRepository() {
		super();
	}

	public DefaultPageRepository(T sqlSession) {
		this();
		this.sqlSession = sqlSession;
	}

	public String getStatement(Class mapperClass, String sqlId) {
		return MessageFormat.format("{0}.{1}", mapperClass.getName(), sqlId);
	}

	public RowBounds getRowBounds(int page, int count) {
		page = page <= 0 ? 1 : page;
		count = count <= 0 ? 10 : count;
		return new RowBounds((page - 1) * count, count);
	}

	public RowBounds getRowBounds(PageParam parameter) {
		int page = parameter.getPage();
		int count = parameter.getCount();
		return this.getRowBounds(page, count);
	}

	public void rendPageResult(PageResult pageResult, RowBounds rowBounds) {
		List rows = pageResult.getRows();
		if (rows != null) {
			pageResult.setNumber(rows.size());
		}
		int count = pageResult.getCount();
		int page = pageResult.getPage();
		pageResult.setTotalPages(pageResult.getTotal() / pageResult.getCount()
				+ (pageResult.getTotal() % pageResult.getCount() != 0 ? 1 : 0));
		pageResult.setFirst(page == 1);
		pageResult.setLast(pageResult.getTotalPages() == page);
	}

	@Override
	public <E> PageResult<E> selectPaging(Class mapperClass, String sqlId, PageRequest<?> parameter) {
		String statement = this.getStatement(mapperClass, sqlId);
		return this.selectPaging(statement, parameter);
	}

	@Override
	public <E> PageResult<E> selectPaging(String statement, PageRequest<?> parameter) {
		return this.selectPaging(statement, parameter.getParam(), parameter.getPage(), parameter.getCount());
	}

	@Override
	public <E> PageResult<E> selectPaging(String statement, Object parameter, int page, int count) {
		RowBounds rowBounds = this.getRowBounds(page, count);
		List<E> rows = sqlSession.selectList(statement, parameter, rowBounds);
		int total = PaginationInterceptor.getPaginationTotal();
		PaginationInterceptor.clean();
		PageResult<E> pageResult = new PageResult<>(rows, total, page, count);
		this.rendPageResult(pageResult, this.getRowBounds(page, count));
		return pageResult;
	}

	@Override
	public <E> List<E> selectRows(Class mapperClass, String sqlId, PageParam parameter) {
		RowBounds rowBounds = this.getRowBounds(parameter.getPage(), parameter.getCount());
		String statement = this.getStatement(mapperClass, sqlId);
		return sqlSession.selectList(statement, parameter, rowBounds);
	}

	public void setSqlSession(T sqlSession) {
		this.sqlSession = sqlSession;
	}

	public T getSqlSession() {
		return sqlSession;
	}
}
