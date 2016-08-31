package com.zach.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;


/**
 * Created by Administrator on 2016-8-30.
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //添加解码器
        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));

        //经过这里的转换之后，在handler里面过来的消息就是一个字符串了
        ch.pipeline().addLast(new StringDecoder());

        //添加自己的编码器
        ch.pipeline().addLast(new MyEncoder());

        //channel里面有一个管道，管道追加一个handler，用其来追加真正的业务
        ch.pipeline().addLast(new DiscardServerHandler());
        //这里的handler就像是在j2ee中的handler，可以直接加上很多


    }
}
