package com.example.administrator.smallvault.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.smallvault.R;
import com.example.administrator.smallvault.ui.ui.LocusPassWordView;
import com.example.administrator.smallvault.util.Md5Utils;
import com.example.administrator.smallvault.util.SP;

/**
 * Created by Administrator on 2016/5/8.
 */
public class TruePassWordActivity extends Activity{

    private LocusPassWordView mPwdView;
    private Context mContext;
    private TextView multi_tv_token_time_hint;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrat_password);
        mPwdView = (LocusPassWordView) this.findViewById(R.id.mPassWordView);
        multi_tv_token_time_hint=(TextView)this.findViewById(R.id.multi_tv_token_time_hint);
        init();
    }

    private void init() {
        mContext = getApplicationContext();
        multi_tv_token_time_hint.setText("请确认您的密码");
        mPwdView.setOnCompleteListener(new LocusPassWordView.OnCompleteListener() {
            @Override
            public void onComplete(String mPassword) {
                SP sph = SP.getInstance(TruePassWordActivity.this,"password");
                String pwd = sph.getPassword();
                Md5Utils md5 = new Md5Utils();
                boolean passed = false;
                if (pwd.length() == 0) {
                    sph.setPassword(md5.toMd5(mPassword, ""));
                    Toast.makeText(mContext, mContext.getString(R.string.pwd_setted), Toast.LENGTH_LONG).show();
                    return;
                } else {
                    String encodedPwd = md5.toMd5(mPassword, "");
                    if (encodedPwd.equals(pwd)) {
                        passed = true;
                    } else {
                        mPwdView.markError();
                    }
                }

                if (passed) {
                    Log.d("hcj", "password is correct!");
                    Toast.makeText(mContext, mContext.getString(R.string.pwd_correct), Toast.LENGTH_LONG).show();
                    sph = SP.getInstance(TruePassWordActivity.this,"password");
                    if(sph.getIsXujia().equals("0")){
                        Intent intent=new Intent();
                        intent.setClass(TruePassWordActivity.this,PayListActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent=new Intent();
                        intent.setClass(TruePassWordActivity.this,XuJiaInfoActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}
