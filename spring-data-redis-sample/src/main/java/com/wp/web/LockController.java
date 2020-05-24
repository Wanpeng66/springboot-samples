package com.wp.web;

import com.wp.utils.RedissLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: wp
 * @Title: LockController
 * @Description: TODO
 * @date 2020/5/24 16:50
 */
@Slf4j
@RestController
public class LockController {
    public static final String LOCKNAME = "testLock";

    @GetMapping("/slow")
    public String slow(){
        int count = 3;
        boolean flag = false;
        RLock rLock = RedissLockUtil.getReidisionLock( LOCKNAME );
        while( count > 0 ){
            try {
                //如果想使用redission看门狗功能，就不能主动给锁设置过期时间，要使用tryLock方法
                if(( flag = rLock.tryLock( 100L, TimeUnit.MILLISECONDS ))){
                    break;
                }
            } catch (InterruptedException e) {
            }
            count--;
        }
        if(flag){
            log.info( "slow 获得锁成功..." );
            try {
                TimeUnit.SECONDS.sleep( 50 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if(rLock.isHeldByCurrentThread()){
                    log.info( "锁未超时..." );
                }
                RedissLockUtil.unlock(LOCKNAME);
                log.info( "slow 释放锁成功..." );
            }
            return "slow done...";
        }else{
            return "slow failure...";
        }

    }

    @GetMapping("/fast")
    public String fast(){
        int count = 3;
        boolean flag = false;
        while(!(flag = RedissLockUtil.tryLock( LOCKNAME, TimeUnit.MILLISECONDS, 100 )) && count > 0 ){
            count--;
        }
        if(flag){
            log.info( "fast 获得锁成功..." );
            try {
                TimeUnit.SECONDS.sleep( 4 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                RedissLockUtil.unlock(LOCKNAME);
                log.info( "slow 释放锁成功..." );
            }
            return "fast done...";
        }else{
            return "fast failure...";
        }

    }
}
