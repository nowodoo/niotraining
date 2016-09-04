package com.zach.netty.protobuf;


import com.google.protobuf.ByteString;
import com.zach.netty.media.Media;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Administrator on 2016-8-30.
 */
public class PfServerHandler extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //因为编码器做的就是转换为这个对象，所以这里需要在强转回来
        RequestMsgProtoBuf.RequestMsg requestMsg = (RequestMsgProtoBuf.RequestMsg)msg;
        String cmd = requestMsg.getCmd();

        //这里就是业务方法，业务方法处理了之后直接返回给客户端
        Object response = Media.execute(requestMsg);

        //收到之后给个回应
        ctx.writeAndFlush(response);

        ctx.channel().close();
    }
}
