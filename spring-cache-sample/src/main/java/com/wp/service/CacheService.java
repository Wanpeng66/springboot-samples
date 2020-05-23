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
@CacheConfig
public class CacheService {

    @Cacheable(cacheNames = {"defaultCache","hotCache"},key = "#cacheService.#methodName")
    public String getCache() throws InterruptedException {
        TimeUnit.SECONDS.sleep( 3 );
        return "this is hot-cache-value";
    }
}
