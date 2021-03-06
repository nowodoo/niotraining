package com.zach.netty;

import com.zach.netty.constants.CommonConstant;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
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
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.AttributeKey;

/**
 * Created by Administrator on 2016-8-30.
 */
public class DiscardClient {

    //优化client
    public static Bootstrap b;

    //用来往handler里面传值
    public static PooledByteBufAllocator allocator = new PooledByteBufAllocator();

    static {
        //客户端只有worker线程
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
                    ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
                    ch.pipeline().addLast(new StringDecoder());

                    //添加编码器
                    ch.pipeline().addLast(new MyEncoder());

                    ch.pipeline().addLast(new DiscardClientHandler());
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            workerGroup.shutdownGracefully();
        }
    }

    public static Object startClient(Object obj) throws Exception {
        //下面表示先链接，然后在等待   这里f不是静态的
        ChannelFuture f = b.connect("localhost", 8999).sync(); // (5);
        //在这里设置一个值，然后去另一个地方获取这个值
        //在这里需要做的就是将数据写到handler里面去
//        f.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).set(obj);

        //使用另一种方式将数据写到handler里面去
//        ByteBuf buf = allocator.buffer().writeBytes(((String)obj).getBytes("UTF-8"));
//        f.channel().writeAndFlush(buf);

        //添加了编码器和解码器之后的写法
        f.channel().writeAndFlush("hello");

        f.channel().closeFuture().sync();

        //在这里获取比较特别的一个特殊的一个对象的一个属性（就是在handler中的属性）
        return f.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).get();
    }

    public static void main(String[] args) {

    }
}
