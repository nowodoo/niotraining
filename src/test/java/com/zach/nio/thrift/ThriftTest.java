package com.zach.nio.thrift;

import com.hzins.thrift.demo.Content;
import com.hzins.thrift.demo.ThriftRequest;
import com.zach.netty.thrift.ThriftClient;
import org.apache.thrift.TException;
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

        TMemoryBuffer reqBuffer = new TMemoryBuffer(1024);
        TProtocol reqProt = new TBinaryProtocol(reqBuffer);
        request.write(reqProt);

        //真正调用客户端
        ThriftClient.startClient(reqBuffer.getArray());
        reqBuffer.close();

    }
}
