package com.example.administrator.smallvault.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.smallvault.R;
import com.example.administrator.smallvault.db.DBHelper;
import com.example.administrator.smallvault.db.entity.Zhichu;
import com.example.administrator.smallvault.ui.view.XCArcMenuView;
import com.example.administrator.smallvault.util.ChartUtil;
import com.example.administrator.smallvault.util.DateTools;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * 跟踪fragment
 */
public class FragmentOne extends Fragment {
    private View mView;
    private MainActivity mActivity;
    private BarChart mChart;
    private TextView zhichuTView, shouruTView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_one, container, false);
        XCArcMenuView view = (XCArcMenuView) mView.findViewById(R.id.arcmenu2);
        view.setOnMenuItemClickListener(new XCArcMenuView.OnMenuItemClickListener() {

            @Override
            public void onClick(View view, int pos) {
                // TODO Auto-generated method stub
                String tag = (String) view.getTag();
                switch (tag) {
                    case "yule":
                        Toast.makeText(mActivity, "娱乐", Toast.LENGTH_SHORT)
                                .show();
                        // 弹出输入框 记录支出多少
                        String value = "85";
                        DBHelper.getIntance(mActivity).updataYule(value);
                        break;
                    case "gouwu":
                        Toast.makeText(mActivity, "购物", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case "canyin":
                        Toast.makeText(mActivity, "餐饮", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case "yiliao":
                        Toast.makeText(mActivity, "医疗", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    default:
                        Toast.makeText(mActivity, "其它", Toast.LENGTH_SHORT)
                                .show();
                        break;
                }

            }
        });
        initBar();
        initTop();
        return mView;
    }

    private void initTop() {
        zhichuTView = (TextView) mView.findViewById(R.id.zhichuTextView);
        zhichuTView.setText(DBHelper.getIntance(mActivity).getShouruToday());
    }

    private void initBar() {
        mChart = (BarChart) mView.findViewById(R.id.barChart);
        mChart.setOnTouchListener(null);
        ChartUtil.getIntance().initBarChart(mChart);
        setData(5, ChartUtil.getIntance().getBarMaxRange(mActivity));
    }

    private void setData(int count, float range) {
        ArrayList<String> xVals = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            xVals.add(ChartUtil.mMonths[i % 5]);
        }
        ArrayList<BarEntry> yVals1 = new ArrayList<>();

        String dataStr = DateTools.getDateYYYYMMDD();
        Zhichu entity = DBHelper.getIntance(mActivity).queryZhichu(dataStr);

        yVals1.add(new BarEntry(DBHelper.getIntance(mActivity).checkNumber(
                entity.getPlay()), 0));
        yVals1.add(new BarEntry(DBHelper.getIntance(mActivity).checkNumber(
                entity.getShopping()), 1));
        yVals1.add(new BarEntry(DBHelper.getIntance(mActivity).checkNumber(
                entity.getFood()), 2));
        yVals1.add(new BarEntry(DBHelper.getIntance(mActivity).checkNumber(
                entity.getMedicine()), 3));
        yVals1.add(new BarEntry(DBHelper.getIntance(mActivity).checkNumber(
                entity.getOther()), 4));

        BarDataSet set1;

        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            // set1.setYVals(yVals1);
            // mChart.getData().setXVals(xVals);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "一天消费");
            set1.setBarSpacePercent(35f);
            set1.setColors(ChartUtil.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(xVals, dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(Typeface.DEFAULT);

            mChart.setData(data);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
