package com.example.administrator.smallvault.ui;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.administrator.smallvault.R;
import com.example.administrator.smallvault.db.DBHelper;
import com.example.administrator.smallvault.db.entity.Zhichu;
import com.example.administrator.smallvault.ui.view.XCArcMenuView;
import com.example.administrator.smallvault.util.ChartUtil;
import com.example.administrator.smallvault.util.DateTools;
import com.example.administrator.smallvault.util.SP;
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
    private EditText et_money;
    private EditText et_paywhere;
    private  AlertDialog.Builder alertDialog;
    private TextView tv_paywhere;
    private String selectFlag;
    private NotificationManager manager;
    private static final int NOTIFICATION_FLAG = 1;


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
        alertDialog=new AlertDialog.Builder(mActivity);
        view.setOnMenuItemClickListener(new XCArcMenuView.OnMenuItemClickListener() {

            @Override
            public void onClick(View view, int pos) {
                // TODO Auto-generated method stub
                String tag = (String) view.getTag();
                switch (tag) {
                    case "yule":
                        selectFlag="1";
                        showDialog();
                        break;
                    case "gouwu":
                        selectFlag="2";
                        showDialog();
                        break;
                    case "canyin":
                        selectFlag="3";
                        showDialog();
                        break;
                    case "yiliao":
                        selectFlag="4";
                        showDialog();
                        break;
                    default:
                        selectFlag="5";
                        showDialog();
                        break;
                }

            }
        });
        initBar();
        initTop();
        return mView;
    }

    private void showDialog() {
        LayoutInflater inflater = mActivity.getLayoutInflater().cloneInContext(mActivity);
        View layout = inflater.inflate(R.layout.item_dialog, null);
        et_money = (EditText) layout.findViewById(R.id.et_money);
        et_paywhere = (EditText) layout.findViewById(R.id.et_paywhere);
        et_paywhere.setVisibility(View.GONE);
        tv_paywhere=(TextView)layout.findViewById(R.id.tv_paywhere);
        tv_paywhere.setVisibility(View.GONE);
        alertDialog.setView(layout).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //存入数据库
                switch (selectFlag){
                    case "1":
                        DBHelper.getIntance(mActivity).updataYule(et_money.getText().toString());
                        IsSendNotify();
                        break;
                    case "2":
                        DBHelper.getIntance(mActivity).updataGouwu(et_money.getText().toString());
                        IsSendNotify();
                        break;
                    case "3":
                        DBHelper.getIntance(mActivity).updataYinshi(et_money.getText().toString());
                        IsSendNotify();
                        break;
                    case "4":
                        DBHelper.getIntance(mActivity).updataYiliao(et_money.getText().toString());
                        IsSendNotify();
                        break;
                    case "5":
                        DBHelper.getIntance(mActivity).updataQita(et_money.getText().toString());
                        IsSendNotify();
                        break;

                }

            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

    private void IsSendNotify() {
        SP sph = SP.getInstance(getActivity(), "password");
        manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        if(!TextUtils.isEmpty(sph.getOneTypeMoney())){
            if(Integer.valueOf(et_money.getText().toString())>Integer.valueOf(sph.getOneTypeMoney())) {
                //如果总收入大于默认设置的收入,通知用户
                // Notification myNotify = new Notification(R.drawable.message,
                // "自定义通知：您有新短信息了，请注意查收！", System.currentTimeMillis());
                makeNotify(1);
            }
        }
    }

    private void initTop() {
        zhichuTView = (TextView) mView.findViewById(R.id.zhichuTextView);
        zhichuTView.setText(DBHelper.getIntance(mActivity).getZhichuToday());
    }

    private void updataUI(){
        zhichuTView.setText(DBHelper.getIntance(mActivity).getZhichuToday());
        setData(5, ChartUtil.getIntance().getBarMaxRange(mActivity));
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

    private void makeNotify(int flag) {
        Notification myNotify = new Notification();
        myNotify.icon = R.drawable.icon_warning;
        if (flag == 0) {
            myNotify.tickerText = "TickerText:您的全部支出已超额!";
        } else if (flag == 1) {
            myNotify.tickerText = "TickerText:您的单项支出已超额!";
        }
        myNotify.when = System.currentTimeMillis();
        myNotify.flags = Notification.FLAG_NO_CLEAR;// 不能够自动清除
        RemoteViews rv = new RemoteViews(getActivity().getPackageName(),
                R.layout.my_notification);
        rv.setTextViewText(R.id.text_content, "您的支出已超额!");
        myNotify.contentView = rv;
        Intent intent = new Intent(Intent.ACTION_MAIN);
        PendingIntent contentIntent = PendingIntent.getActivity(getActivity(), 1, intent,
                PendingIntent.FLAG_ONE_SHOT);
        myNotify.contentIntent = contentIntent;
        manager.notify(NOTIFICATION_FLAG, myNotify);
    }

}
