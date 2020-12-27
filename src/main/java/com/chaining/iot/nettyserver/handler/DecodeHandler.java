package com.chaining.iot.nettyserver.handler;

import com.chaining.iot.common.constans.IOTConst;
import com.chaining.iot.common.utils.ByteTools;
import com.chaining.iot.nettyserver.message.IMessageIn;
import com.chaining.iot.nettyserver.message.MessageInParser;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 解码handler
 *
 * @program: wxstcgateway
 * @ClassName DecodeHandler
 * @author: ning.chai@foxmail.com
 * @create: 2020-03-12 16:50
 * @Version 1.0
 **/
@Slf4j
public class DecodeHandler extends ByteToMessageDecoder {
    // 剩余长度
    private int remainLength = 0;
    //已读长度
    private int hasReadLength = 0;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println(in);
        //记录包头开始位置
        int beginReader = 0;

        //报文长度
        int readableLength = in.readableBytes();
        in.markReaderIndex();
        //防止socket字节流攻击、客户端传来的数据过大，这里需要对数据进行过滤掉
        if (readableLength >= IOTConst.ACCEPT_MAX_MSG_LENGTH) {
            in.skipBytes(readableLength);
            return;
        }

        if (1 == readableLength) {
            //如果是心跳报文
            byte[] b = new byte[readableLength];
            in.getBytes(in.readerIndex(), b, 0, readableLength);
            if (b[0] != IOTConst.HEARTBEAT) {
                System.out.println("啥都不是");
//                in.resetReaderIndex();
                in.clear();
                ctx.close();
                return;
            }
//            in.resetReaderIndex();
            System.out.println("是心跳哦");
//            byte[] bytes = new byte[readableLength];
//            in.readBytes(bytes);
//            out.add(bytes);
            in.clear();
            return;
        }


        byte[] bytes = new byte[readableLength];
        ByteBuf byteBuf = in.readBytes(bytes);
//        bytes = byteBuf.array();
        log.info("I rev msg session:{},data:{}", ctx.channel(), ByteTools.toHexString2(bytes));
        IMessageIn message = MessageInParser.toMessage(bytes);
        if (message != null) {
            out.add(message);
        }

        System.out.println(in);

    }
}
