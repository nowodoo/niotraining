package com.zach.netty.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by Administrator on 2016-9-11.
 */
//表示这里只接收 text类型的消息
public class ChatRoomHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //因为有很多的线程，所以这里需要一个线程池
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String content = msg.text();
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            //表示其他的channel才会收到信息，本身的的话就直接关闭
            if (channel != incoming) {
                channel.writeAndFlush(ctx.channel().remoteAddress()+":"+content);
            } else {
                channel.writeAndFlush("服务器返回!");
            }
        }
    }

    /**
     * 链接刚刚进来的时候会进入这个方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //只要有一个channel进来，将会通知所有的channel
        for (Channel channel : channels) {
            channel.writeAndFlush(ctx.channel().remoteAddress()+":已经进入聊天室!");
        }

        //进入的新channel加入channel池
        channels.add(ctx.channel());
    }


    /**
     * a connection disconnected
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //tell others that this channel leaved !
        for (Channel channel : channels) {
            channel.writeAndFlush(ctx.channel().remoteAddress()+":已经进入聊天室!");
        }

        channels.remove(ctx.channel());
    }
}
