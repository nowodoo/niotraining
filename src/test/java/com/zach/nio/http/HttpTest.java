package com.zach.nio.http;

import com.zach.netty.http.HttpClient;
import com.zach.netty.http.RequestParam;
import org.junit.Test;

/**
 * Created by Administrator on 2016-9-6.
 */

public class HttpTest {
    @Test
    public void testHttpNettyClient() throws Exception {
        RequestParam requestParam = new RequestParam();
        requestParam.setCommand("httpGetEmailByUser");
        requestParam.setParameter("{\"username\":\"1\"}");
//        requestParam.setParameter("helloString");
        Object obj = HttpClient.startClient(requestParam);
        System.out.println(obj);
    }
}
