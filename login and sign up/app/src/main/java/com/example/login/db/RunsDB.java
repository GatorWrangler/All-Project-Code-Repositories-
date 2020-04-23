package com.example.login.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RunsDB
{
    @PrimaryKey
    private int id;

    private String name;

    private String riverClass;

    private int PICoord;
    private int TOCoord;

    private int runLength;
}
