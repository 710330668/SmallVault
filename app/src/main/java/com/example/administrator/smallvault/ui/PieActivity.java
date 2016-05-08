package com.example.administrator.smallvault.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.smallvault.R;
import com.example.administrator.smallvault.util.ChartUtil;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PieActivity extends AppCompatActivity {

    @Bind(R.id.pieChart)
    PieChart mChart;
    private int month = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        ButterKnife.bind(this);

        mChart.setDescription("");

        mChart.setCenterTextTypeface(Typeface.DEFAULT);
        mChart.setCenterText(ChartUtil.getIntance().generateCenterText());
        mChart.setCenterTextSize(10f);
        mChart.setCenterTextTypeface(Typeface.DEFAULT);

        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);

        mChart.setData(ChartUtil.getIntance().generatePieData(this, month));
    }
}
