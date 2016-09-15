package com.zach.nio.thrift;

import com.hzins.thrift.demo.Content;
import com.hzins.thrift.demo.ThriftRequest;
import com.zach.netty.thrift.ThriftClient;
import io.netty.buffer.ByteBuf;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;
import org.junit.Test;

import java.nio.ByteBuffer;

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
        ByteBuf obj = (ByteBuf)ThriftClient.startClient(reqBuffer.getArray());
        reqBuffer.close();


        //处理返回值
        TMemoryBuffer respBuffer = new TMemoryBuffer(1024);  //这里就是借助于中间buffer来做事情，java就是这样，就只存在很多的中间变量去协助中间过程的进行，其实主要的就是一个方法。

        //因为是内存来的，不能直接obj.array() 还需要处理一下
        byte[] b = new byte[obj.readableBytes()];
        obj.readBytes(b); //这里是直接读到b中去
        respBuffer.write(b);

//        respBuffer.write(obj.array());
        TProtocol respProt = new TBinaryProtocol(respBuffer);
        Content c = new Content();
        c.read(respProt);

        System.out.println(c.getPhone());
    }
}
