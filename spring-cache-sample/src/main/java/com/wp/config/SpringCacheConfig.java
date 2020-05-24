package com.wp.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.*;

/**
 * @author: wp
 * @Title: SpringCacheConfig
 * @Description: TODO
 * @date 2020/5/23 17:05
 */
@Configuration
@EnableCaching
public class SpringCacheConfig {

    @Bean
    public RedisTemplate<String,Object> stringObjectRedisTemplate( RedisConnectionFactory redisConnectionFactory ){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory( redisConnectionFactory );
        redisTemplate.setKeySerializer( keySerializer() );
        redisTemplate.setValueSerializer( valueSerializer() );
        redisTemplate.setHashKeySerializer( keySerializer() );
        redisTemplate.setHashValueSerializer( valueSerializer() );
        return redisTemplate;

    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }
    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

    @Primary
    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory){
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        // 设置缓存管理器管理的缓存的默认过期时间
        defaultCacheConfig = defaultCacheConfig.entryTtl(Duration.ofMinutes( 20 ))
                // 设置 key为string序列化
                .serializeKeysWith( RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 设置value为json序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                // 不缓存空值
                .disableCachingNullValues();

        Set<String> cacheNames = new HashSet<>();
        cacheNames.add("defaultCache");
        cacheNames.add( "hotCache" );

        // 对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>(2);
        configMap.put("hotCache", defaultCacheConfig.entryTtl(Duration.ofSeconds(60)));

        RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultCacheConfig)
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(configMap)
                .build();
        return cacheManager;
    }

    @Bean("customKeyGenerator")
    public KeyGenerator customKeyGenerator(){
        return  new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return target.getClass()+"."+method.getName()+"(params:"+ Arrays.asList(params).toString() +")";
            }
        };
    }
}
