package org.islihy.toy.business;

/**
 * 定义序列化接口
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/14 8:26 PM
 */
public interface Serializer {
    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
