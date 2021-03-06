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
        //在这里获取数据
//        String req = (String)ctx.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).get();
//        ByteBuf buf = ctx.alloc().buffer().writeBytes(req.getBytes("UTF-8"));
//        //下面才是真正的将数据写出去，否则要等到这个通道满了才可以溢出
//        ctx.writeAndFlush(buf);
    }

    /**
     * 这个是用来接收server传回来的数据的
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //在这里读取server传回的数据
//        ByteBuf buf = (ByteBuf)msg;
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        //获取server传来的数值
//        while (buf.isReadable()) {
//            stringBuilder.append((char) buf.readByte());
//        }

        //在这里设置一个值，然后在client的前段获取这个值
        ctx.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).set(msg.toString());
        //解决阻碍的问题
        ctx.channel().close();

        //在这里关闭，server是知道的
        ctx.close();
    }
}
