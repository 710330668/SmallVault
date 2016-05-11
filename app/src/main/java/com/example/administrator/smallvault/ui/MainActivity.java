package com.example.administrator.smallvault.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;

import com.example.administrator.smallvault.R;
import com.example.administrator.smallvault.db.DBHelper;
import com.example.administrator.smallvault.ui.ui.IndicatorFragmentActivity;
import com.example.administrator.smallvault.util.SP;

import java.util.List;

//我
public class MainActivity extends IndicatorFragmentActivity {

    public static final int FRAGMENT_ONE = 0;
    public static final int FRAGMENT_TWO = 1;
    public static final int FRAGMENT_THREE = 2;
    private static final int NOTIFICATION_FLAG = 1;
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SP sph = SP.getInstance(MainActivity.this, "password");
        manager = (NotificationManager) getSystemService(
                Context.NOTIFICATION_SERVICE);
        if (!TextUtils.isEmpty(sph.getAllMoney())) {
            if (Integer.valueOf(
                    DBHelper.getIntance(MainActivity.this).getZhichuToday()
                            + "") > Integer.valueOf(sph.getAllMoney())) {
                // 如果总收入大于默认设置的收入,通知用户
                // Notification myNotify = new Notification(R.drawable.message,
                // "自定义通知：您有新短信息了，请注意查收！", System.currentTimeMillis());
                makeNotify(0);
            }
        }

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
        RemoteViews rv = new RemoteViews(getPackageName(),
                R.layout.my_notification);
        rv.setTextViewText(R.id.text_content, "您的支出已超额!");
        myNotify.contentView = rv;
        Intent intent = new Intent(Intent.ACTION_MAIN);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 1, intent,
                PendingIntent.FLAG_ONE_SHOT);
        myNotify.contentIntent = contentIntent;
        manager.notify(NOTIFICATION_FLAG, myNotify);
    }

    @Override
    protected int supplyTabs(List<TabInfo> tabs) {
        tabs.add(new TabInfo(FRAGMENT_ONE, getString(R.string.fragment_one),
                FragmentOne.class));
        tabs.add(new TabInfo(FRAGMENT_TWO, getString(R.string.fragment_two),
                FragmentTwo.class));
        tabs.add(new TabInfo(FRAGMENT_THREE, getString(R.string.fragment_three),
                FragmentThree.class));

        return FRAGMENT_TWO;
    }

}
