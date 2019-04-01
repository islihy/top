package org.islihy.toy.test;


import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Logger;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/27 1:19 AM
 */
public class JdbcTest {

    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false";
        String username = "root";
        String passwd = "123456";

        Connection connection ;
        Statement st;
        try {
            connection = (Connection)DriverManager.getConnection(url,username,passwd);
            st = connection.createStatement();

            ResultSet rs = st.executeQuery("select * from t_user where password = '123434sdg'");

            while (rs.next()) {
                String s = rs.getString("userName");
                System.out.println(s);
            }

            st.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class  DataSource implements javax.sql.DataSource{


    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public java.sql.Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public java.sql.Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}