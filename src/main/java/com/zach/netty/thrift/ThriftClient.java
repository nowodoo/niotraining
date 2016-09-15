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
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

import java.lang.reflect.Method;
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

    public static Object startClient(Object requestParam, Class responseClass) throws Exception {
        ChannelFuture f = b.connect("localhost", 8999).sync();

        f.channel().writeAndFlush(requestParam);
        f.channel().closeFuture().sync();


        //在client里面处理参数
        ByteBuf obj = (ByteBuf)f.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).get();
        TMemoryBuffer respBuffer = new TMemoryBuffer(1024);
        byte[] b = new byte[obj.readableBytes()];
        obj.readBytes(b);
        respBuffer.write(b);

        TProtocol respProt = new TBinaryProtocol(respBuffer);
        //在这里调用content类的read方法
        Method m = responseClass.getMethod("read", TProtocol.class);
        //这里的response其实就是content
        Object response = responseClass.newInstance();

        m.invoke(response, respProt);

        return response;
    }

    public static void main(String[] args) {

    }
}
