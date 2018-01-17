package com.shares.common.dal.plugin.common.helper;

import com.shares.common.dal.plugin.common.dialect.Dialect;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


public abstract class SqlHelper {
	private static Logger logger = LoggerFactory.getLogger(SqlHelper.class);

	public static int getCount(final BoundSql boundSql, final MappedStatement ms, final Connection connection,
			final Object parameterObject, Dialect dialect) throws SQLException {
		String countSql = dialect.getCountString(boundSql.getSql());

		logger.debug("Total count SQL [{}]", countSql);
		logger.debug("Parameters: {} ", parameterObject);

		PreparedStatement stmt = null;
		ResultSet rs;
		try {
			stmt = connection.prepareStatement(countSql);
			DefaultParameterHandler handler = new DefaultParameterHandler(ms, parameterObject, boundSql);
			handler.setParameters(stmt);
			rs = stmt.executeQuery();

			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}

			return count;
		} finally {
			closeStatement(stmt);
		}
	}

	private static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// ignore
			}
		}
	}

	public static String removeOrders(String sql) {
		int index = sql.toLowerCase().lastIndexOf("order by");
		if (index == -1) {
			return sql;
		}
		return sql.substring(0, index);
	}
}
