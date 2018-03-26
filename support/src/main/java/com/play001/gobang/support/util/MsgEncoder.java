package com.play001.gobang.support.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 消息编码
 */
public class MsgEncoder extends MessageToByteEncoder {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) {
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(msg);
            oos.flush();
            byte[] data = bos.toByteArray();
            //先写入长度
            out.writeInt(data.length);
            //写入内容
            out.writeBytes(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
