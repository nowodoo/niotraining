package com.zach.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


/**
 * Created by Administrator on 2016-8-30.
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //channel里面有一个管道，管道追加一个handler，用其来追加真正的业务
        ch.pipeline().addLast(new DiscardServerHandler());
        //这里的handler就像是在j2ee中的handler，可以直接加上很多


    }
}
