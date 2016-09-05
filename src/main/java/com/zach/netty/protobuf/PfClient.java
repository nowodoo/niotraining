package com.zach.netty.protobuf;

import com.google.protobuf.ByteString;
import com.zach.netty.constants.CommonConstant;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.AttributeKey;

/**
 * Created by Administrator on 2016-8-30.
 */
public class PfClient {

    public static Bootstrap b;

    public static PooledByteBufAllocator allocator = new PooledByteBufAllocator();

    static {
        try {
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    //添加解码器（针对入站的数据进行解码）
                    ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                    ch.pipeline().addLast(new ProtobufDecoder(ResponseMsgProtoBuf.ResponseMsg.getDefaultInstance()));
                    ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                    ch.pipeline().addLast(new ProtobufEncoder());
                    ch.pipeline().addLast(new PfClientHandler());
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            workerGroup.shutdownGracefully();
        }
    }

    public static ResponseMsgProtoBuf.ResponseMsg startClient(RequestMsgProtoBuf.RequestMsg.Builder requestMsg) throws Exception {
        ChannelFuture f = b.connect("localhost", 8999).sync();
        f.channel().writeAndFlush(requestMsg);
        f.channel().closeFuture().sync();
        return (ResponseMsgProtoBuf.ResponseMsg)f.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).get();
    }

    public static void main(String[] args) {

    }
}
