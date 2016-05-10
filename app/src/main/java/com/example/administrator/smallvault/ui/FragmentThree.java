package com.example.administrator.smallvault.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.smallvault.R;
import com.example.administrator.smallvault.db.DBHelper;
import com.example.administrator.smallvault.db.entity.SiFangQian;
import com.example.administrator.smallvault.util.SP;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FragmentThree extends Fragment {

    @Bind(R.id.tixingjie)
    TextView tixingjie;
    @Bind(R.id.yueshouzhi)
    TextView yueshouzhi;
    @Bind(R.id.sifangqian)
    TextView sifangqian;
    @Bind(R.id.xijiasifangqian)
    TextView xijiasifangqian;
    private View mView;
    private MainActivity mActivity;
    private EditText et_money;
    private EditText et_paywhere;
    private SiFangQian mSiFangQian;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_three, container, false);
        ButterKnife.bind(this, mView);
        mSiFangQian=new SiFangQian();
        tixingjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(mActivity,NotifyActivity.class);
                startActivity(intent);
            }
        });

        yueshouzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jump(PieActivity.class);
            }
        });
        sifangqian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SP sph = SP.getInstance(mActivity,"password");
                sph.setIsXujia("0");
                Intent intent=new Intent();
                intent.setClass(mActivity,PassWordActivity.class);
                startActivity(intent);
            }
        });
        sifangqian.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                LayoutInflater inflater = mActivity.getLayoutInflater().cloneInContext(mActivity);
                View layout = inflater.inflate(R.layout.item_dialog, null);
                et_money = (EditText) layout.findViewById(R.id.et_money);
                et_paywhere = (EditText) layout.findViewById(R.id.et_paywhere);
                new AlertDialog.Builder(mActivity).setView(layout).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSiFangQian.setMoney(et_money.getText().toString());
                        mSiFangQian.setPaywhere(et_paywhere.getText().toString());
                        Date curDate = new Date(System.currentTimeMillis());
                        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                        mSiFangQian.setTime(format.format(curDate));
                        DBHelper.getIntance(mActivity).insert(mSiFangQian);
                        Toast.makeText(mActivity, "保存完成", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                return false;
            }
        });
        xijiasifangqian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SP sph = SP.getInstance(mActivity,"password");
                sph.setIsXujia("1");
                Intent intent = new Intent();
                intent.setClass(mActivity, PassWordActivity.class);
                startActivity(intent);

            }
        });
        xijiasifangqian.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                LayoutInflater inflater = mActivity.getLayoutInflater().cloneInContext(mActivity);
                View layout = inflater.inflate(R.layout.item_dialog, null);
                et_money = (EditText) layout.findViewById(R.id.et_money);
                et_paywhere = (EditText) layout.findViewById(R.id.et_paywhere);
                new AlertDialog.Builder(mActivity).setView(layout).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SP sph = SP.getInstance(mActivity,"password");
                        sph.setXiuJiaMoney(et_money.getText().toString());
                        sph.setXiuJiaWhere(et_paywhere.getText().toString());
                        Date curDate = new Date(System.currentTimeMillis());
                        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                        sph.setXiuJiaTime(format.format(curDate));
                        Toast.makeText(mActivity, "保存完成", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                return false;
            }
        });
        return mView;
    }

    private void jump(Class<?> Activity) {
        Intent intent = new Intent(mActivity, Activity);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
