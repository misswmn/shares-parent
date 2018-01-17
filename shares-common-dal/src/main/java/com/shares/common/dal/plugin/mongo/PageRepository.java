package com.shares.common.dal.plugin.mongo;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/6/15
 * @description
 */
public interface PageRepository {
    <M> PageModel<M> selectPaging(Condition<M> condition, Integer page, Integer pageSize);
}
