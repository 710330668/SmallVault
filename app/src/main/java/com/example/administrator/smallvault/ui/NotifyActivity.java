package com.example.administrator.smallvault.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.smallvault.R;
import com.example.administrator.smallvault.util.SP;

/**
 * Created by Administrator on 2016/5/10.
 */
public class NotifyActivity extends Activity {

    private Button btAllMoney;
    private Button btOneTypeMoney;
    private EditText et_money;
    private EditText et_paywhere;
    private TextView tv_paywhere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        init();
        initListener();
    }

    private void initListener() {
        btAllMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater().cloneInContext(NotifyActivity.this);
                View layout = inflater.inflate(R.layout.item_dialog, null);
                et_money = (EditText) layout.findViewById(R.id.et_money);
                et_paywhere = (EditText) layout.findViewById(R.id.et_paywhere);
                tv_paywhere=(TextView) layout.findViewById(R.id.tv_paywhere);
                et_paywhere.setVisibility(View.GONE);
                tv_paywhere.setVisibility(View.GONE);
                new AlertDialog.Builder(NotifyActivity.this).setView(layout).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SP sph = SP.getInstance(NotifyActivity.this,"password");
                        sph.setAllMoney(et_money.getText().toString());
                        Toast.makeText(NotifyActivity.this, "保存完成", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });
        btOneTypeMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater().cloneInContext(NotifyActivity.this);
                View layout = inflater.inflate(R.layout.item_dialog, null);
                et_money = (EditText) layout.findViewById(R.id.et_money);
                et_paywhere = (EditText) layout.findViewById(R.id.et_paywhere);
                tv_paywhere=(TextView) layout.findViewById(R.id.tv_paywhere);
                et_paywhere.setVisibility(View.GONE);
                tv_paywhere.setVisibility(View.GONE);
                new AlertDialog.Builder(NotifyActivity.this).setView(layout).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SP sph = SP.getInstance(NotifyActivity.this,"password");
                        sph.setOneTypeMoney(et_money.getText().toString());
                        Toast.makeText(NotifyActivity.this, "保存完成", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });
    }

    private void init() {
        btAllMoney=(Button)this.findViewById(R.id.bt_allmoney);
        btOneTypeMoney=(Button)this.findViewById(R.id.bt_onetypemoney);
    }
}
