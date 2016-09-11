package com.zach.netty.thrift;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class ThriftServer implements ApplicationListener<ContextRefreshedEvent>,Ordered {

    private int port;

    public ThriftServer(int port) {
        this.port = port;
    }

    public ThriftServer() {
    }


    public void run(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    //指定channel
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ThriftChannelInitializer())  //指定handler  下面有两个类去实现，一个是正常的类另一个是真正的实现逻辑
                    .option(ChannelOption.SO_BACKLOG, 128)     //请求线程全忙之后，还允许多少个线程进入
                    .childOption(ChannelOption.SO_KEEPALIVE, true);  //表示在长连接的情况下 是不是需要心跳检测

            //绑定端口
            ChannelFuture f = b.bind(port).sync();
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
        new ThriftServer(port).run(port);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        int port = 8999;
        try {
            run(port);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
