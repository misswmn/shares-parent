package com.shares.web.home.app;

import com.alibaba.druid.pool.DruidDataSource;
import com.shares.common.dal.plugin.common.PaginationInterceptor;
import com.shares.common.dal.plugin.common.repository.DefaultPageRepository;
import com.shares.common.dal.plugin.common.repository.PageRepository;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
    @Value("${jdbc.url}")
    private String jdbcurl;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.driverClassName}")
    private String driverClass;
    @Value("${jdbc.initialSize}")
    private int initialSize;
    @Value("${jdbc.maxActive}")
    private int maxActive = 20;
    @Value("${jdbc.minIdle}")
    private int minIdle;
    @Value("${jdbc.maxWait}")
    private int maxWait;
    @Value("${jdbc.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${jdbc.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${jdbc.validationQuery}")
    private String validationQuery;
    @Value("${jdbc.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${jdbc.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${jdbc.testOnReturn}")
    private boolean testOnReturn;
    @Value("${jdbc.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${jdbc.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${jdbc.filters}")
    private String filters;

    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(jdbcurl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setFilters(filters);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setMinIdle(minIdle);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        // 分页插件
        Interceptor paginationInterceptor = new PaginationInterceptor();
        Properties properties = new Properties();
        properties.setProperty("stmtIdRegex", "*.*ByPage");
        properties.setProperty("dialect", "mysql");
        paginationInterceptor.setProperties(properties);
        sessionFactory.setPlugins(new Interceptor[]{paginationInterceptor});
        sessionFactory.setTypeAliasesPackage("com.shares.common.dal.dataobject");
        sessionFactory.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resource = patternResolver.getResources("classpath*:mybatis/mapper/**/*.xml");
        sessionFactory.setMapperLocations(resource);
        return sessionFactory;
    }

    @Bean
    public SqlSession sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory().getObject());
    }

    @Bean
    public PageRepository pageRepository() throws Exception {
        return new DefaultPageRepository<>(sqlSession());
    }

    @Bean
    public DataSourceTransactionManager transactionManager() throws SQLException {
        DataSourceTransactionManager tx = new DataSourceTransactionManager();
        tx.setDataSource(dataSource());
        tx.setRollbackOnCommitFailure(true);
        tx.setNestedTransactionAllowed(true);
        return tx;
    }
}
