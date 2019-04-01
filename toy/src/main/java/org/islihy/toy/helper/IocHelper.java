package org.islihy.toy.helper;

import org.islihy.toy.annotation.Inject;
import org.islihy.toy.util.ArrayUtil;
import org.islihy.toy.util.CollectionUtil;
import org.islihy.toy.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 通过BeanHelper获取所有Bean Map（是一个Map<Class<?>,Object>结构，记录了类与对象的映射关系，）
 * 然后遍历这个映射关系，分别去除Bean类与Bean实例，进而通过反射获取类中所有的成员变量。继续遍历这些成员变量，
 * 在循环中判断当前成员变量是否带有Inject注解，判断当前成员变量是否带有Inject注解，若带有该注解，则从Bean Map
 * 中根据Bean类取出Bean实例。最后通过ReflectionUtil#Field方法来修改当前成员变量的值
 *
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/14 5:26 PM
 */
public final class IocHelper {
    static {
        /* 获取所有的Bean类与Bean实例之间的映射关系 */
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            /* 遍历Bean Map */
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                /* 从Bean Map中获取Bean类与Bean实例 */
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                /* 获取Bean类定义的所有成员变量 */
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    /* 遍历Bean Field */
                    for (Field beanField : beanFields) {
                        /* 判断当前Bean Field 是否带有Inject注解 */
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            /**
                             * 在Bean Map中获取Bean Field对应的实例
                             */
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                /* 通过反射初始化 BeanFiled的值 */
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
