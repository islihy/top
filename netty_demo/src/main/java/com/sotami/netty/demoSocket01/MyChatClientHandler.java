package com.sotami.netty.demoSocket01;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/2/3 4:46 AM
 */
public class MyChatClientHandler extends SimpleChannelInboundHandler<String>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }
}
