package com.zk.nutz;

import org.nutz.mvc.annotation.*;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/8 5:05 PM
 *
 *
 * 项目以json交互为主,所以,默认用json视图好了.
 * 这里的json指UTF8JsonView类, 后面的full是JsonFormat的其中一种内置格式的缩写:
 * 1. 默认 -- 忽略空值,换行,key不带双引号, 新版jquery不兼容
 * 2. full -- 不忽略空值,换行,key带双引号, 新版jquery兼容
 * 2. compact -- 不换行,忽略空值,key不带双引号, 新版jquery不兼容
 */
@SetupBy(value=MainSetup.class)
@IocBy(args={"*js", "conf/ioc/", "*anno", "com.zk.nutz",
        "*tx", // 事务拦截 aop
        "*async"}) // 异步执行aop
@Modules
@Ok("json:full")
@Fail("jsp:jsp.500")
@Localization(value="msg/", defaultLocalizationKey="zh-CN")
public class MainModule {
}