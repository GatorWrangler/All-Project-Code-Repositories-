package com.brant.river_mile;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by brant on 3/19/20.
 * Helper java file for log database
 */

public class AddRunToLogDBHelper extends SQLiteOpenHelper{
    private static final String TAG="DatabaseHelper";

    private static final String TABLE_NAME="log_table";
    private static final String Col1="log_entry_id";//PID
    private static final String Col2="user_id";//Forign Key
    private static final String Col3="run_id";// Forigen Key




    public AddRunToLogDBHelper(Context context)
    {
        super (context,TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable="CREATE TABLE"+ TABLE_NAME +("ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ Col2 +" TEXT" + Col3+ "TEXT");// create table
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS" +TABLE_NAME);
        onCreate(db);

    }
    public boolean AddData(String uID, String rID)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();// create new content to be added
        contentValues.put(Col2, uID);// add user id
        contentValues.put(Col3, rID); //add run id


        Log.d(TAG, "adding Data: "+ uID+ rID+" to" +TABLE_NAME);//log data added

        long addSucess=db.insert(TABLE_NAME, null, contentValues);// returns -1 if incorrect insert, otherwise it is considered correct

        return addSucess != -1;


    }
}
