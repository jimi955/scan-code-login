package com.xiaoxin.login.bean;


import java.io.Serializable;

public class CodeBean implements Serializable {

    // 二维码唯一标识
    private String qrCodeValue;

    // 二维码状态
    private Integer qrCodeStatus;

    // 用户登录身份证
    private String token;

    // 二维码生成时间
    private Long createTime;

    // 员工工号
    private String workerNo;

    public String getWorkerNo() {
        return workerNo;
    }

    public void setWorkerNo(String workerNo) {
        this.workerNo = workerNo;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getQrCodeValue() {
        return qrCodeValue;
    }

    public void setQrCodeValue(String qrCodeValue) {
        this.qrCodeValue = qrCodeValue;
    }

    public Integer getQrCodeStatus() {
        return qrCodeStatus;
    }

    public void setQrCodeStatus(Integer qrCodeStatus) {
        this.qrCodeStatus = qrCodeStatus;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}