package com.chaining.iot.nettyserver.message;

import com.chaining.iot.common.utils.ByteTools;

/**
 * 帧类型0x06
 * 建立连接后续主动上报数据
 *
 * @program: wxstcgateway
 * @ClassName MessageInFor06
 * @author: ning.chai@foxmail.com
 * @create: 2020-03-15 13:04
 * @Version 1.0
 **/
public class MessageInFor06 extends BaseMessageIn{

    /**
     * 温度
     */
    private int temp;

    /**
     * 湿度
     */
    private int hum;

    /**
     * 光照度
     */
    private int lus;

    /**
     * 房门状态
     * 1/0=开启/关闭
     */
    private String door;

    /**
     * 灯状态
     * 1/0=开启/关闭
     */
    private String lamp;

    /**
     * 窗帘状态
     * 2/1/0=关闭/开启/停止
     */
    private String curtain;

    /**
     * 幕布
     * 2/1/0=关闭/开启/停止
     */
    private String screen;

    /**
     * 投影仪
     * 1/0=开启/关闭
     */
    private String projector;

    /**
     * 蜂鸣器
     * 1/0=开启/关闭
     */
    private String buzzer;

    /**
     * 备用继电器
     * 1/0=开启/关闭
     */
    private String backupRelay;

    /**
     * 入侵检测
     * 1/0=开启/关闭
     */
    private String intrusionDetection;

    /**
     * 门禁检测
     * 1/0=开启/关闭
     */
    private String accessControl;

    /**
     * 窗户检测
     * 1/0=开启/关闭
     */
    private String windowDetection;

    /**
     * 预留检测
     * 1/0=开启/关闭
     */
    private String reservedDetection;
    @Override
    public IMessageIn parseMessage(byte[] userData) {
        MessageInFor06 messageInFor06 = new MessageInFor06();
        messageInFor06.setTemp(ByteTools.bytesToInt(new byte[]{userData[0],userData[1]}));
        messageInFor06.setHum(ByteTools.bytesToInt(new byte[]{userData[2],userData[3]}));
        messageInFor06.setLus(ByteTools.bytesToInt(new byte[]{userData[4],userData[5]}));
        messageInFor06.setDoor(ByteTools.toHexString(userData[6]));
        messageInFor06.setLamp(ByteTools.toHexString(userData[7]));
        messageInFor06.setCurtain(ByteTools.toHexString(userData[8]));
        messageInFor06.setScreen(ByteTools.toHexString(userData[9]));
        messageInFor06.setProjector(ByteTools.toHexString(userData[10]));
        messageInFor06.setBuzzer(ByteTools.toHexString(userData[11]));
        messageInFor06.setBackupRelay(ByteTools.toHexString(userData[12]));
        messageInFor06.setIntrusionDetection(ByteTools.toHexString(userData[13]));
        messageInFor06.setAccessControl(ByteTools.toHexString(userData[14]));
        messageInFor06.setWindowDetection(ByteTools.toHexString(userData[15]));
        messageInFor06.setReservedDetection(ByteTools.toHexString(userData[16]));
        return messageInFor06;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getHum() {
        return hum;
    }

    public void setHum(int hum) {
        this.hum = hum;
    }

    public int getLus() {
        return lus;
    }

    public void setLus(int lus) {
        this.lus = lus;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getLamp() {
        return lamp;
    }

    public void setLamp(String lamp) {
        this.lamp = lamp;
    }

    public String getCurtain() {
        return curtain;
    }

    public void setCurtain(String curtain) {
        this.curtain = curtain;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getProjector() {
        return projector;
    }

    public void setProjector(String projector) {
        this.projector = projector;
    }

    public String getBuzzer() {
        return buzzer;
    }

    public void setBuzzer(String buzzer) {
        this.buzzer = buzzer;
    }

    public String getBackupRelay() {
        return backupRelay;
    }

    public void setBackupRelay(String backupRelay) {
        this.backupRelay = backupRelay;
    }

    public String getIntrusionDetection() {
        return intrusionDetection;
    }

    public void setIntrusionDetection(String intrusionDetection) {
        this.intrusionDetection = intrusionDetection;
    }

    public String getAccessControl() {
        return accessControl;
    }

    public void setAccessControl(String accessControl) {
        this.accessControl = accessControl;
    }

    public String getWindowDetection() {
        return windowDetection;
    }

    public void setWindowDetection(String windowDetection) {
        this.windowDetection = windowDetection;
    }

    public String getReservedDetection() {
        return reservedDetection;
    }

    public void setReservedDetection(String reservedDetection) {
        this.reservedDetection = reservedDetection;
    }

    @Override
    public String toString() {
        return "MessageInFor06{" +
                "temp=" + temp +
                ", hum=" + hum +
                ", lus=" + lus +
                ", door='" + door + '\'' +
                ", lamp='" + lamp + '\'' +
                ", curtain='" + curtain + '\'' +
                ", screen='" + screen + '\'' +
                ", projector='" + projector + '\'' +
                ", buzzer='" + buzzer + '\'' +
                ", backupRelay='" + backupRelay + '\'' +
                ", intrusionDetection='" + intrusionDetection + '\'' +
                ", accessControl='" + accessControl + '\'' +
                ", windowDetection='" + windowDetection + '\'' +
                ", reservedDetection='" + reservedDetection + '\'' +
                '}';
    }
}
