package com.chaining.iot.wxserver.model;

import java.util.Date;

/**
 * 小程序用户
 *
 * @program: wxstcgateway
 * @ClassName User
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-12 22:45
 * @Version 1.0
 **/
public class User {

    private Integer id;

    private String userName;

    private String password;

    private Date createTime;

    private String status;

    private String delFlag;

    private Date loginDate;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", status='" + status + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", loginDate=" + loginDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
