package com.shares.common.util.section;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/6/13
 * @description
 */
public interface Dictionary<S, R> {
    R transfer(S dict, IValue value);
}
