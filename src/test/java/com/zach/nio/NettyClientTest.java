package com.zach.nio;

import com.zach.netty.DiscardClient;
import org.junit.Test;

/**
 * Created by Administrator on 2016-8-30.
 */
public class NettyClientTest {
    @Test
    public void test01() {
        //在这里模拟很多的线程
        Object obj = DiscardClient.startClient();
        System.out.println(obj);
    }
}
