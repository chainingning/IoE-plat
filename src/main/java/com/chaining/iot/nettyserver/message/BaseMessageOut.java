package com.chaining.iot.nettyserver.message;
import com.chaining.iot.common.constans.EnumMessageType;
import com.chaining.iot.common.utils.ByteTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ning.chai@foxmail.com
 * @Description:
 * @date 2020/3/6 18:02
 */
public abstract class BaseMessageOut implements IMessageOut {
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/*
	 * 报文类型
	 */
	private EnumMessageType messageType;
	
	public abstract byte[] getUserData();
	
	
	@Override
	public byte[] toByte(){
		byte[] userData = getUserData();
		byte[] data = new byte[userData.length+7];
		int i = 0;
		int msgType = messageType.getValue();
        byte[] msgLength = ByteTools.intToBytes(userData.length+7);
		byte[] type = ByteTools.intToBytes(msgType);
		data[i++] = (byte)0x7B;
		data[i++] = type[0];
		data[i++] = msgLength[0];
		data[i++] = msgLength[1];
		if(userData!=null && userData.length>0) {
			System.arraycopy(userData, 0, data, 4, userData.length);
		}
		setCRC16(data);
		return data;
	}
	
	
	protected void setCRC16(byte[] data){
		byte[] checkdata = new byte[data.length - 4];
		System.arraycopy(data, 1, checkdata, 0, data.length - 4);
		byte[] CRC16 = ByteTools.CRC16(checkdata);
		data[data.length-3] = CRC16[0];
		data[data.length-2] = CRC16[1];
		data[data.length-1] = (byte)0x7B;
	}
	
	public EnumMessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(EnumMessageType messageType) {
		this.messageType = messageType;
	}
	
	
}
