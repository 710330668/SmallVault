package com.example.administrator.smallvault.db.entity;

/**
 * Created by Administrator on 2016/5/5.
 */
public class ShouRu {

    private String time;
    private String shouru;

    public ShouRu() {
    }

    public ShouRu(String time, String shouru) {
        this.time = time;
        this.shouru = shouru;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getShouru() {
        return shouru;
    }

    public void setShouru(String shouru) {
        this.shouru = shouru;
    }


}
