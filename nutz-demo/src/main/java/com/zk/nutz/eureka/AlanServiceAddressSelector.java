package com.zk.nutz.eureka;

import com.netflix.client.ClientFactory;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: AlanServiceAddressSelector
 * @Description:  获取到目标服务注册在Eureka地址
 * @author SuXun
 * @date 2018年3月2日 下午5:23:24
 */
public class AlanServiceAddressSelector {

    private static final Logger log = LoggerFactory.getLogger(AlanServiceAddressSelector.class);
    private static RoundRobinRule chooseRule = new RoundRobinRule();

    /**
     * 根据轮询策略选择一个地址
     * @param clientName ribbon.properties配置文件中配置项的前缀名, 如myclient
     * @return null表示该服务当前没有可用地址
     */
    public static AlanServiceAddress selectOne(String clientName) {
        // ClientFactory.getNamedLoadBalancer会缓存结果, 所以不用担心它每次都会向eureka发起查询
        @SuppressWarnings("rawtypes")
        DynamicServerListLoadBalancer lb = (DynamicServerListLoadBalancer) ClientFactory
                .getNamedLoadBalancer(clientName);
        Server selected = chooseRule.choose(lb, null);
        if (null == selected) {
            log.warn("服务{}没有可用地址", clientName);
            return null;
        }
        log.debug("服务{}选择结果:{}", clientName, selected);
        return new AlanServiceAddress(selected.getPort(), selected.getHost());
    }

    /**
     * 选出该服务所有可用地址
     * @param clientName
     * @return
     */
    public static List<AlanServiceAddress> selectAvailableServers(String clientName) {
        @SuppressWarnings("rawtypes")
        DynamicServerListLoadBalancer lb = (DynamicServerListLoadBalancer) ClientFactory
                .getNamedLoadBalancer(clientName);
        List<Server> serverList = lb.getServerList(true);
        // List<Server> serverList = lb.getReachableServers();
        if (serverList.isEmpty()) {
            log.warn("服务{}没有可用地址", clientName);
            return Collections.emptyList();
        }
        log.debug("服务{}所有选择结果:{}", clientName, serverList);
        List<AlanServiceAddress> address = new ArrayList<AlanServiceAddress>();
        for (Server server : serverList) {
            address.add(new AlanServiceAddress(server.getPort(), server.getHost()));
        }
        return address;
    }
}
