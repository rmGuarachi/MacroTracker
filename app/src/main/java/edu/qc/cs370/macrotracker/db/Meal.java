package edu.qc.cs370.macrotracker.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.List;

@Entity(tableName = "meals")
public class Meal {

    @PrimaryKey
    @NonNull
    private int id;

    @ColumnInfo(name = "meal_name")
    private String mealName;

    @TypeConverters(Converter.class)
    public final List<Food> foods;


    public Meal(List<Food> foods) {
        this.foods = foods;
    }

    public int getId() {
        return id;
    }
}
