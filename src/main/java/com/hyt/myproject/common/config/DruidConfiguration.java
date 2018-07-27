package com.hyt.myproject.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;

/**
 * Created by pc on 2018/4/23.
 */
@Configuration
public class DruidConfiguration {
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;
//    @Value("${datasource.driver-class-name}")
//    private String driverClassName;
    @Value("${datasource.initialSize}")
    private int initialSize;
    @Value("${datasource.minIdle}")
    private int minIdle;
    @Value("${datasource.maxActive}")
    private int maxActive;
    @Value("${datasource.maxWait}")
    private int maxWait;
    @Value("${datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${datasource.validationQuery}")
    private String validationQuery;
    @Value("${datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${datasource.filters}")
    private String filters;
    @Value("${datasource.logSlowSql}")
    private String logSlowSql;
    @Value("${datasource.connectionProperties}")
    private String connectionProperties;
    @Value("${datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Bean
    @Primary
    public DruidDataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.url);
        datasource.setUsername(this.username);
        datasource.setPassword(this.password);
//        datasource.setDriverClassName(this.driverClassName);
        datasource.setInitialSize(this.initialSize);
        datasource.setMinIdle(this.minIdle);
        datasource.setMaxActive(this.maxActive);
        datasource.setMaxWait((long)this.maxWait);
        datasource.setTimeBetweenEvictionRunsMillis((long)this.timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis((long)this.minEvictableIdleTimeMillis);
        datasource.setValidationQuery(this.validationQuery);
        datasource.setTestWhileIdle(this.testWhileIdle);
        datasource.setTestOnBorrow(this.testOnBorrow);
        datasource.setTestOnReturn(this.testOnReturn);
        String[] paramArray = this.connectionProperties.split(";");
        datasource.setConnectionProperties(this.connectionProperties);

        try {
            datasource.setFilters(this.filters);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return datasource;
    }

    public DruidConfiguration() {
    }
}
