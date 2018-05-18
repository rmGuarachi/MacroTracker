package edu.qc.cs370.macrotracker.macrotracker.macro;
import java.util.ArrayList;

import edu.qc.cs370.macrotracker.macro.Food;

public class Meal {
	ArrayList<edu.qc.cs370.macrotracker.macrotracker.macro.Food> foods;
	String mealName;

	// The constructor should require a meal name, commenting out the below for now. - DV

	// Meal name now defaults to BLANK SPACE instead of null. avoids nullpointerexception - LX
	public Meal() {
		mealName = "";
		foods = new ArrayList<edu.qc.cs370.macrotracker.macrotracker.macro.Food>();
		//foods.add(new Food());
	}


	public Meal(String mealName) {
	  // Adding the initialization of the foods ArrayList from the above constructor. - DV
    	this();
		this.mealName = mealName;
	}

	public void addFood(edu.qc.cs370.macrotracker.macrotracker.macro.Food food) {
		foods.add(food);
	}

	public double getCalorie() {
		double calorie = 0;
		for(edu.qc.cs370.macrotracker.macrotracker.macro.Food food: foods){
			calorie += food.getCalorie();
		}
		return calorie;
	}

	public double getCarb() {
		double carb = 0;
		for(edu.qc.cs370.macrotracker.macrotracker.macro.Food food: foods) {
			carb += food.getCarb();
		}
		return carb;
	}

	public double getProtein() {
		double protein = 0;
		for(edu.qc.cs370.macrotracker.macrotracker.macro.Food food: foods) {
			protein += food.getProtein();
		}
		return protein;
	}

  public double getFat() {
    double fat = 0;
    for(edu.qc.cs370.macrotracker.macrotracker.macro.Food food: foods) {
	  fat += food.getFat();
    }
   return fat;
  }

  public ArrayList<edu.qc.cs370.macrotracker.macrotracker.macro.Food> getFoods() {
		return foods;
	}

  public void setFoods(ArrayList<edu.qc.cs370.macrotracker.macrotracker.macro.Food> foods) {
    this.foods = foods;
  }
  public String getMealName() {
    return mealName;
  }

  public void setMealName(String mealName) {
    this.mealName = mealName;
  }

  //toString modified to display meal Name - LX
  public String toString() {
	    return mealName + ": " + getCalorie() + "/" + getCarb() + "/" + getProtein() + "/" + getFat();
  }
}
