package com.zach.netty.websocket;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

/**
 * Created by Administrator on 2016-8-30.
 */
public class WebsocketServerHandler extends ChannelHandlerAdapter {
    private WebSocketServerHandshaker handshaker;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //如果是http请求怎么处理
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest req = (FullHttpRequest) msg;
            //不是websocket
            if (!req.decoderResult().isSuccess() || req.headers().get("Upgrade").equals("websocket")) {
                //输出bad response
                DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
                //表示写客户端没有成功
                if (response.status().code() != 200) {
                    ByteBuf buf = Unpooled.copiedBuffer("exception !", CharsetUtil.UTF_8);
                    response.content().writeBytes(buf);
                    buf.release();
                }
                //处理错误完成， 写出
                ctx.writeAndFlush(response);
            }else {
                //正确的连接输出
                WebSocketServerHandshakerFactory handshakerFactory = new WebSocketServerHandshakerFactory("ws://localhost:8999/websocket", null, false);
                handshaker = handshakerFactory.newHandshaker(req);
                if (null == handshaker) {
                    WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
                }
                handshaker.handshake(ctx.channel(), req);

            }


        }else if (msg instanceof WebSocketFrame) {
            //如果是一个关闭的消息
            if (msg instanceof CloseWebSocketFrame) {
                handshaker.close(ctx.channel(), (CloseWebSocketFrame) msg);
            }
            //要是消息类型
            if (msg instanceof TextWebSocketFrame) {
                String req = ((TextWebSocketFrame) msg).text();

                //将消息返回
                ctx.writeAndFlush(new TextWebSocketFrame(req+"服务端返回数据"));
            }

        }
    }
}
