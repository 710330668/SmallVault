package com.example.administrator.smallvault.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.administrator.smallvault.db.entity.ShouRu;
import com.example.administrator.smallvault.db.entity.SiFangQian;
import com.example.administrator.smallvault.db.entity.Zhichu;
import com.example.administrator.smallvault.util.DateTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public class DBHelper {

    private static volatile DBHelper intance = null;
    private ContentResolver mContentResolver;
    private static Context context;

    private DBHelper(Context context) {
        this.mContentResolver = context.getContentResolver();
    }

    public static DBHelper getIntance(Context context) {

        if (intance == null) {
            synchronized (DBHelper.class) {
                if (intance == null) {
                    intance = new DBHelper(context);
                }
            }
        }
        return intance;
    }

    private String getMonth() {
        return DateTools.getDateYYYYMMDD().substring(0, 6);
    }

    public boolean inster(List<Zhichu> list) {
        try {
            for (Zhichu entity : list) {
                ContentValues values = new ContentValues();
                values.put("time", entity.getTime());
                values.put("month", getMonth());
                values.put("food", entity.getFood());
                values.put("shopping", entity.getShopping());
                values.put("play", entity.getPlay());
                values.put("medicine", entity.getMedicine());
                values.put("other", entity.getOther());
                mContentResolver.insert(PayContentProvider.CONTENT_URI_ZHICHU,
                        values);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean inster(Zhichu entity) {
        try {
            ContentValues values = new ContentValues();
            values.put("time", entity.getTime());
            values.put("month", getMonth());
            values.put("food", entity.getFood());
            values.put("shopping", entity.getShopping());
            values.put("play", entity.getPlay());
            values.put("medicine", entity.getMedicine());
            values.put("other", entity.getOther());
            mContentResolver.insert(PayContentProvider.CONTENT_URI_ZHICHU,
                    values);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean insert(SiFangQian entity) {
        try {
            ContentValues values = new ContentValues();
            values.put("time", entity.getTime());
            values.put("money", entity.getMoney());
            values.put("paywhere", entity.getPaywhere());
            mContentResolver.insert(PayContentProvider.CONTENT_URI_SIFANGQIAN, values);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean inster(ShouRu entity) {
        try {
            ContentValues values = new ContentValues();
            values.put("time", DateTools.getDateYYYYMMDD());
            values.put("month", getMonth());
            values.put("shouru", 15);
            mContentResolver.insert(PayContentProvider.CONTENT_URI_SHOURU,
                    values);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<ShouRu> queryShouru() {
        ArrayList<ShouRu> list = new ArrayList<>();
        Cursor cursor = mContentResolver.query(
                PayContentProvider.CONTENT_URI_SHOURU, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ShouRu entity = new ShouRu();
            // String table = cursor.getString(cursor.getColumnIndex("table_name"));
            entity.setTime(cursor.getString(cursor.getColumnIndex("time")));
            entity.setMonth(cursor.getString(cursor.getColumnIndex("month")));
            entity.setShouru(cursor.getString(cursor.getColumnIndex("shouru")));
            list.add(entity);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ShouRu queryShouru(String dataStr) {
        ShouRu entity = new ShouRu();
        Cursor cursor = mContentResolver.query(
                PayContentProvider.CONTENT_URI_SHOURU, null, "time=?",
                new String[]{dataStr}, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                entity.setShouru(cursor.getString(cursor
                        .getColumnIndex("shouru")));
                entity.setTime(cursor.getString(cursor.getColumnIndex("time")));
                entity.setMonth(cursor.getString(cursor.getColumnIndex("month")));
            }
        }
        cursor.close();
        return entity;
    }

    public Zhichu queryZhichu(String dataStr) {
        Zhichu entity = new Zhichu();
        Cursor cursor = mContentResolver.query(
                PayContentProvider.CONTENT_URI_ZHICHU, null, "time=?",
                new String[]{dataStr}, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                entity.setTime(cursor.getString(cursor.getColumnIndex("time")));
                entity.setMonth(cursor.getString(cursor.getColumnIndex("month")));
                entity.setFood(cursor.getString(cursor.getColumnIndex("food")));
                entity.setMedicine(cursor.getString(cursor
                        .getColumnIndex("medicine")));
                entity.setPlay(cursor.getString(cursor.getColumnIndex("play")));
                entity.setShopping(cursor.getString(cursor
                        .getColumnIndex("shopping")));
                entity.setOther(cursor.getString(cursor.getColumnIndex("other")));
            }
        }
        cursor.close();
        return entity;
    }

    public ArrayList<Zhichu> queryZhichu() {
        ArrayList<Zhichu> list = new ArrayList<>();
        Cursor cursor = mContentResolver.query(
                PayContentProvider.CONTENT_URI_ZHICHU, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Zhichu entity = new Zhichu();
            // String table = cursor.getString(cursor.getColumnIndex("table_name"));
            entity.setTime(cursor.getString(cursor.getColumnIndex("time")));
            entity.setMonth(cursor.getString(cursor.getColumnIndex("month")));
            entity.setFood(cursor.getString(cursor.getColumnIndex("food")));
            entity.setShopping(cursor.getString(cursor.getColumnIndex("shopping")));
            entity.setPlay(cursor.getString(cursor.getColumnIndex("play")));
            entity.setMedicine(cursor.getString(cursor.getColumnIndex("medicine")));
            entity.setOther(cursor.getString(cursor.getColumnIndex("other")));
            list.add(entity);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    public ArrayList<Zhichu> queryCurrentZhichu() {
        ArrayList<Zhichu> list = new ArrayList<>();
        Cursor cursor = mContentResolver.query(PayContentProvider.CONTENT_URI_ZHICHU, null, "month=?",
                new String[]{getMonth()}, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Zhichu entity = new Zhichu();
            // String table = cursor.getString(cursor.getColumnIndex("table_name"));
            entity.setMonth(cursor.getString(cursor.getColumnIndex("month")));
            entity.setTime(cursor.getString(cursor.getColumnIndex("time")));
            entity.setFood(cursor.getString(cursor.getColumnIndex("food")));
            entity.setShopping(cursor.getString(cursor.getColumnIndex("shopping")));
            entity.setPlay(cursor.getString(cursor.getColumnIndex("play")));
            entity.setMedicine(cursor.getString(cursor.getColumnIndex("medicine")));
            entity.setOther(cursor.getString(cursor.getColumnIndex("other")));
            list.add(entity);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    public List<SiFangQian> querySiFangQian() {
        List<SiFangQian> list = new ArrayList<SiFangQian>();
        Cursor cursor = mContentResolver.query(PayContentProvider.CONTENT_URI_SIFANGQIAN, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SiFangQian entity = new SiFangQian();
//            String table = cursor.getString(cursor.getColumnIndex("table_name"));
            entity.setTime(cursor.getString(cursor.getColumnIndex("time")));
            entity.setMoney(cursor.getString(cursor.getColumnIndex("money")));
            entity.setPaywhere(cursor.getString(cursor.getColumnIndex("paywhere")));
            list.add(entity);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public CharSequence getShouruToday() {
        String dataStr = DateTools.getDateYYYYMMDD();
        ShouRu entity = queryShouru(dataStr);
        return entity.getShouru();
    }

    public CharSequence getZhichuToday() {
        String dataStr = DateTools.getDateYYYYMMDD();
        Zhichu entity = queryZhichu(dataStr);
        float num = 0;
        num = num + checkNumber(entity.getFood())
                + checkNumber(entity.getMedicine())
                + checkNumber(entity.getOther())
                + checkNumber(entity.getShopping())
                + checkNumber(entity.getPlay());

        return num + "";
    }

    public float checkNumber(String string) {
        if (string != null && !string.equals("")) {
//            return (new   Double(Double.valueOf(string))).intValue();
            return Float.valueOf(string);
        } else
            return Float.valueOf(0);
    }

    public void updataYule(String value) {
        ContentValues values = new ContentValues();
        String dataStr = DateTools.getDateYYYYMMDD();
        values.put("play", value);
        commonValues(values, dataStr);
    }

    public void updataYinshi(String value) {
        ContentValues values = new ContentValues();
        String dataStr = DateTools.getDateYYYYMMDD();
        values.put("food", value);
        commonValues(values, dataStr);
    }

    public void updataGouwu(String value) {
        ContentValues values = new ContentValues();
        String dataStr = DateTools.getDateYYYYMMDD();
        values.put("shopping", value);
        commonValues(values, dataStr);
    }

    public void updataYiliao(String value) {
        ContentValues values = new ContentValues();
        String dataStr = DateTools.getDateYYYYMMDD();
        values.put("medicine", value);
        commonValues(values, dataStr);
    }

    public void updataQita(String value) {
        ContentValues values = new ContentValues();
        String dataStr = DateTools.getDateYYYYMMDD();
        values.put("other", value);
        commonValues(values, dataStr);
    }

    private void commonValues(ContentValues values, String dataStr) {
        Cursor cursor = mContentResolver.query(
                PayContentProvider.CONTENT_URI_ZHICHU, null, "time=?",
                new String[]{dataStr}, null);
        if (cursor.getCount() != 0) {
            mContentResolver.update(PayContentProvider.CONTENT_URI_ZHICHU, values,
                    "time=" + dataStr, null);
        } else {
            values.put("time", dataStr);
            values.put("month", getMonth());
            mContentResolver.insert(PayContentProvider.CONTENT_URI_ZHICHU, values);
        }
    }

    public ArrayList<Zhichu> queryCurrentMonthZhichu() {
        ArrayList<Zhichu> arrayList = new ArrayList<>();
        Cursor cursor = mContentResolver.query(PayContentProvider.CONTENT_URI_ZHICHU, null, "month=?",
                new String[]{getMonth()}, "time DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Zhichu entity = new Zhichu();
            entity.setMonth(cursor.getString(cursor.getColumnIndex("month")));
            entity.setTime(cursor.getString(cursor.getColumnIndex("time")));
            entity.setFood(cursor.getString(cursor.getColumnIndex("food")));
            entity.setShopping(cursor.getString(cursor.getColumnIndex("shopping")));
            entity.setPlay(cursor.getString(cursor.getColumnIndex("play")));
            entity.setMedicine(cursor.getString(cursor.getColumnIndex("medicine")));
            entity.setOther(cursor.getString(cursor.getColumnIndex("other")));
            arrayList.add(entity);
            cursor.moveToNext();
        }
        cursor.close();
        return arrayList;
    }
}