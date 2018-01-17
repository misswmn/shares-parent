package com.shares.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wangmn
 * @version 1.0
 * @date 2016/11/10
 * @description
 */
public final class JedisClient {
    private Logger logger = LoggerFactory.getLogger(JedisClient.class);
    private JedisPool jedisPool;

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    private Jedis jedis;

    public static final int EXPIRE = 300;

    /**
     * 向redis存入key和value,并释放连接资源
     *
     * @param key
     * @return
     */
    public synchronized Boolean exists(String key) {
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    /**
     * 向redis存入key和value,并释放连接资源
     *
     * @param key
     * @param value
     * @return
     */
    public synchronized String set(String key, String value) {
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    /**
     * 向redis存入key和value,并释放连接资源
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public synchronized String set(String key, String value, int seconds) {
        try {
            jedis = jedisPool.getResource();
            String result = jedis.set(key, value);
            if (seconds > 0) {
                jedis.expire(key, seconds);
            }
            return result;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    /**
     * 向redis存入key和value,并释放连接资源
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public synchronized String set(byte[] key, byte[] value, int seconds) {
        try {
            jedis = jedisPool.getResource();
            String result = jedis.set(key, value);
            if (seconds > 0)
                jedis.expire(key, seconds);
            return result;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }


    /**
     * 通过key获取在redis中的value
     *
     * @param key
     * @return
     */
    public synchronized String get(String key) {
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }
    public synchronized byte[] get(byte[] key) {
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }
    /**
     * 通过key获取删除在redis中的value
     *
     * @param key
     * @return
     */
    public synchronized Long delete(String key) {
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    /**
     * 通过key数组获取删除在redis中的value
     *
     * @param key
     * @return
     */
    public synchronized Long delete(String... key) {
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    /**
     * 保存map
     *
     * @param key
     * @param hash
     * @param seconds
     * @return
     */
    public synchronized String hmset(String key, Map<String, String> hash, int seconds) {
        try {
            jedis = jedisPool.getResource();
            String result = jedis.hmset(key, hash);
            if (seconds > 0) {
                jedis.expire(key, seconds);
            }
            return result;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    /**
     * @param key
     * @param field
     * @param val
     * @return
     */
    public synchronized Long hset(String key, String field, String val) {
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.hset(key, field, val);
            return result;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    /**
     * 通过key和field判断是否有指定的value存在
     *
     * @param key
     * @param field
     * @return
     */
    public synchronized Boolean hexists(String key, String field) {
        try {
            jedis = jedisPool.getResource();
            Boolean result = jedis.hexists(key, field);
            return result;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    /**
     * 通过key和fields获取指定的value
     *
     * @param key
     * @param fields
     * @return
     */
    public synchronized List<String> hmget(String key, String... fields) {
        try {
            jedis = jedisPool.getResource();
            List<String> result = jedis.hmget(key, fields);
            return result;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    /**
     * 通过key和field获取指定的value
     *
     * @param key
     * @param field
     * @return
     */
    public synchronized String hget(String key, String field) {
        try {
            jedis = jedisPool.getResource();
            return jedis.hget(key, field);
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    /**
     * 通过key和member正序获取指定的value
     *
     * @param key
     * @param member
     * @return
     */
    public synchronized Long zrevrank(String key, String member) {
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.zrevrank(key, member);
            return result;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    /**
     * 通过key和member倒序获取指定的value
     *
     * @param key
     * @param member
     * @return
     */
    public synchronized Long zrank(String key, String member) {
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.zrank(key, member);
            return result;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    /**
     * 通过key获取指定的MAP类型的value
     *
     * @param key
     * @return
     */
    public synchronized Map<String, String> hgetAll(String key) {
        try {
            jedis = jedisPool.getResource();
            Map<String, String> result = jedis.hgetAll(key);
            return result;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    /**
     * 通过key获取指定的MAP类型的value
     *
     * @param key
     * @return
     */
    public synchronized Set<String> hkeys(String key) {
        try {
            jedis = jedisPool.getResource();
            return jedis.hkeys(key);
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    /**
     * 通过key获取指定的MAP类型的value
     *
     * @param key
     * @return
     */
    public synchronized List<String> hvals(String key) {
        try {
            jedis = jedisPool.getResource();
            return jedis.hvals(key);
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    public synchronized Long hlen(String key) {
        try {
            jedis = jedisPool.getResource();
            Long count = jedis.hlen(key);
            return count;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    /**
     * 通过key删除指定的fields
     *
     * @param key
     * @param fields
     * @return
     */
    public synchronized Long hdel(String key, String... fields) {
        try {
            jedis = jedisPool.getResource();
            Long count = jedis.hdel(key, fields);
            return count;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    /**
     * 返回list的count
     *
     * @param key
     * @return
     */
    public synchronized Long llen(String key) {
        try {
            jedis = jedisPool.getResource();
            Long count = jedis.llen(key);
            return count;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    public synchronized Long lpush(String key, int seconds, String... val) {
        try {
            jedis = jedisPool.getResource();
            Long count = jedis.lpush(key, val);
            if (seconds > 0) {
                jedis.expire(key, seconds);
            }
            return count;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    public synchronized Long rpush(String key, int seconds, String... val) {
        try {
            jedis = jedisPool.getResource();
            Long count = jedis.rpush(key, val);
            if (seconds > 0) {
                jedis.expire(key, seconds);
            }
            return count;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    public synchronized String lindex(String key, long index) {
        try {
            jedis = jedisPool.getResource();
            String result = jedis.lindex(key, index);
            return result;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    public synchronized Long linsertBefore(String key, String beforeVal, String val) {
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.linsert(key, BinaryClient.LIST_POSITION.BEFORE, beforeVal, val);
            return result;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    public synchronized Long linsertAfter(String key, String beforeVal, String val) {
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.linsert(key, BinaryClient.LIST_POSITION.AFTER, beforeVal, val);
            return result;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    public synchronized List<String> lrange(String key, long start, long end) {
        try {
            jedis = jedisPool.getResource();
            List<String> result = jedis.lrange(key, start, end);
            return result;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

    /**
     * 清除redis中的keys
     *
     * @return
     */
    public synchronized String flushAll() {
        try {
            jedis = jedisPool.getResource();
            return jedis.flushAll();
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
        }
    }

}
