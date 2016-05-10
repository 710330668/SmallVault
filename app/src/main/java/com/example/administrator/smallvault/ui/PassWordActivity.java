package com.example.administrator.smallvault.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.smallvault.R;
import com.example.administrator.smallvault.util.SP;

/**
 * Created by Administrator on 2016/5/9.
 */
public class PassWordActivity extends Activity {

    private TextView tv_jiugongge;
    private TextView tv_siwei;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        init();
        initListener();
    }

    private void initListener() {
        tv_jiugongge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到九宫格密码
                
                SP sph = SP.getInstance(PassWordActivity.this, "password");
                String pwd = sph.getPassword();
                if(pwd.length()>0){
                    Intent intent=new Intent();
                    intent.setClass(PassWordActivity.this,TruePassWordActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent=new Intent();
                    intent.setClass(PassWordActivity.this,ScratPassWordActivity.class);
                    startActivity(intent);
                }
            }
        });
        tv_siwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PassWordActivity.this, SiWeiPassWordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        tv_jiugongge=(TextView)this.findViewById(R.id.tv_jiugongge);
        tv_siwei=(TextView)this.findViewById(R.id.tv_siwei);

    }
}
