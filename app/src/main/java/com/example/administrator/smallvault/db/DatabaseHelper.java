package com.example.administrator.smallvault.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "smallvault.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_FIRST_NAME = "zhichu";
    public static final String TABLE_SECOND_NAME = "shouru";
    public static final String TABLE_THIRD_NAME = "sifangqian";
    public static final String SQL_CREATE_TABLE_FIRST = "CREATE TABLE " + TABLE_FIRST_NAME + "("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "table_name" + " VARCHAR(50) default 'zhichu',"
            + "time" + " VARCHAR(10),"
            + "food" + " VARCHAR(10),"
            + "shopping" + " VARCHAR(10),"
            + "play" + " VARCHAR(10),"
            + "medicine" + " VARCHAR(10),"
            + "other" + " VARCHAR(10)"
            + ");";

    public static final String SQL_CREATE_TABLE_SECOND = "CREATE TABLE " + TABLE_SECOND_NAME + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "table_name" + " VARCHAR(50) default 'shouru',"
            + "time" + " VARCHAR(10),"
            + "shouru" + " VARCHAR(10)"
            + ");";
    public static final String SQL_CREATE_TABLE_THIRD = "CREATE TABLE " + TABLE_THIRD_NAME + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "table_name" + " VARCHAR(50) default 'sifangqian',"
            + "time" + " VARCHAR(10),"
            + "money" + " VARCHAR(10),"
            + "paywhere" + " VARCHAR(10)"
            + ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_FIRST);
        db.execSQL(SQL_CREATE_TABLE_SECOND);
        db.execSQL(SQL_CREATE_TABLE_THIRD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS zhichu");
        db.execSQL("DROP TABLE IF EXISTS shouru");
        db.execSQL("DROP TABLE IF EXISTS sifangqian");
        onCreate(db);
    }
}
