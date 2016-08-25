package com.zach.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Administrator on 2016-8-25.
 */
public class SocketChannelDemo {
    public static void startClient() throws Exception    {
        //和server的设置都是一样的
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8999));
        socketChannel.configureBlocking(false);


        String request = "hello ServerSocketChannel";
        //创建一个新的buffer  就是直接将内容放到buf里面去
        ByteBuffer buf = ByteBuffer.wrap(request.getBytes("UTF-8"));

        //然后将buffer写到channel去
        socketChannel.write(buf);
        socketChannel.close();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("client started !");
        startClient();
    }
}
