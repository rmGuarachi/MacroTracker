package edu.qc.cs370.macrotracker.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;


@Database(entities = {Food.class, User.class}, version = 1)
public abstract class FoodDatabase extends RoomDatabase {
    public abstract FoodDao foodDao();

    public abstract UserDao userDao();
}
