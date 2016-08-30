package com.zach.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {

    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        //这里有两个线程(boss线程主要是监听有没有新的线程接入进来)
        //boss线程要是监听到有了就直接交给worker线程处理，然后work线程就直接消失 work线程也会有接下来的性能的问题需要处理
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //将两个线程设置进去
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    //指定channel
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyChannelInitializer())  //指定handler  下面有两个类去实现，一个是正常的类另一个是真正的实现逻辑
                    .option(ChannelOption.SO_BACKLOG, 128)     //请求线程全忙之后，还允许多少个线程进入
                    .childOption(ChannelOption.SO_KEEPALIVE, true);  //表示在长连接的情况下 是不是需要心跳检测

            //绑定端口
            ChannelFuture f = b.bind(port).sync();
            //在这里可以添加监听
            //绑定完端口需要在这里一直等待。
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //用于关闭两个线程
            bossGroup.close();
            workerGroup.close();
        }

    }


    public static void main(String[] args) throws Exception {
        int port = 8999;
        new DiscardServer(port).run();
    }
}
