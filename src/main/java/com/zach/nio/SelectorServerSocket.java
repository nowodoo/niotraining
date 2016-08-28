package com.zach.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016-8-28.
 */
public class SelectorServerSocket {
    public static void startServer() throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8999));
        serverSocketChannel.configureBlocking(false);

        //使用selector
        Selector selector = Selector.open(); //打开的方式是一样的
        //将selector直接注册到chanenl里, ACCEPT 表示一个可接收的
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            //需要注意，每一个链接只会select一次
            int select = selector.select();

            //表示通道已经接入进来了
            if (select > 0) {
                //下面开始读取了，直接就是遍历出来
                for (SelectionKey key : selector.selectedKeys()) {
                    if (key.isAcceptable()) {
                        //下面就是buffer将channel中的数据放到buffer中来就可以了

                        //首先获取这个select中的chanenl, 在这里进行强制传唤
                        SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
                        ByteBuffer buf = ByteBuffer.allocate(40);
                        int size = socketChannel.read(buf);  //这里其实是channel将数据写在buf中
                        while (size >0) {
                            //这里由写的模式转换到读的模式，因为size是要写完了就再次读取。
                            buf.flip();
                            Charset charset = Charset.forName("UTF-8");
                            System.out.println(charset.newDecoder().decode(buf).toString());
                            size = socketChannel.read(buf);
                        }
                        //首先清空
                        buf.clear();

                        //将一个结果反馈给用户
                        ByteBuffer response = ByteBuffer.wrap("alraedy get your request(from server)!".getBytes("UTF-8"));
                        socketChannel.write(response);
                        socketChannel.close();

                        //下面需要将这个连接人工的remove掉
                        selector.selectedKeys().remove(key);
                    }
                }

            }
        }


    }

    public static void main(String[] args) throws Exception {
        //启动server
        startServer();
    }
}
