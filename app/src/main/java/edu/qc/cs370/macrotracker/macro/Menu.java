package edu.qc.cs370.macrotracker.macro;
import android.annotation.TargetApi;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// Applied the below decorator to prevent a warning about DateTimeFormatter needing min sdk 26. - DV
@TargetApi(26)
public class Menu {
	static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM");
	String menuName;
	
	ArrayList<Meal> meals;
	LocalDate day;

	public Menu() {
		this.menuName = null;
		meals = new ArrayList<Meal>();
		day = LocalDate.now();
	}


	// The constructor should take in a menu name argument, in over to display breakfast, lunch, dinner, etc. - DV
	// This constructor should only be used for implementation of saved and shared meals. -LX
	public Menu(String menuName) {
	  this.menuName = menuName;
		meals = new ArrayList<Meal>();
		day = LocalDate.now();
	}

	public void addMeal(Meal meal) {
		meals.add(meal);
	}

	public ArrayList<Meal> getMeals() {
		return meals;
	}

	public void setMeals(ArrayList<Meal> meals) {
		this.meals = meals;
	}

	public String getDay() {
		return DTF.format(this.day);
	}
	public String getDay(LocalDate day) {
		return DTF.format(day);
	}

	public double getCalorie() {
		double calorie = 0;
		for(Meal meal: meals){
			calorie += meal.getCalorie();
		}
		return calorie;
	}

	public double getCarb() {
		double carb = 0;
		for(Meal meal: meals) {
			carb += meal.getCarb();
		}
		return carb;
	}

	public double getProtein() {
		double protein = 0;
		for(Meal meal: meals) {
			protein += meal.getProtein();
		}
		return protein;
	}

	public double getFat() {
		double fat = 0;
		for(Meal meal: meals) {
			fat += meal.getFat();
		}
		return fat;
	}
}
