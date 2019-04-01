package org.islihy.toy.helper;

import org.islihy.toy.annotation.RequestMapping;
import org.islihy.toy.bean.Handler;
import org.islihy.toy.bean.Request;
import org.islihy.toy.util.ArrayUtil;
import org.islihy.toy.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/14 5:56 PM
 */
public final class ControllerHelper {
    /**
     * 用于存放请求与处理器的映射关系（简称Action Map）
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        //获取所有的Controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            //遍历这些Controller类
            for (Class<?> controllerClass : controllerClassSet) {
                //获得Controller类中定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)) {
                    //遍历类中的方法
                    for (Method method : methods) {

                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            //处理有Action注解的方法
                            RequestMapping rm = method.getAnnotation(RequestMapping.class);
                            String mapping = rm.value();
                            //解析Action中的value
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    //get,post
                                    String requestMethod = array[0];
                                    //路径 "/../.."
                                    String requestPath = array[1];
                                    //构建request handler
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取 Handler
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
