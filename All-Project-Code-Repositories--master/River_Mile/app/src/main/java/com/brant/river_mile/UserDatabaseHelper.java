package com.brant.river_mile;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by brant on 3/19/20.
 * Helper java file for user database8
 */

public class UserDatabaseHelper extends SQLiteOpenHelper{
    private static final String TAG="DatabaseHelper";

    private static final String TABLE_NAME="user_table";
    private static final String Col1="user_ID";//PID
    private static final String Col2="user_email";
    private static final String Col3="user_password";
    private static final String Col4="first_name";
    private static final String Col5="last_name";



    public UserDatabaseHelper(Context context)
    {
        super (context,TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable="CREATE TABLE"+ TABLE_NAME +("ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ Col2 +" TEXT" + Col3+ "TEXT"+ Col4+"TEXT"+ Col5+ "TEXT" );// create table
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS" +TABLE_NAME);
        onCreate(db);

    }
    public boolean AddData(String uEmail, String uPass, String uFirstName, String uLastName)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();// create new content to be added
        contentValues.put(Col2, uEmail);// add run name
        contentValues.put(Col3, uPass); //add run put in coord
        contentValues.put(Col4, uFirstName); //add run takeout coord
        contentValues.put(Col5, uLastName);//add run difficulty

        Log.d(TAG, "adding Data: "+ uEmail+ uPass+ uFirstName+ uLastName+" to" +TABLE_NAME);//log data added

        long addSucess=db.insert(TABLE_NAME, null, contentValues);// returns -1 if incorrect insert, otherwise it is considered correct

        if (addSucess==-1){
            return false;
        }
        else{
            return true;
        }


    }
}
