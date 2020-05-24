package com.wp.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author: wp
 * @Title: CacheService
 * @Description: TODO
 * @date 2020/5/23 17:27
 */
@Service
@CacheConfig(cacheNames = {"defaultCache","hotCache"},keyGenerator = "customKeyGenerator")
public class CacheService {

    @Cacheable
    public String getCache() {
        try {
            TimeUnit.SECONDS.sleep( 3 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "this is hot-cache-value";
    }
}
