package org.islihy.toy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/14 11:06 AM
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    /**
     * 请求类型和路径
     * @return
     */
    String value();
}
