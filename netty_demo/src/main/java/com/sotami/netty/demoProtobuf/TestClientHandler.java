package com.sotami.netty.demoProtobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/2/8 5:35 PM
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        MyDataInfo.MyMessage message;

        int randomInt = new Random().nextInt(3);
        if (0==randomInt){
            message = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.PersionType)
                    .setPersion(MyDataInfo.Persion.newBuilder().setName("张三").setAge(13).setAddress("北京")).build();
        }else if (1==randomInt){
            message = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder().setName("单身狗").setAge(30)).build();
        }else {
            message = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder().setName("小白").setCity("北京")).build();
        }


//        MyDataInfo.Persion persion= MyDataInfo.Persion.newBuilder().setName("张三").setAge(12).setAddress("北京").build();

        ctx.writeAndFlush(message);
    }
}
