package com.example.login.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//数据库Helper
public class LoginDBOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "LoginDBOpenHelper";
    public static final String DATABASE_NAME = "login.db"; // 数据库名称
    public static final int DATABASE_VERSION = 1;// 数据库版本

    public LoginDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + LoginColumns.TABLE_NAME + " (" + LoginColumns._ID + " integer primary key autoincrement, " + LoginColumns.USER_NAME + " varchar(100) unique, " + LoginColumns.FIRST_NAME + " varchar(100), " + LoginColumns.LAST_NAME + " varchar(100), " + LoginColumns.USER_EMAIL + " varchar(100) unique, " + LoginColumns.USER_PASSWORD + " varchar(100))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
