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

        TMemoryBuffer buffer = new TMemoryBuffer(1024);
        TProtocol prot = new TBinaryProtocol(buffer);

        ThriftRequest req = new ThriftRequest();



        Object resp = Media.execute(msg);

        ctx.channel().close();
    }
}
