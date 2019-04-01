package org.islihy.toy.test;

import org.junit.jupiter.api.Test;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/27 1:05 AM
 */
public class JdbcTest {



    @Test
    public void jdbcTe()throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/workerman?useUnicode=true&characterEncoding=utf8&useSSL=false";
        String username = "root";
        String passwd = "123456";

        Connection connection = (Connection) DriverManager.getConnection(url,username,passwd);
        System.out.printf(connection.getClientInfo().toString());
    }
}
