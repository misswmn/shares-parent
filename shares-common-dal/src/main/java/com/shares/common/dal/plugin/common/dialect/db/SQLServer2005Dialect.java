package com.shares.common.dal.plugin.common.dialect.db;


import com.shares.common.dal.plugin.common.dialect.Dialect;
import com.shares.common.dal.plugin.common.helper.SqlHelper;
import com.shares.common.dal.plugin.common.helper.StringHelper;

public class SQLServer2005Dialect extends Dialect {

    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return getLimitString(sql, offset, limit, Integer.toString(limit));
    }

    private String getLimitString(String querySqlString, int offset, int limit, String limitPlaceholder) {
        StringBuilder pagingBuilder = new StringBuilder();
        String orderby = getOrderByPart(querySqlString);
        String distinctStr = "";

        String loweredString = querySqlString.toLowerCase();
        String sqlPartString = querySqlString;
        if (loweredString.trim().startsWith("select")) {
            int index = 6;
            if (loweredString.startsWith("select distinct")) {
                distinctStr = "DISTINCT ";
                index = 15;
            }
            sqlPartString = sqlPartString.substring(index);
        }
        pagingBuilder.append(sqlPartString);

        // if no ORDER BY is specified use fake ORDER BY field to avoid errors
        if (StringHelper.isEmpty(orderby)) {
            orderby = "ORDER BY CURRENT_TIMESTAMP";
        }

        StringBuilder result = new StringBuilder();
        result.append("WITH query AS (SELECT ").append(distinctStr).append("TOP 100 PERCENT ")
                .append(" ROW_NUMBER() OVER (").append(orderby).append(") as __row_number__, ").append(pagingBuilder)
                .append(") SELECT * FROM query WHERE __row_number__ BETWEEN ").append(offset + 1).append(" AND ")
                .append(offset + limit).append(" ORDER BY __row_number__");

        return result.toString();
    }

    static String getOrderByPart(String sql) {
        String loweredString = sql.toLowerCase();
        int orderByIndex = loweredString.lastIndexOf("order by");
        if (orderByIndex != -1) {
            // if we find a new "order by" then we need to ignore
            // the previous one since it was probably used for a subquery
            return sql.substring(orderByIndex);
        } else {
            return "";
        }
    }

    public static String getNonOrderByPart(String sql) {
        return SqlHelper.removeOrders(sql);
    }

    @Override
    public String getCountString(String querySqlString) {
        String sql = getNonOrderByPart(querySqlString);
        return "select count(1) from (" + sql + ") as tmp_count";
    }

}
