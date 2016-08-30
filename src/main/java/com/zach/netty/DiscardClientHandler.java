package com.zach.netty;

import com.zach.netty.constants.CommonConstant;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

/**
 * Created by Administrator on 2016-8-30.
 */
public class DiscardClientHandler extends ChannelHandlerAdapter {
    //实现下面两个比较重要的方法

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //使用客户端写数据
        ByteBuf buf = ctx.alloc().buffer().writeBytes("hello netty server".getBytes("UTF-8"));
        //下面才是真正的将数据写出去，否则要等到这个通道满了才可以溢出
        ctx.writeAndFlush(buf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //在这里读取server传回的数据
        ByteBuf buf = (ByteBuf)msg;

        StringBuilder stringBuilder = new StringBuilder();


        while (buf.isReadable()) {
            stringBuilder.append((char) buf.readByte());
        }

        //在这里设置一个值，然后在client的前段获取这个值
        ctx.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).set(stringBuilder.toString());
    }
}
