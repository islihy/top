package com.zk.nutz;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.config.ConfigurationManager;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryManager;
import com.zk.nutz.bean.User;
import com.zk.nutz.eureka.MyInstanceConfig;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/8 5:50 PM
 */
public class MainSetup implements Setup {

    private static final Logger LOG = LoggerFactory.getLogger(MainSetup.class);
    @Override
    public void init(NutConfig nc) {
        Ioc ioc = nc.getIoc();
        Dao dao = ioc.get(Dao.class);
        // 如果没有createTablesInPackage,请检查nutz版本
        Daos.createTablesInPackage(dao, "com.zk.nutz", false);

        // 初始化默认根用户
        if (dao.count(User.class) == 0) {
            User user = new User();
            user.setName("admin");
            user.setPassword("123456");
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            dao.insert(user);
        }

//        LOG.info("开始初始化ribbon");
//        try {
//            // 加载ribbon配置文件
//            ConfigurationManager.loadPropertiesFromResources("ribbon.properties");
//        } catch (IOException e) {
//            e.printStackTrace();
//            LOG.error("ribbon初始化失败");
//            throw new IllegalStateException("ribbon初始化失败");
//        }
//        LOG.info("ribbon初始化完成");
//        // 初始化Eureka Client
//        LOG.info("Eureka初始化完成,正在注册Eureka Server");
//        DiscoveryManager.getInstance().initComponent(new MyInstanceConfig(), new DefaultEurekaClientConfig());
//        ApplicationInfoManager.getInstance().setInstanceStatus(InstanceInfo.InstanceStatus.UP);
    }

    @Override
    public void destroy(NutConfig nc) {

    }
}
