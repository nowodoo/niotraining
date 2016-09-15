package com.zach.netty.thrift;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016-9-15.
 */
public class ThriftServerEncode extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        //将返回的数据进行编码
        Method method = msg.getClass().getMethod("write", TProtocol.class);
        TMemoryBuffer buffer = new TMemoryBuffer(1024);
        TProtocol prot = new TBinaryProtocol(buffer);
        method.invoke(msg, prot);
        byte[] b = buffer.getArray();
        out.writeBytes(b);
        out.writeBytes(Delimiters.lineDelimiter()[0]);
    }
}
