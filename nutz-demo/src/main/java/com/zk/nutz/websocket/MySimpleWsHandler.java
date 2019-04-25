package com.zk.nutz.websocket;

import org.nutz.lang.util.NutMap;
import org.nutz.plugins.mvc.websocket.handler.SimpleWsHandler;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019-04-11 15:49
 */
public class MySimpleWsHandler extends SimpleWsHandler {
    public MySimpleWsHandler() {
        super("");
    }
    public void sayhi(NutMap req) {
        String name = req.getString("name");
        NutMap resp = new NutMap("action", "notify");
        resp.setv("msg", "hi, " + name);
        endpoint.sendJson(session.getId(), resp);
    }
}