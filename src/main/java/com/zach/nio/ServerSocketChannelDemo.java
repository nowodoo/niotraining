package com.zach.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016-8-25.
 */
public class ServerSocketChannelDemo {
    public static void start() throws IOException {
        //创建（通过静态的方法）
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //指定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8999));
        //配置阻塞
        serverSocketChannel.configureBlocking(false);


        //首先不用selector，先用while循环
        while (true) {
            //先看一下有没有channel过来
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (null != socketChannel) {
                //使用上一节课创建的byteBuffer
                ByteBuffer buf = ByteBuffer.allocate(48);
                int size = socketChannel.read(buf);

                //只要是有channel过来，就会读出来。
                while (size > 0) {
                    //前面是写的，现在是读
                    buf.flip();
                    //这里就是借用第一节课的内容
                    Charset charset = Charset.forName("UTF-8");
                    System.out.println(charset.newDecoder().decode(buf));
                    size = socketChannel.read(buf);
                }
                //不要占用内存，直接清空
                buf.clear();

                //收到数据之后将数据写回去（写给客户端）
                ByteBuffer response = ByteBuffer.wrap("client, hello!".getBytes("UTF-8"));
                socketChannel.write(response);
                //写完之后要关掉
                response.clear();

                //读完之后关掉channel要
                socketChannel.close();
            }
        }
    }


    public static void main(String[] args) throws IOException {
        System.out.println("server start!");
        start();
    }
}
