package com.zach.nio;

import com.zach.netty.DiscardClient;
import org.junit.Test;

/**
 * Created by Administrator on 2016-8-30.
 */
public class NettyClientTest {
    @Test
    public void test01() {
        Object obj = null;
        try {
            //在这里模拟很多的线程
            obj = DiscardClient.startClient("hello server");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println(obj);
    }

    @Test
    public void test02() throws Exception {
        for (int i = 0; i < 1000; i++) {
            long startTime = System.currentTimeMillis();
            Object obj = DiscardClient.startClient("hello" + i+"---");
            if (obj == null) {
                throw new RuntimeException("");
            }
            System.out.println(obj);
            long endTime = System.currentTimeMillis();
            System.out.println("cycle:" + i + "time:" + (endTime - startTime) + "ms");


        }
    }
}
