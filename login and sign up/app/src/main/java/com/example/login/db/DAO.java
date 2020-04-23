package com.example.login.db;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface DAO
{
    @Insert
   public void addUser(User user);
}


