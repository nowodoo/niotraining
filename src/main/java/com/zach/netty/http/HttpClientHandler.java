package com.zach.netty.http;

import com.zach.netty.constants.CommonConstant;
import com.zach.netty.protobuf.*;
import com.zach.netty.protobuf.ResponseMsgProtoBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

/**
 * Created by Administrator on 2016-8-30.
 */
public class HttpClientHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        com.zach.netty.protobuf.ResponseMsgProtoBuf.ResponseMsg responseMsg = (ResponseMsgProtoBuf.ResponseMsg)msg;
        ctx.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).set(responseMsg);
        ctx.channel().close();
    }
}
