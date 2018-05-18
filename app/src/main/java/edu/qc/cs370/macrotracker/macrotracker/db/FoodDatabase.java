package edu.qc.cs370.macrotracker.macrotracker.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

import edu.qc.cs370.macrotracker.db.Food;
import edu.qc.cs370.macrotracker.db.FoodDao;
import edu.qc.cs370.macrotracker.db.User;
import edu.qc.cs370.macrotracker.db.UserDao;


@Database(entities = {edu.qc.cs370.macrotracker.macrotracker.db.Food.class, edu.qc.cs370.macrotracker.macrotracker.db.User.class}, version = 1)
public abstract class FoodDatabase extends RoomDatabase {
    public abstract edu.qc.cs370.macrotracker.macrotracker.db.FoodDao foodDao();

    public abstract edu.qc.cs370.macrotracker.macrotracker.db.UserDao userDao();
}
