package com.example.first_android_project;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    long insert(User user);

    @Query("SELECT*FROM mytable")
    List<User> getAll();

   // @Query("DROP FROM mytable")
    @Query("DELETE FROM mytable")
    int clearAll();

    @Query("DELETE FROM sqlite_sequence WHERE name = 'mytable'")
    void reset();

    @Query("DELETE FROM mytable WHERE id=:id")
    int deleteById(int id);
}
