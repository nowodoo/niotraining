package com.zach.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016-8-28.
 */
public class ClientThread extends Thread {

    private Selector selector;


    public ClientThread(Selector selector) {
        super();
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            //这里的selector和server端的selector是很相似的
            //总之在这一刻就是为了接受从服务端发来的数据的
            while (selector.select() > 0) {
                for (SelectionKey key : selector.selectedKeys()) {
                    //获取channel
                    SocketChannel socketChannel = ((SocketChannel)key.channel());
                    ByteBuffer buf = ByteBuffer.allocate(48);
                    int size = socketChannel.read(buf);
                    while (size > 0) {
                        //TODO
                        buf.flip();
                        Charset charset = Charset.forName("UTF-8");
                        System.out.println(charset.newDecoder().decode(buf).toString());
                    }
                    //处理完一个channel就要释放这个channel
                    selector.selectedKeys().remove(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
