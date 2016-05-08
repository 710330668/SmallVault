package com.example.administrator.smallvault.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.smallvault.R;

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

        tixingjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

            }
        });
        xijiasifangqian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
