package com.play001.gobang.client.util;

import com.play001.gobang.support.entity.msg.client.ClientBaseMsg;
import com.play001.gobang.support.entity.msg.server.ServerBaseMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * 消息解码
 */
public class ServerMsgDecoder extends ByteToMessageDecoder  {


    private final Logger logger = Logger.getLogger(ServerMsgDecoder.class);
    /**
     *
     * @param in 消息前四个字节为消息长度
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //消息头长度, 四个字节
        final byte HEAD_LENGTH = 4;

        if(in.readableBytes() < HEAD_LENGTH){
            logger.debug("消息长度小于4");
            //消息头未传完则什么都不做
            return ;
        }
        //保存开始位置
        in.markReaderIndex();
        //消息实际长度
        int length = in.readInt();
        if(length <= 0){
            //一般情况不会出现这种情况, 出现了异常关闭连接
            logger.error("消息头数据小于0");
            ctx.close();
            return ;
        }

        if(in.readableBytes() < length){
            logger.debug("长度不够");
            //读到的消息体长度如果小于我们传送过来的消息长度，则resetReaderIndex. 这个配合markReaderIndex使用的。把readIndex重置到mark的地方
            in.resetReaderIndex();
            // 缓存当前剩余的buffer数据，等待剩下数据包到来
            return;
        }
        //读取数据
        byte[] data = new byte[length];
        in.readBytes(data);
        //反序列化
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
        ServerBaseMsg msg = (ServerBaseMsg)ois.readObject();
        out.add(msg);
    }
}
