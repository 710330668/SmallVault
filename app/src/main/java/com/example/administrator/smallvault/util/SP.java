package com.example.administrator.smallvault.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SP {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SP(Context context, String file) {
        sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
        editor = sp.edit();
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
}
