package com.chaining.iot.common.constans;

/**
 * 继电器枚举
 *
 * @author ningc
 */
public class RelayNum {
    /**
     * 执行的动作
     */
    public enum ActionEnum {
        /**
         * 暂停
         */
        PAUSE(2),
        /**
         * 开启
         */
        NO(1),
        /**
         * 关闭
         */
        OFF(0);

        private int value;

        ActionEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static ActionEnum toEnumValue(int value) {
            for (ActionEnum e : values()) {
                if (e.getValue() == (value)) {
                    return e;
                }
            }
            return null;
        }
    }

    /**
     * 继电器编号
     */
    public enum RelayNumEnum {
        /**
         * 房门
         */
        DOOR(1),
        /**
         * 灯
         */
        LIGHT(2),
        /**
         * 窗帘
         */
        CURTAIN(3),
        /**
         * 屏幕
         */
        SCREEN(4),
        /**
         * 投影仪
         */
        PROJECTOR(5),
        /**
         * 蜂鸣器
         */
        BUZZ(6),
        /**
         * 预留继电器接口
         */
        BACKUP_RELAY(7);
        private int value;

        RelayNumEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public static RelayNumEnum toEnumValue(int value) {
            for (RelayNumEnum e : values()) {
                if (e.getValue() == (value)) {
                    return e;
                }
            }
            return null;
        }

        public static boolean codeOf(int value) {
            for (RelayNumEnum d : values()) {
                if (d.getValue() == (value)) {
                    return true;
                }
            }
            return false;
        }
    }
}