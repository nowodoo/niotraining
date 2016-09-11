package com.zach.netty.thrift;

import com.zach.netty.constants.CommonConstant;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpContent;
import io.netty.util.AttributeKey;

import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016-8-30.
 */
public class ThriftClientHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpContent) {
            System.out.println("yes");
            HttpContent httpContent = (HttpContent)msg;
            ByteBuf buf = httpContent.content();
            ctx.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).set(buf.toString(Charset.forName("UTF-8")));
            ctx.channel().close();
        }

    }
}
