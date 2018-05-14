package edu.qc.cs370.macrotracker.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface FoodDao {

    @Insert
    public void addFood(Food food);

    @Query("SELECT * FROM foods")
    public List<Food> getFoods();

    @Query("Select * FROM foods WHERE date = :datequery")
    public List<Food> getFoods(String datequery);

    @Delete
    public void deleteFood(Food food);

    @Update
    public void updateFood(Food food);
}
