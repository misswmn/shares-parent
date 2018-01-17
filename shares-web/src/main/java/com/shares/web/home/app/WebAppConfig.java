package com.shares.web.home.app;

import com.shares.common.util.JedisClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by misswmn on 2016/12/27.
 */
@Configuration
@EnableAspectJAutoProxy
@PropertySources(
        @PropertySource(value = {"classpath:orange.properties"})
)
@ComponentScan(basePackages = "com.shares", lazyInit = true, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,
        value = {Controller.class, ControllerAdvice.class, RestController.class})})
@Import({AppDataConfig.class, DataSourceConfig.class})
public class WebAppConfig {

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.timeout}")
    private int redisTimeout;
    @Value("${redis.password}")
    private String redisPassword;
    @Value("${redis.database}")
    private int redisDatabase;

    @Value("${redis.pool.maxIdle}")
    private int redisMaxIdle;
    @Value("${redis.pool.minIdle}")
    private int redisMinIdle;
    @Value("${redis.pool.maxTotal}")
    private int redisMaxTotal;
    @Value("${redis.pool.maxWaitMillis}")
    private int redisMaxWaitMillis;

    @Bean
    public JedisClient jedisClient() {
        JedisClient jedisClient = new JedisClient();
        jedisClient.setJedisPool(jedisPool());
        return jedisClient;
    }

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisMaxIdle);
        jedisPoolConfig.setMinIdle(redisMinIdle);
        jedisPoolConfig.setMaxTotal(redisMaxTotal);
        jedisPoolConfig.setMaxWaitMillis(redisMaxWaitMillis);
        return new JedisPool(jedisPoolConfig, host, port, redisTimeout);
    }
}
