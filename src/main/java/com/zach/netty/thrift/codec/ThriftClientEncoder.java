package com.zach.netty.thrift.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Administrator on 2016-9-12.
 */
public class ThriftClientEncoder extends MessageToByteEncoder<ByteBuf>{

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        //在这里out输出的就是编码完成的
        out.writeBytes(msg);
        out.writeBytes(Delimiters.lineDelimiter()[0]);
    }
}
