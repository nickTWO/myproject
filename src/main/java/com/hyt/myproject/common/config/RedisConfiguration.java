
package com.hyt.myproject.common.config;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import java.lang.reflect.Method;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ConditionalOnExpression("${redis.enable:false}")
public class RedisConfiguration extends CachingConfigurerSupport {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port:6379}")
    private int port;
    @Value("${redis.password:}")
    private String password;
    @Value("${redis.timeout:0}")
    private int timeout;
    @Value("${redis.database:0}")
    private int database;
    @Value("${redis.pool.max-active:8}")
    private int maxTotal;
    @Value("${redis.pool.max-wait:-1}")
    private int maxWaitMillis;
    @Value("${redis.pool.max-idle:8}")
    private int maxIdle;
    @Value("${redis.pool.min-idle:0}")
    private int minIdle;

    public RedisConfiguration() {
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                Object[] var5 = params;
                int var6 = params.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    Object obj = var5[var7];
                    sb.append(obj.toString());
                }

                return sb.toString();
            }
        };
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(this.jedisPoolConfig());
        jedisConnectionFactory.setHostName(this.host);
        jedisConnectionFactory.setPort(this.port);
        if(StringUtils.isNotBlank(this.password)) {
            jedisConnectionFactory.setPassword(this.password);
        }

        return jedisConnectionFactory;
    }

    private JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxWaitMillis((long)this.maxWaitMillis);
        jedisPoolConfig.setMaxTotal(this.maxTotal);
        jedisPoolConfig.setMaxIdle(this.maxIdle);
        jedisPoolConfig.setMinIdle(this.minIdle);
        return jedisPoolConfig;
    }

    @Bean
    public StringRedisTemplate redisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate(this.jedisConnectionFactory());
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
        om.enableDefaultTyping(DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
