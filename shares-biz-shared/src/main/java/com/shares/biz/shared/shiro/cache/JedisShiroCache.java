package com.shares.biz.shared.shiro.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.inject.Inject;

/**
 * @author ex-wangmengnan
 * @description
 * @date 2018/1/30 16:28
 */
public class JedisShiroCache<K, V> implements Cache<K, V> {

	private static final Logger LOGGER = LoggerFactory.getLogger(JedisShiroCache.class);
	private String prefix;
	@Inject
	private RedisTemplate<K, V> redisSessionTemplate;

	/**
	 * 
	 */
	public JedisShiroCache() {
		super();
	}

	@SuppressWarnings("unchecked")
	public JedisShiroCache(String prefix, RedisTemplate<?, ?> redisSessionTemplate) {
		this.prefix = prefix;
		this.redisSessionTemplate = (RedisTemplate<K, V>) redisSessionTemplate;
	}

	@Override
	public V get(K key) throws CacheException {
		V result = null;
		key = buildCacheKey(key);
		try {
			LOGGER.debug("get shiro cache : {}", key);
			ValueOperations<K, V> valueOperations = redisSessionTemplate.opsForValue();
			result = valueOperations.get(key);
		} catch (Exception e) {
			LOGGER.error("get shiro cache error", e);
		}
		return result;
	}

	@Override
	public V put(K key, V value) throws CacheException {
		key = buildCacheKey(key);
		try {
			LOGGER.debug("put shiro cache : {}", key);
			ValueOperations<K, V> valueOperations = redisSessionTemplate.opsForValue();
			valueOperations.set(key, value);
		} catch (Exception e) {
			LOGGER.error("put shiro cache error", e);
		}
		return value;
	}

	@Override
	public V remove(K key) throws CacheException {
		V result = null;
		key = buildCacheKey(key);
		try {
			LOGGER.debug("remove shiro cache : {}", key);
			result = get(key);
			ValueOperations<K, V> valueOperations = redisSessionTemplate.opsForValue();
			valueOperations.getOperations().delete(key);
		} catch (Exception e) {
			LOGGER.error("remove shiro cache error", e);
		}
		return result;
	}

	@Override
	public void clear() throws CacheException {
		// TODO
	}

	@Override
	public int size() {
		if (keys() == null)
			return 0;
		return keys().size();
	}

	@Override
	public Set<K> keys() {
		// TODO
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO
		return null;
	}

	@SuppressWarnings("unchecked")
	private K buildCacheKey(Object key) {
		return (K) (prefix + key);
	}

}
