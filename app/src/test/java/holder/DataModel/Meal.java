package holder.DataModel;

import java.util.ArrayList;

public class Meal {
    String mealName;
    ArrayList<Food> foods;

    // The constructor should require a meal name, commenting out the below for now. - DV
    // Meal name now defaults to BLANK SPACE instead of null. avoids nullpointerexception - LX
    public Meal() {
        mealName = "";
        foods = new ArrayList<Food>();
    }

    public Meal(String mealName) {
        this();
        this.mealName = mealName;
    }

    //toString modified to display meal Name - LX
    public String toString() {
        return mealName + ": " + getCalorie() + "/" + getCarb() + "/" + getProtein() + "/" + getFat();
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
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
}
