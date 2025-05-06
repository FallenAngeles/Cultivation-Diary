package com.example.myapplication.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DiaryDao {

    @Query("SELECT * FROM Diary")
    List<Diary> getAll();

    @Query("SELECT text, CreateDate FROM Diary WHERE Diary_id = :Diary_id")
    Diary getById(int Diary_id);

    @Insert
    void insert(Diary diary);

    @Update
    void update(Diary diary);

    @Delete
    void delete(Diary diary);

}