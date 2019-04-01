package org.islihy.toy.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/14 1:48 AM
 */
public final class ClassUtil {
    static Logger logger = LogManager.getLogger();
    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            logger.error("load class failure", e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 加载类（默认将初始化类）
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    /**
     * 获取指定包名下的所有类
     * 根据包名转换为文件路径，读取class文件或jar包，获取指定的类名加载类。
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();
        try {
            /**
             * 获取包的名字 并进行替换
             * 定义一个枚举的集合 并进行循环来处理这个目录下的things
             */
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            //循环迭代
            while (urls.hasMoreElements()) {
                //获取下一个元素
                URL url = urls.nextElement();
                if (url != null) {
                    //获取协议名称
                    String protocol = url.getProtocol();
                    //以文件的形式保存在服务器上
                    if (protocol.equals("file")) {
                        //获取包的物理路径
                        String packagePath = url.getPath().replaceAll("%20", " ");
                        //
                        addClass(classSet, packagePath, packageName);
                    } else if (protocol.equals("jar")) {
                        //如果是个jar包文件
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            //获取jar包文件
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                //从此jar包中得到枚举类
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                //循环迭代改jar文件
                                while (jarEntries.hasMoreElements()) {
                                    //获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    //jar实体文件内容名
                                    String jarEntryName = jarEntry.getName();

                                    if (jarEntryName.endsWith(".class")) {
                                        //处理以.class为结尾的文件 转换为java类完整类名
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        //放入Set<Class<?>>中
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("get class set failure", e);
            throw new RuntimeException(e);
        }
        return classSet;
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(file -> (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory());
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtil.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;
                if (StringUtil.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (StringUtil.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }
}
