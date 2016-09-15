package com.zach.netty.thrift;


import com.hzins.thrift.demo.ThriftRequest;
import com.zach.netty.media.Media;
import com.zach.utils.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016-8-30.
 */
public class ThriftServerHandler extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //byteBuffer 转换为 thriftRequest
        ByteBuf buf = (ByteBuf)msg;

        //表示直接去内存中去读
        TMemoryBuffer buffer = new TMemoryBuffer(1024);

        //数据是二进制的
        if (buf.hasArray()) {
            buffer.write(buf.array());
        } else {
            byte[] dst = new byte[buf.readableBytes()];  //根据实际实际拥有的创建大小
            //这个地方也是一个关键，就只如何将一个bytebu写到平常的byte里面去
            buf.readBytes(dst);  //将buf的数据写到dst里面去。
            buffer.write(dst);
        }


        //这里就是整个架构的关键
        TProtocol prot = new TBinaryProtocol(buffer);
        ThriftRequest req = new ThriftRequest();
        req.read(prot);
        Object resp = Media.execute(req);


        //最后将结果返回
        ctx.writeAndFlush(resp);
    }
}
