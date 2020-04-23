package com.brant.river_mile;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by brant on 3/19/20.
 * Helper java file for run database
 * Resource used:
 * https://www.youtube.com/watch?v=aQAIMY-HzL8
 */

public class RunsDatabaseHelper extends SQLiteOpenHelper{
    private static final String TAG="DatabaseHelper";

    private static final String TABLE_NAME="runs_table";
    private static final String Col1="run_ID";
    private static final String Col2="run_name";
    private static final String Col3="PI_coord";
    private static final String Col4="TO_coord";
    private static final String Col5="run_diff";


    public RunsDatabaseHelper(Context context)
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
    public boolean AddData(String rName, String PIcord, String TOcord, String runDiff)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();// create new content to be added
        contentValues.put(Col2, rName);// add run name
        contentValues.put(Col3, PIcord); //add run put in coord
        contentValues.put(Col4, TOcord); //add run takeout coord
        contentValues.put(Col5, runDiff);//add run difficulty

        Log.d(TAG, "adding Data: "+ rName+ PIcord+TOcord+runDiff+" to" +TABLE_NAME);//log data added

        long addSucess=db.insert(TABLE_NAME, null, contentValues);// returns -1 if incorrect insert, otherwise it is considered correct

        if (addSucess==-1){
            return false;
        }
        else{
            return true;
        }


    }
}
