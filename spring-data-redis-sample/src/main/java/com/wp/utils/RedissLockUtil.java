package com.wp.utils;

import com.wp.config.Lock.DistributedLocker;
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * @author: wp
 * @Title: RedissLockUtil
 * @Description: TODO
 * @date 2020/5/24 16:46
 */
public class RedissLockUtil {

    private static DistributedLocker redissLock;

    public static void setLocker(DistributedLocker locker) {
        redissLock = locker;
    }

    public static void lock(String lockKey) {
        redissLock.lock(lockKey);
    }

    public static void unlock(String lockKey) {
        redissLock.unlock(lockKey);
    }

    /**
     * 带超时的锁
     * @param lockKey
     * @param timeout 超时时间   单位：秒
     */
    public static void lock(String lockKey, int timeout) {
        redissLock.lock(lockKey, timeout);
    }

    public static boolean tryLock(String lockKey, TimeUnit unit, int timeout) {
        return redissLock.tryLock(lockKey, unit, timeout);
    }

    public static RLock getReidisionLock( String lockKey ) {
        return redissLock.getReidisionLock( lockKey );
    }

    /**
     * 带超时的锁
     * @param lockKey
     * @param unit 时间单位
     * @param timeout 超时时间
     */
    public static void lock( String lockKey, TimeUnit unit , int timeout) {
        redissLock.lock(lockKey, unit, timeout);
    }
}
