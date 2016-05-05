package com.example.administrator.smallvault.db.entity;

/**
 * Created by Administrator on 2016/5/5.
 */
public class Zhichu {

    private String time;
    private String food;
    private String shopping;
    private String play;
    private String medicine;
    private String other;

    public Zhichu() {
    }

    public Zhichu(String time, String food, String shopping, String play, String medicine, String other) {
        this.time = time;
        this.food = food;
        this.shopping = shopping;
        this.play = play;
        this.medicine = medicine;
        this.other = other;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getShopping() {
        return shopping;
    }

    public void setShopping(String shopping) {
        this.shopping = shopping;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
