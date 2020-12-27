package com.chaining.iot.nettyserver.message;

import com.chaining.iot.common.constans.EnumMessageType;
import com.chaining.iot.common.exception.BizException;
import com.chaining.iot.common.utils.ByteTools;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 报文解析成对应消息，工厂模式
 *
 * @program: wxstcgateway
 * @ClassName MessageInParser
 * @author: ning.chai@foxmail.com
 * @create: 2020-03-15 12:58
 * @Version 1.0
 **/
@Slf4j
public class MessageInParser {

    private static Map<Integer, IMessageIn> customParserMap = new HashMap<Integer,IMessageIn>();

    static {
        //握手包
        customParserMap.put(EnumMessageType.COMMAND1.getValue(),new MessageInFor01());
        //建立连接后续主动上报数据
        customParserMap.put(EnumMessageType.COMMAND6.getValue(),new MessageInFor06());

        customParserMap.put(EnumMessageType.COMMAND8.getValue(),new MessageInFor08());
    }

    public static IMessageIn toMessage(byte[] data){
        //检测数据格式是否正确
//        if (data.length <= IOTConst.ACCEPT_MIN_MSG_LENGTH) {
//            throw new BizException("invalid message[HEADER_ERROR]");
//        }
        //检验包头0x7B
        if (data[0]!= 0x7B) {
            throw new BizException("invalid message[HEADER_ERROR]");
        }
        //校验数据长度
        //校验CRC
//        if (!checkCRC16(data)) {
//            throw new BizException("invalid message[CRC_ERROR]");
//        }

        //帧类型
        int messageType = ByteTools.byteToInt(data[1]);

        //数据内容
        byte[] userData = new byte[data.length - 7];
        System.arraycopy(data, 4, userData, 0, data.length-7);

        EnumMessageType type = EnumMessageType.toEnumValue(messageType);
        IMessageIn customParser = getMessageParser(messageType);
        if (null == customParser) {
            throw  new BizException("invalid message[NOT_SUPPORT_MSG]:"+messageType);
        }
        customParser.setMessageType(type);

        IMessageIn messageIn = customParser.parseMessage(userData);
        messageIn.setMessageType(type);

        return messageIn;
    }

    private static IMessageIn getMessageParser(int messageType){
        return customParserMap.get(messageType);
    }

    protected static boolean checkCRC16(byte[] data){
        byte[] checkdata = new byte[data.length - 4];
        System.arraycopy(data, 2, checkdata, 0, data.length - 4);
        byte[] CRC16 = ByteTools.CRC16(checkdata);
        return (data[data.length-2] == CRC16[0] && data[data.length-1] == CRC16[1]);
    }
}
