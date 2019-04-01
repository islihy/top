package org.islihy.toy.helper;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/14 3:03 PM
 */
public class ConfigHelperTest {

    @Test
    void getAppBasePackage(){
        String basePackage =  ConfigHelper.getAppBasePackage();
        assertEquals("org.islihy.toy",basePackage);
    }
}
