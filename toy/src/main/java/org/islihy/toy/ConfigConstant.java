package org.islihy.toy;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/14 1:55 AM
 */
public interface ConfigConstant {
    String CONFIG_FILE = "toy.properties";

    /**
     * jdbc驱动路径
     */
    String JDBC_DRIVER = "toy.jdbc.driver";

    String JDBC_URL = "toy.jdbc.url";
    String JDBC_USERNAME = "toy.jdbc.username";
    String JDBC_PASSWORD = "toy.jdbc.password";

    /**
     * 表示项目的基础包名
     */
    String APP_BASE_PACKAGE = "toy.app.base_package";
    /**
     * jsp的基础路径
     */
    String APP_JSP_PATH = "toy.app.jsp_path";
    /**
     * 静态资源文件的基础路径
     */
    String APP_ASSET_PATH = "toy.app.asset_path";

    String APP_UPLOAD_LIMIT = "toy.app.upload_limit";
}
