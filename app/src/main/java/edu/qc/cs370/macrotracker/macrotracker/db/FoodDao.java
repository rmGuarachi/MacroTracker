package edu.qc.cs370.macrotracker.macrotracker.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.qc.cs370.macrotracker.db.Food;

@Dao
public interface FoodDao {

    @Insert
    public void addFood(edu.qc.cs370.macrotracker.macrotracker.db.Food food);

    @Query("SELECT * FROM foods")
    public List<edu.qc.cs370.macrotracker.macrotracker.db.Food> getFoods();

    @Query("Select * FROM foods WHERE date = :datequery")
    public List<edu.qc.cs370.macrotracker.macrotracker.db.Food> getFoods(String datequery);

    @Delete
    public void deleteFood(edu.qc.cs370.macrotracker.macrotracker.db.Food food);

    @Update
    public void updateFood(edu.qc.cs370.macrotracker.macrotracker.db.Food food);
}
