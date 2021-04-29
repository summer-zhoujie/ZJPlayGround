package com.example.stepcount.beans;

import java.io.Serializable;

/**
 * Created by zhangxiaohai on 2018/4/17.
 */

public class StepData implements Serializable {
    private int date;
    private int step;
    private int time;
    // 记录今天为止启动的步数
    private int totalOfBoot;
    private int hasReboot;


    public int getHasReboot() {
        return hasReboot;
    }

    public void setHasReboot(int hasReboot) {
        this.hasReboot = hasReboot;
    }


    public int getTotalOfBoot() {
        return totalOfBoot;
    }

    public void setTotalOfBoot(int totalOfBoot) {
        this.totalOfBoot = totalOfBoot;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
