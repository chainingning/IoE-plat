package com.chaining.iot.common.constans;

/**
 * @author ning.chai@foxmail.com
 * @Description:
 * @date 2020/3/6 14:21
 */
public enum EnumMessageType {

    /**
     * 0x01
     * 终端请求注册
     */
    COMMAND1(1),
    /**
     * 0x08
     * 查询传感器
     */
    COMMAND8(8),
    /**
     *  0x82
     * 注册成功
     */
    COMMAND129(129),

    /**
     *  0x86
     * 接收到DTU数据
     */
    COMMAND134(134),

    /**
     *  0x87
     * 控制DTU电磁阀或者继电器
     */
    COMMAND135(135),
    /**
     *  0x88
     * 查询传感器
     */
    COMMAND136(136),
    /**
     * 0x06
     * 建立连接后续主动上报数据
     */
    COMMAND6(6),
    /**
     * 通用协议
     */
    GENERAL(99999);
    private int value;

    private EnumMessageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * int值转换成枚举值
     *
     * @param value
     * @return
     */
    public static EnumMessageType toEnumValue(int value) {
        for (EnumMessageType e : values()) {
            if (e.getValue() == (value)) {
                return e;
            }
        }
        return null;
    }

    /**
     * 根据值判断是否存在
     *
     * @param value
     * @return
     */
    public static boolean codeOf(int value) {
        for (EnumMessageType d : values()) {
            if (d.getValue() == (value)) {
                return true;
            }
        }
        return false;
    }

}
