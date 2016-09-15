package com.zach.nio.thrift;

import com.hzins.thrift.demo.Content;
import com.hzins.thrift.demo.ThriftRequest;
import com.zach.netty.thrift.ThriftClient;
import io.netty.buffer.ByteBuf;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;
import org.junit.Test;

/**
 * Created by Administrator on 2016-9-15.
 */
public class ThriftTest {
    @Test
    public void testThrift() throws Exception {
        //组装thriftRequest
        //转换为byte[]

        ThriftRequest request = new ThriftRequest();
        request.setCommand("ThriftGetEmailByContent");

        Content content = new Content();
        content.setId(12);
        content.setPhone("99999");

        TMemoryBuffer buffer = new TMemoryBuffer(1024);
        TProtocol prot = new TBinaryProtocol(buffer);
        content.write(prot);

        request.setRequestParam(buffer.getArray());
        buffer.close();


        //真正调用客户端
        Content c = (Content)ThriftClient.startClient(request, Content.class);

        //将原先的处理逻辑放在client进行操作.
        System.out.println(c.getPhone());
    }
}
