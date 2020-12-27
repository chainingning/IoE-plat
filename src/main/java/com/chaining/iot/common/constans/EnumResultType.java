package com.chaining.iot.common.constans;

public enum EnumResultType {

	
	RESULT_ONLINE(1), //设备在线
	RESULT_OFFLINE(2),//设备离线
	RESULT_REPORT(90);//所有设备状态

	private int value;

	private EnumResultType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	/**
	 * 值转成枚举类型
	 * 
	 * @param value
	 * @return
	 */
	public static EnumResultType toEnumValue(int value) {
		for (EnumResultType d : values()) {
			if (d.getValue() == (value)) {
				return d;
			}
		}
		throw new IllegalArgumentException("Enum Conversion Error:" + value);
	}

	/**
	 * 根据值判断是否存在
	 * 
	 * @param value
	 * @return
	 */
	public static boolean codeOf(int value) {
		for (EnumResultType d : values()) {
			if (d.getValue() == (value)) {
				return true;
			}
		}
		return false;
	}
}
