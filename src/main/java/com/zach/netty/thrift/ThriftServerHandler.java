package com.zach.netty.thrift;


import com.zach.netty.media.Media;
import com.zach.utils.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016-8-30.
 */
public class ThriftServerHandler extends ChannelHandlerAdapter {
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
        Object resp = Media.execute(requestParam);
        String jsonp = JsonUtils.beanToJson(resp);

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(jsonp.getBytes("UTF-8")));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
        response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
        ctx.writeAndFlush(response);

        ctx.channel().close();
    }
}
