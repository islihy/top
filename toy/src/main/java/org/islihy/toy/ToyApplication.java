package org.islihy.toy;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.islihy.toy.netty.NettyHttpServer;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/13 3:47 PM
 */
public class ToyApplication {
    static Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws Exception{
        logger.error("12345");
        HelperLoader.init();
        NettyHttpServer.start();
    }
}
