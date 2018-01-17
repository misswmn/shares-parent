package com.shares.common.dal.plugin.common;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.session.Configuration;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/5/8
 * @description
 */
public interface LanguageDriver {

    ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql);

    SqlSource createSqlSource(Configuration configuration, XNode script, Class<?> parameterType);

    SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType);
}
