package org.islihy.toy.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/14 1:46 AM
 */
public final class PropsUtil {

    static Logger logger = LogManager.getLogger();
    /**
     * 加载属性文件
     * 通过类加载器获取InputStream流
     * 修改属性文件后，即使重新执行，读入的仍为修改前的参数。
     * 此问题的原因在于ClassLoader.getResourceAsStream读入后，会将.properties保存在缓存中，
     * 重新执行时会从缓存中读取，而不是再次读取.properties文件。
     */
    public static Properties loadProps(String fileName) {
        Properties props = null;
        InputStream is = null;
        try {
            is = trans(fileName);
            if (is == null) {
                throw new FileNotFoundException(fileName + " file is not found");
            }
            props = new Properties();
            props.load(is);
        } catch (IOException e) {
            logger.error("load properties file failure", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("close input stream failure", e);
                }
            }
        }
        return props;
    }

    /**
     * 获取 String 类型的属性值（默认值为空字符串）
     */
    public static String getString(Properties props, String key) {
        return getString(props, key, "");
    }

    /**
     * 获取 String 类型的属性值（可指定默认值）
     */
    public static String getString(Properties props, String key, String defaultValue) {
        String value = defaultValue;
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }


    /**
     * 获取数值型属性（默认值为0）
     * @param props
     * @param key
     * @return
     */
    public static int getInt(Properties props,String key){
        return getInt(props,key,0);
    }

    /**
     * 获取数值型属性（可以指定默认值）
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(Properties props,String key ,int defaultValue){
        int value = defaultValue;
        if (props.containsKey(key)){
            value = CastUtil.castInt(props.getProperty(key));
        }

        return value;
    }

    /**
     * 获取布尔类型属性（默认值为false）
     * @param props
     * @param key
     * @return
     */
    public static boolean getBoolean(Properties props,String key){
        return getBoolean(props,key,false);
    }

    /**
     * 获取布尔类型属性（可指定默认值）
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(Properties props,String key ,boolean defaultValue){
        boolean value = defaultValue;
        if (props.containsKey(key)){
            value = CastUtil.castBoolean(defaultValue);
        }
        return value;
    }

    private static InputStream trans(String fileName)throws IOException{

        /**
         * 1.通过相对路径获取文件的stream流
         * "new FileInputStream(ClassUtil.getClassLoader().getResource("")+"/"+fileName);"
         *
         * 2.缓存读取
         */
        return ClassUtil.getClassLoader().getResourceAsStream(fileName);
    }
}
