package com.zach.netty;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Administrator on 2016-8-30.
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //这里就是处理管道的
        ByteBuf buf = (ByteBuf)msg;
        while (buf.isReadable()) {
            System.out.print((char) buf.readByte());
            System.out.flush();
        }

        //读到了然后再次写回去
        ByteBuf wbuf =  ctx.alloc().buffer().writeBytes("您收到了吗?".getBytes("UTF-8"));
        ctx.writeAndFlush(wbuf);
    }
}
