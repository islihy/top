package org.islihy.toy.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/14 5:29 PM
 */
public final class ArrayUtil {
    /**
     * 判断数组是否非空
     */
    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }

    /**
     * 判断数组是否为空
     */
    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }
}
