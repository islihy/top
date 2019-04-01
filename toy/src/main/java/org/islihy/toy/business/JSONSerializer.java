package org.islihy.toy.business;

import com.alibaba.fastjson.JSON;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/14 8:28 PM
 */
public class JSONSerializer implements Serializer{
    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
