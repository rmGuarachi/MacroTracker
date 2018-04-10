package edu.qc.cs370.macrotracker.Model.macros;

import java.util.ArrayList;
import java.util.Date;

import edu.qc.cs370.macrotracker.Model.Food;

public class DailyMacro {
    private ArrayList<Food> foods;
    private Date date;
    private int totalCalories;
    private int targetCalories;

    public DailyMacro(Date date, int maxCal) {
        foods = new ArrayList<>();
        this.date = date;
        totalCalories = 0;
        targetCalories = maxCal;
    }

    public void insertFood(Food food){
        foods.add(food);
        totalCalories += food.getCalories();
    }

    public Food getFood(int index){
        return foods.get(index);
    }

    public int caloriesLeft(){
        return targetCalories - totalCalories;
    }
}
