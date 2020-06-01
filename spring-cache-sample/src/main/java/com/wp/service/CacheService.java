package com.wp.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.CompletionService;
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
    public static void main( String[] args ) {
        String[] arguments = new String[] {"python", "D:/CountingSort.py","2,7,1,10,4"};
        try {

            Process process = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            //java代码中的process.waitFor()返回值为0表示我们调用python脚本成功，
            //返回值为1表示调用python脚本失败，这和我们通常意义上见到的0与1定义正好相反
            int re = process.waitFor();
            BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = error.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println(re);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
