package com.wp.config.Lock;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author: wp
 * @Title: DistributedLocker
 * @Description: TODO
 * @date 2020/5/24 16:45
 */
public interface DistributedLocker {
    void lock(String lockKey);

    void unlock(String lockKey);

    void lock(String lockKey, int timeout);

    void lock( String lockKey, TimeUnit unit , int timeout);

    boolean tryLock(String lockKey, TimeUnit unit , int timeout);

    RLock getReidisionLock( String lockKey);

}
