package org.lhy.winxin.bear.controller;

import org.lhy.winxin.bear.config.wx.WxMpProperties;
import org.lhy.winxin.bear.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/3 下午2:24
 */
@RestController
public class HelloController {

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private WxMpProperties wxMpProperties;

    @GetMapping(value = "/hello")
    public String sayHello(){
        return "Hello world : "+ wxMpProperties.getToken();
    }

    @GetMapping(value = "/redis")
    public String testRedis(){

        redisOperator.set("key","1234");
        return "ok";
    }
}
