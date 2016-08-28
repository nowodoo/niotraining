package com.zach.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by Administrator on 2016-8-28.
 */
public class SelectorSocketChannelDemo {
    public static void startClient() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        //解决一个无法链接的现象，问题的根源就在于首先要建立好了链接然后在进行设置，这个原先是放在后面的，所以是失败的。
        socketChannel.connect(new InetSocketAddress("localhost", 8999));
        //主要是设置为非阻塞
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        //只有在读的时候才用这个selector
        socketChannel.register(selector, SelectionKey.OP_READ);


        ByteBuffer byteBuffer = ByteBuffer.wrap("我是客户端".getBytes("UTF-8"));
        socketChannel.write(byteBuffer);
        byteBuffer.clear();

        //客户端直接开一个线程去处理，处理完成之后再去做其他的事情
        new ClientThread(selector).start();
    }

    public static void main(String[] args) throws IOException {
        startClient();
    }
}
