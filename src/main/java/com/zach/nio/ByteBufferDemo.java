package com.zach.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016-8-23.
 */
public class ByteBufferDemo {


    //主要通过读取文件内容，写到ByteBuffer里，然后再从ByteBuffer对象中获取数据，显示到控制台

    public static void readFile(String fileName) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            int size = fileChannel.read(byteBuffer);
            while (size > 0) {
                //把ByteBuffer从写模式，转变成读取模式
                byteBuffer.flip();
//				while(byteBuffer.hasRemaining()){
                // 在这里使用getChar是不对的，因为这里是二进制流
//					System.out.print(byteBuffer.get());
//				}
                Charset charset = Charset.forName("UTF-8");
                System.out.println(charset.newDecoder().decode(byteBuffer).toString());
                byteBuffer.clear();
                size = fileChannel.read(byteBuffer);

            }

            fileChannel.close();
            randomAccessFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
