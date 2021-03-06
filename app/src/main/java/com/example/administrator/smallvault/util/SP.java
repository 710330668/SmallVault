package com.example.administrator.smallvault.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SP {
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;
    private static SP msp;

    public static SP getInstance(Context context,String file) {
        if(sp==null){
            synchronized (SP.class){
                msp=new SP();
                sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
                editor = sp.edit();
            }
        }
        return msp;
    }

    // 是否第一次登陆
    public void setIsFirst(String IsFirst) {
        editor.putString("IsFirst", IsFirst);
        editor.commit();
    }

    public String getIsFirst() {
        return sp.getString("IsFirst", "0");
    }

    // 公共FTP账号
    public void setPublicFtpAcount(String publicFtpAcount) {
        editor.putString("PublicFtpAcount", publicFtpAcount);
        editor.commit();
    }

    public String getPublicFtpAcount() {
        return sp.getString("PublicFtpAcount", "");
    }
    //九宫格密码
    public void setPassword(String password) {
        editor.putString("password", password);
        editor.commit();
    }

    public String getPassword() {
        return sp.getString("password", "");
    }
    //四位密码
    public void setSiWeiPassword(String password) {
        editor.putString("siweipassword", password);
        editor.commit();
    }

    public String getSiWeiPassword() {
        return sp.getString("siweipassword", "");
    }
    //虚假信息
    public void setXiuJiaMoney(String money) {
        editor.putString("xiujiamoney", money);
        editor.commit();
    }

    public String getXiuJiaMoney() {
        return sp.getString("xiujiamoney", "");
    }
    public void setXiuJiaWhere(String where) {
        editor.putString("xiujiawhere", where);
        editor.commit();
    }

    public String getXiuJiaWhere() {
        return sp.getString("xiujiawhere", "");
    }
    public void setXiuJiaTime(String time) {
        editor.putString("xiujiatime", time);
        editor.commit();
    }

    public String getXiuJiaTime() {
        return sp.getString("xiujiatime", "");
    }
    public void setIsXujia(String isXujia) {
        editor.putString("isXujia", isXujia);
        editor.commit();
    }

    public String getIsXujia() {
        return sp.getString("isXujia", "");
    }
    public void setAllMoney(String allMoney) {
        editor.putString("allMoney", allMoney);
        editor.commit();
    }

    public String getAllMoney() {
        return sp.getString("allMoney", "");
    }
    public void setOneTypeMoney(String oneTypeMoney) {
        editor.putString("oneTypeMoney", oneTypeMoney);
        editor.commit();
    }

    public String getOneTypeMoney() {
        return sp.getString("oneTypeMoney", "");
    }

}
