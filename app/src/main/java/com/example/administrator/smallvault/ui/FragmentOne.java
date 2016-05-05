package com.example.administrator.smallvault.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.smallvault.R;
import com.github.mikephil.charting.charts.BarChart;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 跟踪fragment
 */
public class FragmentOne extends Fragment {
    @Bind(R.id.barChart)
    BarChart barChart;
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
        mView = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, mView);
        initBar();

        return mView;
    }

    private void initBar() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
