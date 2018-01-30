package com.shares.biz.shared.shiro.cache;

import com.shares.core.model.bo.ConstantsBO;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.inject.Inject;

/**
 * @author wangmn
 * @description
 * @date 2018/1/30 16:20
 */
public class CustomShiroCacheManager implements CacheManager, Destroyable {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomShiroCacheManager.class);
    @Inject
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        LOGGER.info("获取redis cache: {}", name);
        return new JedisShiroCache<>(ConstantsBO.Session.SHARES_REDIS_CACHE, redisTemplate);
    }

    @Override
    public void destroy() throws Exception {

    }
}
