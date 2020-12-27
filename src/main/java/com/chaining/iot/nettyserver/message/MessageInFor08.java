package com.chaining.iot.nettyserver.message;

import com.chaining.iot.common.utils.ByteTools;

import java.util.Arrays;
import java.util.List;

/**
 * 帧类型0x08
 * 查询传感器数据
 *
 * @program: wxstcgateway
 * @ClassName MessageInFor06
 * @author: ning.chai@foxmail.com
 * @create: 2020-03-15 13:04
 * @Version 1.0
 **/
public class MessageInFor08 extends BaseMessageIn{

    /**
     * 继电器编号
     */
    private int relayCode;

    /**
     * 设备状态
     */
    private String unitStatus;

    /**
     * 检测值
     */
    private int detectionValue;

//    /**
//     * 温度
//     */
//    private int temp;
//
//    /**
//     * 湿度
//     */
//    private int hum;
//
//    /**
//     * 光照度
//     */
//    private int lus;
//
//    /**
//     * 入侵检测触发次数
//     */
//    private String intrusionDetection;
//
//    /**
//     * 门禁检测触发次数
//     */
//    private String accessControlSum;
//
//    /**
//     * 窗户检测触发次数
//     */
//    private String windowDetectionSum;
//
//    /**
//     * 预留检测触发次数
//     */
//    private String reservedDetectionSum;

    @Override
    public IMessageIn parseMessage(byte[] userData) {
        MessageInFor08 messageInFor08 = new MessageInFor08();
        messageInFor08.setUnitStatus("00");
        messageInFor08.setRelayCode(ByteTools.byteToInt(userData[0]));
        //解析2B设备
        List<String> uintType1 = Arrays.asList("01", "02", "03");
        //解析1B设备
        List<String> uintType2 = Arrays.asList("04", "05", "06","07");

        if("01".equals(ByteTools.toHexString(userData[1]))){
            if (uintType1.contains(ByteTools.toHexString(userData[0]))){
                messageInFor08.setDetectionValue(ByteTools.bytesToInt(new byte[]{userData[2],userData[3]}));
            }

            if (uintType2.contains(ByteTools.toHexString(userData[0]))){
                messageInFor08.setDetectionValue(ByteTools.byteToInt(userData[2]));
            }
        }
//        messageInFor08.setTemp(ByteTools.bytesToInt(new byte[]{userData[0],userData[1]}));
//        messageInFor08.setHum(ByteTools.bytesToInt(new byte[]{userData[2],userData[3]}));
//        messageInFor08.setLus(ByteTools.bytesToInt(new byte[]{userData[4],userData[5]}));
//        messageInFor08.setIntrusionDetection(ByteTools.toHexString(userData[6]));
//        messageInFor08.setAccessControlSum(ByteTools.toHexString(userData[7]));
//        messageInFor08.setWindowDetectionSum(ByteTools.toHexString(userData[8]));
//        messageInFor08.setReservedDetectionSum(ByteTools.toHexString(userData[9]));
        return messageInFor08;
    }

    public int getRelayCode() {
        return relayCode;
    }

    public void setRelayCode(int relayCode) {
        this.relayCode = relayCode;
    }

    public String getUnitStatus() {
        return unitStatus;
    }

    public void setUnitStatus(String unitStatus) {
        this.unitStatus = unitStatus;
    }

    public int getDetectionValue() {
        return detectionValue;
    }

    public void setDetectionValue(int detectionValue) {
        this.detectionValue = detectionValue;
    }

    @Override
    public String toString() {
        return "MessageInFor08{" +
                "relayCode=" + relayCode +
                ", unitStatus='" + unitStatus + '\'' +
                ", detectionValue=" + detectionValue +
                '}';
    }
}
