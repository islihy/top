package com.zk.nutz.eureka;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.config.ConfigurationManager;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;

/**
 * @ClassName: EurekaInitAndRegisterListener
 * @Description:  服务器启动初始化Ribbon和注册到Eureka Server
 * @author SuXun
 * @date 2018年3月7日 上午9:21:12
 */
@SuppressWarnings("deprecation")
public class EurekaInitAndRegisterListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(EurekaInitAndRegisterListener.class);
    /**
     * 默认的ribbon配置文件名, 该文件需要放在classpath目录下
     */
    public static final String RIBBON_CONFIG_FILE_NAME = "ribbon.properties";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("开始初始化ribbon");
        try {
            // 加载ribbon配置文件
            ConfigurationManager.loadPropertiesFromResources(RIBBON_CONFIG_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("ribbon初始化失败");
            throw new IllegalStateException("ribbon初始化失败");
        }
        LOGGER.info("ribbon初始化完成");
        // 初始化Eureka Client
        LOGGER.info("Eureka初始化完成,正在注册Eureka Server");
        DiscoveryManager.getInstance().initComponent(new MyInstanceConfig(), new DefaultEurekaClientConfig());
        ApplicationInfoManager.getInstance().setInstanceStatus(InstanceInfo.InstanceStatus.UP);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DiscoveryManager.getInstance().shutdownComponent();
    }

}
