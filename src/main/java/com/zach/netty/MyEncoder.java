package com.zach.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.Delimiters;

import java.util.List;

/**
 * Created by Administrator on 2016-8-31.
 */
public class MyEncoder extends ByteToMessageCodec<String> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, String s, ByteBuf byteBuf) throws Exception {

        //这里针对出站的数据要进行编码
        byteBuf.writeBytes(s.getBytes("UTF-8"));

        //使用的形式（最后追加一个换行符）
        byteBuf.writeBytes(Delimiters.lineDelimiter()[0]);
    }

    /**
     * 这个方法是用来解码的
     * @param channelHandlerContext
     * @param byteBuf
     * @param list
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {


    }
}
