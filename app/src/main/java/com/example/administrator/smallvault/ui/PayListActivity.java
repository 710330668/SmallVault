package com.example.administrator.smallvault.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.administrator.smallvault.R;
import com.example.administrator.smallvault.db.DBHelper;
import com.example.administrator.smallvault.db.entity.SiFangQian;
import com.example.administrator.smallvault.ui.adapter.PayListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/10.
 */
public class PayListActivity extends Activity {

    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paylist);
        init();
        initDate();
    }

    private void initDate() {
        List<SiFangQian> list=new ArrayList<>();
        list= DBHelper.getIntance(PayListActivity.this).querySiFangQian();
        PayListAdapter payListAdapter=new PayListAdapter(PayListActivity.this,list);
        mListView.setAdapter(payListAdapter);

    }

    private void init() {
        mListView=(ListView)this.findViewById(R.id.lv_pay);
    }
}
