package org.islihy.toy.util;

import org.islihy.toy.ConfigConstant;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/14 2:01 AM
 */
public class PropsUtilTest {

    @Test
    void test(){
        Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);
        assertEquals("com.mysql.jdbc.Driver",PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER));
    }
}
