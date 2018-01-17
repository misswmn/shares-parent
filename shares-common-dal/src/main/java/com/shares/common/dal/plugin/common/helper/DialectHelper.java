package com.shares.common.dal.plugin.common.helper;


import com.shares.common.dal.plugin.common.dialect.Dialect;
import com.shares.common.dal.plugin.common.dialect.DialectFactory;

import java.util.HashMap;
import java.util.Map;


public abstract class DialectHelper {
	private static Map<Dialect.Type, Dialect> MAPPERS = new HashMap<Dialect.Type, Dialect>();

	public static Dialect getDialect(Dialect.Type dialectType) {
		if (MAPPERS.containsKey(dialectType)) {
			return MAPPERS.get(dialectType);
		} else {
			Dialect dialect = DialectFactory.buildDialect(dialectType);
			MAPPERS.put(dialectType, dialect);
			return dialect;
		}
	}
}
