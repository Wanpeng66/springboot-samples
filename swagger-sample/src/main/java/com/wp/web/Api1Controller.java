package com.wp.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wp
 * @Title: Api1Controller
 * @Description: TODO
 * @date 2020/6/22 15:37
 */
@RestController
@Api(tags = "api1")
@RequestMapping("/api1")
public class Api1Controller {

    @ApiOperation( value = "api1中的method1", notes = "api1中的method1方法...")
    @GetMapping("/method1")
    public String method1(){
        return "/api1/method1";
    }

    @PostMapping("/method2")
    @ApiOperation( value = "api1中的method2", notes = "api1中的method2...")
    public String method2(){
        return "/api1/method2";
    }




}
