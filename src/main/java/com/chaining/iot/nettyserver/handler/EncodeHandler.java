package com.chaining.iot.nettyserver.handler;

import com.chaining.iot.common.constans.IOTConst;
import com.chaining.iot.common.entity.Connection;
import com.chaining.iot.common.utils.ByteTools;
import com.chaining.iot.nettyserver.message.IMessageOut;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 编码
 *
 * @program: wxstcgateway
 * @ClassName EncodeHandler
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-19 14:42
 * @Version 1.0
 **/
@Slf4j
public class EncodeHandler extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (msg == null) {
            return;
        }
        IMessageOut retMsg = (IMessageOut) msg;
        byte[] data = retMsg.toByte();
        Connection conn = ctx.channel().attr(IOTConst.NETTY_CHANNEL_KEY).get();
        String identityId = conn.getIdentityId();
        out.writeBytes(data);
        log.info("Send HEX DATA:{} to UNIT_ID:{} CHANNEL: {}", ByteTools.toHexString2(data),identityId,ctx.channel());
    }
}
