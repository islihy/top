package org.islihy.toy.test;

import org.islihy.toy.annotation.RequestMapping;
import org.islihy.toy.bean.Param;
import org.islihy.toy.business.TestController;
import org.islihy.toy.util.ReflectionUtil;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/15 11:04 AM
 */
public class ReflectTest {

    @Test
    void invokMethod(){
        Object o = ReflectionUtil.newInstance(TestController.class);

        Method[] methods = TestController.class.getDeclaredMethods();

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("ar","2");
        Param param = new Param(paramMap);
        Object[] strings = {"好多书"};
        Object[] tt = param.getObjectArr();
        for (Method method:methods){
            if(method.isAnnotationPresent(RequestMapping.class)){
                Object o1 = ReflectionUtil.invokeMethod(o,method, strings);
                o1.toString();
            }
        }
    }
}
