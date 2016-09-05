package com.zach.netty.http;


import com.google.protobuf.ByteString;
import com.zach.netty.media.Media;
import com.zach.netty.protobuf.*;
import com.zach.netty.protobuf.ResponseMsgProtoBuf;
import com.zach.utils.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016-8-30.
 */
public class HttpServerHandler extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest request = (FullHttpRequest) msg;
        ByteBuf buf = request.content();
        String req  = buf.toString(Charset.forName("UTF-8"));

        RequestParam requestParam = JsonUtils.jsonToBean(req, RequestParam.class);

        String cmd = requestParam.getCommand();
        System.out.println(cmd);

        //收到之后给个回应
        ctx.writeAndFlush(null);

        ctx.channel().close();
    }
}
