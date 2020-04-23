package com.example.login.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface runDAO {
    @Insert
    public void addRun(Run run);

    @Query("SELECT * from Run WHERE name LIKE :Rname")
    Run findRunByName(String Rname);
}
