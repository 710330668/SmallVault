package com.example.administrator.smallvault.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.smallvault.R;
import com.example.administrator.smallvault.util.SP;

/**
 * Created by Administrator on 2016/5/10.
 */
public class XuJiaInfoActivity extends Activity {

    private TextView tv_money;
    private TextView tv_paywhere;
    private TextView tv_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_paylist);
        init();
        initDate();
    }

    private void initDate() {
        SP sph = SP.getInstance(XuJiaInfoActivity.this,"password");
        tv_money.setText("支出金额:"+sph.getXiuJiaMoney());
        tv_paywhere.setText("支出理由:"+sph.getXiuJiaWhere());
        tv_time.setText(sph.getXiuJiaTime());
    }

    private void init() {
        tv_money=(TextView)this.findViewById(R.id.tv_money);
        tv_paywhere=(TextView)this.findViewById(R.id.tv_paywhere);
        tv_time=(TextView)this.findViewById(R.id.tv_time);

    }
}
