package com.shares.common.dal.plugin.mongo;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/6/15
 * @description
 */
public class Condition<T> {

    private Query<T> query;

    public Condition(Datastore datastore, Class<T> t) {
        query = datastore.createQuery(t);
    }

    public Condition<T> equal(String field, Object value) {
        if (!StringUtils.isEmpty(value)) query.field(field).equal(value);
        return this;
    }

    public Condition<T> notEqual(String field, String value) {
        if (!StringUtils.isEmpty(value)) query.field(field).notEqual(value);
        return this;
    }

    public Condition<T> in(String field, Iterable<?> values) {
        if (values != null) query.field(field).in(values);
        return this;
    }

    public Condition<T> notIn(String field, Iterable<?> values) {
        if (values != null) query.field(field).notIn(values);
        return this;
    }

    public Condition<T> between(String field, Object left, Object right) {
        query.field(field).greaterThanOrEq(left).field(field).lessThanOrEq(right);
        return this;
    }

    public Condition<T> lessThan(String field, Object value) {
        if (!StringUtils.isEmpty(value)) query.field(field).lessThan(value);
        return this;
    }

    public Condition<T> lessThanOrEq(String field, Object value) {
        if (!StringUtils.isEmpty(value)) query.field(field).lessThanOrEq(value);
        return this;
    }

    public Condition<T> greaterThan(String field, Object value) {
        if (!StringUtils.isEmpty(value)) query.field(field).greaterThan(value);
        return this;
    }

    public Condition<T> greaterThanOrEq(String field, Object value) {
        if (!StringUtils.isEmpty(value)) query.field(field).greaterThanOrEq(value);
        return this;
    }

    public Condition<T> like(String field, String value) {
        if (!StringUtils.isEmpty(value)) query.field(field).contains(value);
        return this;
    }

    public Condition<T> startsWith(String field, String prefix) {
        if (!StringUtils.isEmpty(prefix)) query.field(field).startsWith(prefix);
        return this;
    }

    public Condition<T> sort(String field, Direction sort) {
        query.order(Direction.ASC.equals(sort) ? "+" + field : "-" + field);
        return this;
    }

    public int getTotal() {
        return (int) query.countAll();
    }

    public List<T> execute() {
        return query.retrievedFields(false, "_id").asList();
    }

    public List<T> execute(int skip, int pageSize) {
        query.retrievedFields(false, "_id").offset(skip).limit(pageSize);
        return query.asList();
    }
}
