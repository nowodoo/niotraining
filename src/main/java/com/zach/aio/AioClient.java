package com.zach.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2016-8-28.
 */
public class AioClient {
    public static void startClient() throws Exception {
        AsynchronousSocketChannel asynchronousSocketChannel = AsynchronousSocketChannel.open();
        //因为这里都是异步的，所有首先要确认已经链接上了服务端，这样才可以去写数据。
        Future<?> future = asynchronousSocketChannel.connect(new InetSocketAddress("localhost", 8999));
        future.get();

        //这个技术最常用的了
        ByteBuffer buf = ByteBuffer.wrap("hello aio server".getBytes("UTF-8"));
        asynchronousSocketChannel.write(buf);

        buf.clear();
        asynchronousSocketChannel.close();
    }

    public static void main(String[] args) throws Exception {
        startClient();
    }
}
