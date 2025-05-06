package com.example.myapplication.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity
public class Diary {

    @PrimaryKey
    public int Diary_id;
    public String text;
    public LocalDate CreateDate;
}

