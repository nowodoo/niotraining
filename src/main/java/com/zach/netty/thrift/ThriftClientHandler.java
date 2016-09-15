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
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //这里msg是bytebuf
        ctx.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).set(msg);
    }
}
