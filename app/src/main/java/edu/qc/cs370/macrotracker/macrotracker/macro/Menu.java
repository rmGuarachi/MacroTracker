package edu.qc.cs370.macrotracker.macrotracker.macro;
import android.annotation.TargetApi;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.qc.cs370.macrotracker.macro.Meal;

// Applied the below decorator to prevent a warning about DateTimeFormatter needing min sdk 26. - DV
@TargetApi(26)
public class Menu {
	//static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM");

	//Using SimpleDataFormat because java.lang.NoClassDefFoundError: Failed resolution of: Ljava/time/format/DateTimeFormatter; - LX
	static final SimpleDateFormat DTF = new SimpleDateFormat("dd/MM");
	String menuName;

	ArrayList<edu.qc.cs370.macrotracker.macrotracker.macro.Meal> meals;
	//Change Date to String - LX
	String day;

	public Menu() {
		this.menuName = "";
		meals = new ArrayList<edu.qc.cs370.macrotracker.macrotracker.macro.Meal>();
		meals.add(new edu.qc.cs370.macrotracker.macrotracker.macro.Meal());
		//LocalDate.now() throws error. -LX
		//day = LocalDate.now();
		day = DTF.format( Calendar.getInstance().getTime() );
	}


	// The constructor should take in a menu name argument, in over to display breakfast, lunch, dinner, etc. - DV
	// This constructor should only be used for implementation of saved and shared meals. -LX depre.

	//****************************
	/* -LX
		MENU CONSTRUCTOR
		This constructor is intended for use when we share Today's Menu.
		eg. menuName : Louis' Fasting
		    menuName : Carboloading
		    menuName : Paleo Diet
	*/
	//****************************
	public Menu(String menuName) {
		this();
		this.menuName = menuName;
		/*
		meals = new ArrayList<Meal>();
		//Same bug as default constructor - LX
		//day = LocalDate.now();
		day = DTF.format( Calendar.getInstance().getTime() );
		*/
	}

	public void addMeal(edu.qc.cs370.macrotracker.macrotracker.macro.Meal meal) {
		meals.add(meal);
	}

	public ArrayList<edu.qc.cs370.macrotracker.macrotracker.macro.Meal> getMeals() {
		return meals;
	}

	public void setMeals(ArrayList<edu.qc.cs370.macrotracker.macrotracker.macro.Meal> meals) {
		this.meals = meals;
	}

	public String getDay() {
		return day;
	}
	public static String getTodayDay() {
		return DTF.format( (Date)Calendar.getInstance().getTime() );
	}

	public double getCalorie() {
		double calorie = 0;
		for(edu.qc.cs370.macrotracker.macrotracker.macro.Meal meal: meals){
			calorie += meal.getCalorie();
		}
		return calorie;
	}

	public double getCarb() {
		double carb = 0;
		for(edu.qc.cs370.macrotracker.macrotracker.macro.Meal meal: meals) {
			carb += meal.getCarb();
		}
		return carb;
	}

	public double getProtein() {
		double protein = 0;
		for(edu.qc.cs370.macrotracker.macrotracker.macro.Meal meal: meals) {
			protein += meal.getProtein();
		}
		return protein;
	}

	public double getFat() {
		double fat = 0;
		for(edu.qc.cs370.macrotracker.macrotracker.macro.Meal meal: meals) {
			fat += meal.getFat();
		}
		return fat;
	}
}