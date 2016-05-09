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
    //密码
    public void setPassword(String password) {
        editor.putString("password", password);
        editor.commit();
    }

    public String getPassword() {
        return sp.getString("password", "");
    }
}
