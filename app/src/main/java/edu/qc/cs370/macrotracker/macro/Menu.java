package edu.qc.cs370.macrotracker.macro;
import android.annotation.TargetApi;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@TargetApi(26)
public class Menu {
	static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM");
	
	ArrayList<Meal> meals;
	LocalDate day;
	
	public Menu() {
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

}
