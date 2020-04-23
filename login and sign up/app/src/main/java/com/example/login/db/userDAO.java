package com.example.login.db;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface userDAO
{
    @Insert
    public void addUser(User user);

}
