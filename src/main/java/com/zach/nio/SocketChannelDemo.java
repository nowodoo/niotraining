package com.zach.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016-8-25.
 */
public class SocketChannelDemo {
    public static void startClient() throws Exception    {
        //和server的设置都是一样的
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8999));
        socketChannel.configureBlocking(false);


        String request = "hello, I am one of the client!";
        //创建一个新的buffer  就是直接将内容放到buf里面去
        ByteBuffer buf = ByteBuffer.wrap(request.getBytes("UTF-8"));

        //应该是channel将buffer直接写出去
        socketChannel.write(buf);


        //写入到server完成了之后就开始读取server返回的数据
        ByteBuffer rbuf = ByteBuffer.allocate(48);
        //这里应该解释为socketchannel进行读取的操作，然后将读取的内容放在buffer里面就是了。
        int size = socketChannel.read(rbuf);
        while (size > 0) {
            rbuf.flip();
            Charset charset = Charset.forName("UTF-8");
            System.out.println(charset.newDecoder().decode(rbuf));
            rbuf.clear();
            //再次读进来
            size = socketChannel.read(rbuf);
        }

        //清空原先的写入server的那个buffer就好了
        buf.clear();
        //等读取完毕，最后在关闭channel就好了。
        socketChannel.close();
        //想要debug的话，就需要在client sleep一下 然后server 就可以接收到信息了
//        Thread.sleep(50000);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("client started !");
        startClient();
        Thread.sleep(50000);
    }
}
