package edu.qc.cs370.macrotracker.Macro;
import java.util.ArrayList;

public class Meal {
	ArrayList<Food> foods;
	String mealName;
	
	public Meal() {
		foods = new ArrayList<Food>();
	}
	
	public Meal(String mealName) {
		this.mealName = mealName;
	}
	
	public void addFood(Food food) {
		foods.add(food);
	}
	
	public double getCalorie() {
		double calorie = 0;
		for(Food food: foods){
			calorie += food.getCalorie();
		}
		return calorie;
	}
	
	public double getCarb() {
		double carb = 0;
		for(Food food: foods) {
			carb += food.getCarb();
		}
		return carb;
	}
	
	public double getProtein() {
		double protein = 0;
		for(Food food: foods) {
			protein += food.getProtein();
		}
		return protein;
	}
	
	public double getFat() {
		double fat = 0;
		for(Food food: foods) {
			fat += food.getFat();
		}
		return fat;
	}

	public ArrayList<Food> getFoods() {
		return foods;
	}

	public void setFoods(ArrayList<Food> foods) {
		this.foods = foods;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}
}
