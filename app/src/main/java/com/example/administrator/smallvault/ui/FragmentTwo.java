package com.example.administrator.smallvault.ui;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.smallvault.R;
import com.example.administrator.smallvault.db.DBHelper;
import com.example.administrator.smallvault.db.PayContentProvider;
import com.example.administrator.smallvault.db.entity.Zhichu;

import java.util.ArrayList;

public class FragmentTwo extends Fragment {

    private View mView;
    private TextView txtView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_two, container, false);
        txtView = (TextView) mView.findViewById(R.id.txtView);
        initData();
//        getData();

        ArrayList<Zhichu> arrayList = DBHelper.getIntance(getActivity()).queryZhichu();
        for (Zhichu t : arrayList) {
            Log.e("###########", t.getFood());
            Log.e("￥￥￥￥￥￥￥", t.getPlay());
        }

        return mView;
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
            getActivity().getContentResolver().insert(PayContentProvider.CONTENT_URI_ZHICHU, values);
        }

        ContentValues values2 = new ContentValues();
        values2.put("time", "2016-05-05");
        values2.put("shouru", 15);
        for (int i = 0; i < 10; i++) {
            getActivity().getContentResolver().insert(PayContentProvider.CONTENT_URI_SHOURU, values);
        }
    }

    public void getData() {
        Cursor cursor = getActivity().getContentResolver().query(PayContentProvider.CONTENT_URI_ZHICHU, null, null, null, null);
        Log.e("test ", "count=" + cursor.getCount());
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String table = cursor.getString(cursor.getColumnIndex("table_name"));
            String name = cursor.getString(cursor.getColumnIndex("time"));
            Log.e("test", "table_name:" + table);
            Log.e("test ", "time: " + name);
            cursor.moveToNext();
        }
        cursor.close();

    }
}
