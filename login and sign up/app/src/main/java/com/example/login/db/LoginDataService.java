package com.example.login.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.login.UserBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 表操作类
 *
 * @author x8.phoenix
 */

public class LoginDataService {
    private static final String TAG = LoginDataService.class.getName();
    private LoginDBOpenHelper dbOpenHelper;
    //private OnPasswordChangeListener onPasswordChangeListener;

    public LoginDataService(Context context) {
        dbOpenHelper = new LoginDBOpenHelper(context);
    }

    // 增;
    public void insertUser(UserBean userBean) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("insert into " + LoginColumns.TABLE_NAME + "(" + LoginColumns.USER_NAME + "," + LoginColumns.FIRST_NAME + "," + LoginColumns.LAST_NAME + "," + LoginColumns.USER_EMAIL + "," + LoginColumns.USER_PASSWORD + ") values(?,?,?,?,?)", new Object[]{userBean.getUser_name(), userBean.getFirst_name(), userBean.getLast_name(), userBean.getUser_email(), userBean.getUser_password()});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        db.close();
    }


    // 删
    public void deleteUser(String username) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("delete from " + LoginColumns.TABLE_NAME + " where " + LoginColumns.USER_NAME + "=?", new Object[]{username});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        db.close(); // new
        // Object[]{_id}
    }


    // 改
    public void update(UserBean userBean) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("update " + LoginColumns.TABLE_NAME + " set " + LoginColumns.FIRST_NAME + " =?, " + LoginColumns.LAST_NAME + " =?, " + LoginColumns.USER_EMAIL + " =?, " + LoginColumns.USER_PASSWORD + "=? where " + LoginColumns.USER_NAME + "=?", new Object[]{userBean.getFirst_name(), userBean.getLast_name(), userBean.getUser_email(), userBean.getUser_password(), userBean.getUser_name()});
            db.setTransactionSuccessful();
            //passwordChangeListener(passwordEntity.getPassword());
        } finally {
            db.endTransaction();
        }
        db.close();

    }

    //查询
    public List<UserBean> queryAllUser() {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + LoginColumns.TABLE_NAME, null);
        if (cursor == null) {
            return null;
        } else {
            if (cursor.getCount() == 0) return null;
        }
        List<UserBean> userBeanList = new ArrayList<UserBean>();
        while (cursor.moveToNext()) {
            UserBean bean = new UserBean();
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String USER_NAME = cursor.getString(cursor.getColumnIndex(LoginColumns.USER_NAME));
            String FIRST_NAME = cursor.getString(cursor.getColumnIndex(LoginColumns.FIRST_NAME));
            String LAST_NAME = cursor.getString(cursor.getColumnIndex(LoginColumns.LAST_NAME));
            String USER_EMAIL = cursor.getString(cursor.getColumnIndex(LoginColumns.USER_EMAIL));
            String USER_PASSWORD = cursor.getString(cursor.getColumnIndex(LoginColumns.USER_PASSWORD));
            bean._id = id;
            bean.setUser_name(USER_NAME);
            bean.setFirst_name(FIRST_NAME);
            bean.setLast_name(LAST_NAME);
            bean.setUser_email(USER_EMAIL);
            bean.setUser_password(USER_PASSWORD);
            userBeanList.add(bean);
        }
        cursor.close();
        return userBeanList;
    }

    //根据用户名查询
    public UserBean queryUserByUserName(String userName) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + LoginColumns.TABLE_NAME + " where " + LoginColumns.USER_NAME + "=?", new String[]{userName});
        UserBean bean = null;
        if (cursor.moveToFirst()) {
            bean = new UserBean();
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String USER_NAME = cursor.getString(cursor.getColumnIndex(LoginColumns.USER_NAME));
            String FIRST_NAME = cursor.getString(cursor.getColumnIndex(LoginColumns.FIRST_NAME));
            String LAST_NAME = cursor.getString(cursor.getColumnIndex(LoginColumns.LAST_NAME));
            String USER_EMAIL = cursor.getString(cursor.getColumnIndex(LoginColumns.USER_EMAIL));
            String USER_PASSWORD = cursor.getString(cursor.getColumnIndex(LoginColumns.USER_PASSWORD));
            bean._id = id;
            bean.setUser_name(USER_NAME);
            bean.setFirst_name(FIRST_NAME);
            bean.setLast_name(LAST_NAME);
            bean.setUser_email(USER_EMAIL);
            bean.setUser_password(USER_PASSWORD);
        }
        cursor.close();
        return bean;
    }
    //根据email查询
    public UserBean queryUserByEmail(String email) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + LoginColumns.TABLE_NAME + " where " + LoginColumns.USER_EMAIL + "=?", new String[]{email});
        UserBean bean = null;
        if (cursor.moveToFirst()) {
            bean = new UserBean();
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String USER_NAME = cursor.getString(cursor.getColumnIndex(LoginColumns.USER_NAME));
            String FIRST_NAME = cursor.getString(cursor.getColumnIndex(LoginColumns.FIRST_NAME));
            String LAST_NAME = cursor.getString(cursor.getColumnIndex(LoginColumns.LAST_NAME));
            String USER_EMAIL = cursor.getString(cursor.getColumnIndex(LoginColumns.USER_EMAIL));
            String USER_PASSWORD = cursor.getString(cursor.getColumnIndex(LoginColumns.USER_PASSWORD));
            bean._id = id;
            bean.setUser_name(USER_NAME);
            bean.setFirst_name(FIRST_NAME);
            bean.setLast_name(LAST_NAME);
            bean.setUser_email(USER_EMAIL);
            bean.setUser_password(USER_PASSWORD);
        }
        cursor.close();
        return bean;
    }

}
