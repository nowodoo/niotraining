package com.zach.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2016-8-28.
 */
public class AioServerDemo {

    //添加一个变量，防止主线程停止了之后程序没法响应了
    private static CountDownLatch latch;

    public static void startServer() throws Exception {
        AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
        asynchronousServerSocketChannel.bind(new InetSocketAddress(8999));
        //初始化变量
        latch = new CountDownLatch(1);

        //使用匿名内部类（这里的accept方法表示是有连接进来了，就是这么简单，连接上了时候就会直接触发complete这个方法）
        asynchronousServerSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            /**
             * 这个方法就是有请求过来了，我们要处理它
             * @param result
             * @param attachment
             */
            @Override
            public void completed(AsynchronousSocketChannel result, Void attachment) {
                try {
                    operate(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * 要是连接处理失败了就会触发这个方法
             * @param exc
             * @param attachment
             */
            @Override
            public void failed(Throwable exc, Void attachment) {
                //只要有异常的情况才会countDown
                latch.countDown();
            }

        });

        //在这里一直等待，等到为0的时候再次开启。
        latch.await();
    }

    /**
     * 腹泻方法的具体的实现方法
     */
    private static void operate(AsynchronousSocketChannel ch) throws Exception {
        ByteBuffer buf = ByteBuffer.allocate(48);
        //注意这里和原先相比还有一个get方法。
        int size = ch.read(buf).get();

        while (size > 0) {
            buf.flip();
            Charset charset = Charset.forName("UTF-8");
            charset.newDecoder().decode(buf).toString();
            size = ch.read(buf).get();
        }

        //最后在操作完成之后要记得关闭这个channel
        ch.close();
    }

    public static void main(String[] args) throws Exception {
        startServer();
    }
}
