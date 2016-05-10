package com.example.administrator.smallvault.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.administrator.smallvault.R;
import com.example.administrator.smallvault.ui.ui.SiWeiPasswordView;
import com.example.administrator.smallvault.util.SP;

/**
 * Created by Administrator on 2016/5/9.
 */
public class SiWeiPassWordActivity extends Activity{

    private SiWeiPasswordView mSiWeiPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siwei);
        init();
    }

    private void init() {
        mSiWeiPasswordView=(SiWeiPasswordView)this.findViewById(R.id.password);
        SP sph = SP.getInstance(SiWeiPassWordActivity.this,"password");
        if(!TextUtils.isEmpty(sph.getSiWeiPassword())){
            mSiWeiPasswordView.setTruePassword(sph.getSiWeiPassword());
        }
        mSiWeiPasswordView.setOnPasswordInputListener(new SiWeiPasswordView.OnPasswordInputListener() {
            @Override
            public void onInputFinished(String password) {
                SP sph = SP.getInstance(SiWeiPassWordActivity.this,"password");
                sph.setSiWeiPassword(password);
                Toast.makeText(SiWeiPassWordActivity.this, "密码设置完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPasswordCheckSuccess() {
                Toast.makeText(SiWeiPassWordActivity.this, "密码验证正确", Toast.LENGTH_SHORT).show();
                SP sph = SP.getInstance(SiWeiPassWordActivity.this,"password");
                if(sph.getIsXujia().equals("0")){
                    Intent intent=new Intent();
                    intent.setClass(SiWeiPassWordActivity.this,PayListActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent=new Intent();
                    intent.setClass(SiWeiPassWordActivity.this,XuJiaInfoActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onPasswordCheckFailed() {
                Toast.makeText(SiWeiPassWordActivity.this, "密码验证失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
