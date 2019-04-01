package org.islihy.toy.helper;

import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/14 3:04 PM
 */
public class ClassHelperTest {


    @Test
    void getClassSet(){

        Set<Class<?>> CLASS_SET = ClassHelper.getControllerClassSet();

        for (Class c:CLASS_SET) {
            System.out.println("all:"+c.getName());
        }
    }
}
