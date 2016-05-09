package com.example.administrator.smallvault.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import com.example.administrator.smallvault.db.DBHelper;
import com.example.administrator.smallvault.db.entity.Zhichu;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/6.
 */
public class ChartUtil {

    private static volatile ChartUtil intance = null;

    private ChartUtil() {
    }

    public static ChartUtil getIntance() {
        if (intance == null) {
            synchronized (ChartUtil.class) {
                if (intance == null) {
                    intance = new ChartUtil();
                }
            }
        }
        return intance;
    }

    public SpannableString generateCenterText() {
        SpannableString s = new SpannableString("月结\n5月份");
        s.setSpan(new RelativeSizeSpan(2f), 0, 6, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 6, s.length(), 0);
        return s;
    }

    public PieData generatePieData(Context context, int month) {
        ArrayList<Zhichu> list = DBHelper.getIntance(context).queryZhichu();
        if (list != null) {
            ArrayList<Entry> entries1 = new ArrayList<>();
            ArrayList<String> xVals = new ArrayList<>();

            xVals.add("餐饮");
            xVals.add("购物");
            xVals.add("娱乐");
            xVals.add("医疗");
            xVals.add("其他");

            int food = 0;
            for (Zhichu entity : list) {
                food = food + Integer.valueOf(entity.getFood());
            }
            entries1.add(new Entry((float) food, 0));

            int shopping = 0;
            for (Zhichu entity : list) {
                shopping = shopping + Integer.valueOf(entity.getShopping());
            }
            entries1.add(new Entry((float) shopping, 1));

            int play = 0;
            for (Zhichu entity : list) {
                play = play + Integer.valueOf(entity.getPlay());
            }
            entries1.add(new Entry((float) play, 2));

            int yiliao = 0;
            for (Zhichu entity : list) {
                yiliao = yiliao + Integer.valueOf(entity.getMedicine());
            }
            entries1.add(new Entry((float) yiliao, 3));

            int other = 0;
            for (Zhichu entity : list) {
                other = other + Integer.valueOf(entity.getOther());
            }
            entries1.add(new Entry((float) other, 4));

            // for (int i = 0; i < count; i++) {
            // xVals.add("entry" + (i + 1));
            // entries1.add(new Entry((float) 10, i));
            // }

            PieDataSet ds1 = new PieDataSet(entries1, "月结:" + month + "月份");
            ds1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            ds1.setSliceSpace(2f);
            ds1.setValueTextColor(Color.WHITE);
            ds1.setValueTextSize(12f);

            PieData d = new PieData(xVals, ds1);
            d.setValueTypeface(Typeface.DEFAULT);

            return d;
        } else {
            return null;
        }
    }

    public static String[] mMonths = new String[] { "娱乐", "购物", "餐饮", "医疗", "其他" };

    public static final int[] MATERIAL_COLORS = { rgb("#2ecc71"),
        rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db") };

    public static int rgb(String hex) {
        int color = (int) Long.parseLong(hex.replace("#", ""), 16);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;
        return Color.rgb(r, g, b);
    }

    public void initBarChart(BarChart mChart) {
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.setDescription("");

        mChart.setMaxVisibleValueCount(60);

        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(Typeface.DEFAULT);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);

        YAxisValueFormatter custom = new MyYAxisValueFormatter();

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(Typeface.DEFAULT);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(Typeface.DEFAULT);
        rightAxis.setLabelCount(8, false);
        rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
    }
}
