package com.zach.netty.thrift;

import com.zach.netty.constants.CommonConstant;
import com.zach.netty.thrift.codec.ThriftClientEncoder;
import com.zach.utils.JsonUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.http.*;
import io.netty.util.AttributeKey;

import java.net.URI;
import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2016-8-30.
 */
public class ThriftClient {

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
                    ch.pipeline().addLast(new ThriftClientEncoder());
                    ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
                    ch.pipeline().addLast(new ThriftClientHandler());
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public static Object startClient(byte[] requestParam) throws Exception {
        ChannelFuture f = b.connect("localhost", 8999).sync();
        URI uri = new URI("http://127.0.0.1:8999");
        ByteBuf content = Unpooled.wrappedBuffer(JsonUtils.beanToJson(requestParam).getBytes("UTF-8"));
        DefaultFullHttpRequest req = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uri.toASCIIString(), content);
        req.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
        req.headers().set(HttpHeaderNames.HOST, "127.0.0.1");
        req.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
        req.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);

        f.channel().writeAndFlush(req);
        f.channel().closeFuture().sync();
        return f.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).get();
    }

    public static void main(String[] args) {

    }
}
