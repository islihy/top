package com.zk.nutz.module;

import com.zk.nutz.eureka.BaseFeignBuilder;
import com.zk.nutz.eureka.BaseFeignClient;
import com.zk.nutz.eureka.BaseFeignClientFallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/12 4:39 PM
 */
@IocBean
@At("/test")
@Ok("json")
public class TestModule {
    private BaseFeignClient xxxClient = null;


    @At
    public void get(@Param("id") String id) {
        xxxClient = BaseFeignBuilder.getCacheFeign(BaseFeignClient.class,
                "xxx-service", new BaseFeignClientFallback(), "xxx");
    }
}
