package com.example.administrator.smallvault.ui;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.smallvault.R;
import com.example.administrator.smallvault.db.PayContentProvider;
import com.example.administrator.smallvault.util.ChartUtil;
import com.example.administrator.smallvault.util.DateTools;
import com.github.mikephil.charting.charts.BarChart;

public class FragmentTwo extends Fragment {

    private View mView;
    private TextView txtView;
    private BarChart mChart;
    private MainActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_two, container, false);
        txtView = (TextView) mView.findViewById(R.id.txtView);
        initBar();
        return mView;
    }

    private void initBar() {
        mChart = (BarChart) mView.findViewById(R.id.chart1);
        mChart.setOnTouchListener(null);
        ChartUtil.getIntance().initBarChart(mChart);
        ChartUtil.getIntance().setData(mChart, mActivity, 5);
    }

    private void initData() {
        Log.e("##### Date ######", DateTools.getDateYYYYMMDD());

        ContentValues values = new ContentValues();
        values.put("time", "20160512");
        values.put("month", DateTools.getDateYYYYMMDD().substring(0, 6));
        values.put("food", 15);
        values.put("shopping", 45);
        values.put("play", 10);
        values.put("medicine", 15);
        values.put("other", 15);
        getActivity().getContentResolver().insert(
                PayContentProvider.CONTENT_URI_ZHICHU, values);

        ContentValues values2 = new ContentValues();
        values2.put("time", DateTools.getDateYYYYMMDD());
        values2.put("shouru", 150);
        for (int i = 0; i < 10; i++) {
            getActivity().getContentResolver().insert(
                    PayContentProvider.CONTENT_URI_SHOURU, values2);
        }
    }

    public void getData() {
        Cursor cursor = getActivity().getContentResolver().query(
                PayContentProvider.CONTENT_URI_ZHICHU, null, null, null, null);
        Log.e("test ", "count=" + cursor.getCount());
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(cursor.getColumnIndex("time"));
            Log.e("test ", "time: " + name);
            cursor.moveToNext();
        }
        cursor.close();
    }
}
