package com.chaining.iot.common.entity;

/**
 * TODO
 *
 * @program: wxstcgateway
 * @ClassName Command
 * @author: ning.chai@foxmail.com
 * @create: 2020-05-17 18:29
 * @Version 1.0
 **/
public class Command {
    private String unitId;
    private Integer action;
    private Integer relayNum;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Integer getRelayNum() {
        return relayNum;
    }

    public void setRelayNum(Integer relayNum) {
        this.relayNum = relayNum;
    }
}
