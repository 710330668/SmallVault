package com.example.administrator.smallvault.ui;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
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
import com.example.administrator.smallvault.ui.view.MyMarkerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class FragmentTwo extends Fragment implements
        OnChartValueSelectedListener {

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
        initData();
        // getData();
        initBar();
        return mView;
    }

    private void initBar() {
        mChart = (BarChart) mView.findViewById(R.id.chart1);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDescription("");
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);

        mChart.setDrawGridBackground(false);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MyMarkerView mv = new MyMarkerView(mActivity,
                R.layout.custom_marker_view);

        // define an offset to change the original position of the marker
        // (optional)
        // mv.setOffsets(-mv.getMeasuredWidth() / 2, -mv.getMeasuredHeight());

        // set the marker to the chart
        mChart.setMarkerView(mv);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);
        l.setTypeface(Typeface.DEFAULT);
        l.setYOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        XAxis xl = mChart.getXAxis();
        xl.setTypeface(Typeface.DEFAULT);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(Typeface.DEFAULT);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(30f);
        leftAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)

        mChart.getAxisRight().setEnabled(false);

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            xVals.add((i + 1990) + "");
        }

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();

        float mult = 100 * 1000f;

        for (int i = 0; i < 20; i++) {
            float val = (float) (Math.random() * mult) + 3;
            yVals1.add(new BarEntry(val, i));
        }

        for (int i = 0; i < 20; i++) {
            float val = (float) (Math.random() * mult) + 3;
            yVals2.add(new BarEntry(val, i));
        }

        for (int i = 0; i < 20; i++) {
            float val = (float) (Math.random() * mult) + 3;
            yVals3.add(new BarEntry(val, i));
        }

        BarDataSet set1, set2, set3;

        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) mChart.getData().getDataSetByIndex(1);
            set3 = (BarDataSet) mChart.getData().getDataSetByIndex(2);
            // set1.setYVals(yVals1);
            // set2.setYVals(yVals2);
            // set3.setYVals(yVals3);
            // mChart.getData().setXVals(xVals);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create 3 datasets with different types
            set1 = new BarDataSet(yVals1, "Company A");
            set1.setColor(Color.rgb(104, 241, 175));
            set2 = new BarDataSet(yVals2, "Company B");
            set2.setColor(Color.rgb(164, 228, 251));
            set3 = new BarDataSet(yVals3, "Company C");
            set3.setColor(Color.rgb(242, 247, 158));

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            dataSets.add(set2);
            dataSets.add(set3);

            BarData data = new BarData(xVals, dataSets);
            data.setGroupSpace(80f);
            data.setValueTypeface(Typeface.DEFAULT);

            mChart.setData(data);
        }

        mChart.invalidate();
    }

    private void initData() {

        ContentValues values = new ContentValues();
        values.put("time", "2016-05-05");
        values.put("food", 15);
        values.put("shopping", 45);
        values.put("play", 15);
        values.put("medicine", 15);
        values.put("other", 15);
        for (int i = 0; i < 10; i++) {
            getActivity().getContentResolver().insert(
                    PayContentProvider.CONTENT_URI_ZHICHU, values);
        }

        ContentValues values2 = new ContentValues();
        values2.put("time", "2016-05-05");
        values2.put("shouru", 15);
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
            String table = cursor
                    .getString(cursor.getColumnIndex("table_name"));
            String name = cursor.getString(cursor.getColumnIndex("time"));
            Log.e("test", "table_name:" + table);
            Log.e("test ", "time: " + name);
            cursor.moveToNext();
        }
        cursor.close();

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
