package com.shares.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ex-wangmengnan
 */
public class BeanUtil {
    /**
     * 集合复制
     *
     * @param src
     * @param clazz
     * @throws
     */
    public static <S, R> List<R> copy(List<S> src, Class<R> clazz) throws Exception {
        if (CollectionUtils.isEmpty(src)) {
            return null;
        }
        List<R> result = new ArrayList<>(src.size());
        for (S s : src) {
            R r = clazz.newInstance();
            BeanUtils.copyProperties(s, r);
            result.add(r);
        }
        return result;
    }
}
