package com.wp.web;

import com.wp.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wp
 * @Title: CacheController
 * @Description: TODO
 * @date 2020/5/23 17:25
 */
@RestController
public class CacheController {
    @Autowired
    CacheService cacheService;

    @GetMapping("/getCache")

    public String getHotCache(){
        return cacheService.getCache();
    }
}
