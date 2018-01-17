package com.shares.common.util.section;

import java.util.List;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/6/13
 * @description
 */
public abstract class AbstractDictionary<S, T, R> implements Dictionary<S, R> {

    abstract List<T> init(S dict);

    abstract R getValue(List<T> dict, IValue value);

    @Override
    public R transfer(S dict, IValue value) {
        return getValue(init(dict), value);
    }
}
