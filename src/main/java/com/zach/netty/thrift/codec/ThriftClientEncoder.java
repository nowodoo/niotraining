package com.zach.netty.thrift.codec;

import com.hzins.thrift.demo.ThriftRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

/**
 * Created by Administrator on 2016-9-12.
 */
    public class ThriftClientEncoder extends MessageToByteEncoder<Object>{

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        ThriftRequest request = (ThriftRequest)msg;

        TMemoryBuffer reqBuffer = new TMemoryBuffer(1024);
        TProtocol reqProt = new TBinaryProtocol(reqBuffer);
        request.write(reqProt);

        out.writeBytes(reqBuffer.getArray());
        out.writeBytes(Delimiters.lineDelimiter()[0]);
        reqBuffer.close();
    }
}
