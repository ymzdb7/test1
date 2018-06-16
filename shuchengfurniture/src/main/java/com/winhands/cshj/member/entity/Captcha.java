package com.winhands.cshj.member.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author guojun
 */
public class Captcha implements Serializable{
    private String phoneNum;
    private String captcha;
    private Timestamp updateTime;
    private int todayCount;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public int getTodayCount() {
        return todayCount;
    }

    public void setTodayCount(int todayCount) {
        this.todayCount = todayCount;
    }
}
