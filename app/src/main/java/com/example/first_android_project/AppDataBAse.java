package com.example.first_android_project;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class},version = 2)
public abstract class AppDataBAse extends RoomDatabase {

    public abstract UserDao userDao();

    public static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase Database) {
            Database.execSQL("ALTER TABLE mytable ADD COLUMN phone TEXT");
         //   Database.execSQL("ALTER TABLE mytable ADD COLUMN age INTEGER NOT NULL DEFAULT 0");
        }
    };

}
