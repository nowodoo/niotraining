package com.zach.netty;

import com.zach.netty.constants.CommonConstant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.AttributeKey;

/**
 * Created by Administrator on 2016-8-30.
 */
public class DiscardClient {
    public static Object startClient() {
        //客户端只有worker线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            Bootstrap b = new Bootstrap();
            b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new DiscardClientHandler());
                }
            });

            //下面表示先链接，然后在等待
            ChannelFuture f = b.connect("localhost", 8999).sync();
            f.channel().closeFuture().sync();

            //在这里获取比较特别的一个特殊的一个对象的一个属性（就是在handler中的属性）
            return f.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }

        return null;
    }

    public static void main(String[] args) {

    }
}
