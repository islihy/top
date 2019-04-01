package org.islihy.toy.helper;

import org.islihy.toy.business.TestController;
import org.islihy.toy.util.ClassUtil;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/14 5:04 PM
 */
public class BeanHelperTest {

    @Test
    void getBeanMap(){
        ClassUtil.loadClass(IocHelper.class.getName());
        Map<Class<?>, Object> beanMap =BeanHelper.getBeanMap();

        for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()){
            Class<?> c = beanEntry.getKey();
            Object o = beanEntry.getValue();
            if (c.getName().equalsIgnoreCase("org.islihy.toy.business.TestController")){
                TestController t = (TestController)o;
//                System.out.printf(t.sayHi().toString());
            }
        }
    }
}
