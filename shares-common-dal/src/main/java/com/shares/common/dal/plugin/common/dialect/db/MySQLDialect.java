package com.shares.common.dal.plugin.common.dialect.db;


import com.shares.common.dal.plugin.common.dialect.Dialect;

public class MySQLDialect extends Dialect {
	@Override
	public boolean supportsLimit() {
		return true;
	}

	@Override
	public String getLimitString(final String sql, final int offset, final int limit) {
		return getLimitString(sql, offset, Integer.toString(offset), Integer.toString(limit));
	}

	private String getLimitString(final String sql, final int offset, final String offsetPlaceholder,
			final String limitPlaceholder) {
		StringBuilder stringBuilder = new StringBuilder(getLineSql(sql));
		stringBuilder.append(" limit ");
		if (offset > 0) {
			stringBuilder.append(offsetPlaceholder).append(",").append(limitPlaceholder);
		} else {
			stringBuilder.append(limitPlaceholder);
		}

		return stringBuilder.toString();
	}
}
