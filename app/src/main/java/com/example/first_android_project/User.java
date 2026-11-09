package com.example.first_android_project;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mytable")
public class User {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String email;
    String phone;

    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public User() {
    }
}
