package com.zk.nutz.websocket;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.plugins.mvc.websocket.AbstractWsEndpoint;
import org.nutz.plugins.mvc.websocket.NutWsConfigurator;
import org.nutz.plugins.mvc.websocket.WsHandler;

import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019-04-11 15:46
 */
@ServerEndpoint(value = "/websocket", configurator= NutWsConfigurator.class)
@IocBean
public class MyWebsocket extends AbstractWsEndpoint {
    @Override
    public WsHandler createHandler(Session session, EndpointConfig config) {
        return new MySimpleWsHandler();
    }
}
