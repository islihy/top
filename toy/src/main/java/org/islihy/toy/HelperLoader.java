package org.islihy.toy;

import org.islihy.toy.helper.BeanHelper;
import org.islihy.toy.helper.ClassHelper;
import org.islihy.toy.helper.ControllerHelper;
import org.islihy.toy.helper.IocHelper;
import org.islihy.toy.util.ClassUtil;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/14 6:03 PM
 */
public final class HelperLoader {
    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
