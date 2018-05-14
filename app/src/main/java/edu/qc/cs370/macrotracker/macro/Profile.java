package edu.qc.cs370.macrotracker.macro;

import java.util.ArrayList;

public class Profile {
	int calorie; //This is kCal
	double carb, protein, fat, water; //These values represent (g).
	ArrayList<Menu> menu;

	//Default constructor using HealthyEater.com recommended values -LX
	public Profile() {
		this(2200, 343, 99, 65);
	}

	//This constructor intended to accept target calorie, carb, protein, fat. - LX
	public Profile(int calorie, double carb, double protein, double fat) {
		this.calorie = calorie;
		this.carb = carb;
		this.protein = protein;
		this.fat = fat;
		menu = new ArrayList<Menu>();
	}
	
	public static double PERCENT_TO_MASS_CP(int calorie, double percent) {
		double cp_mass = ((double)calorie * percent) / 4;
		return cp_mass;
	}
	
	public static double PERCENT_TO_MASS_FAT(int calorie, double percent) {
		double f_mass = ((double)calorie * percent) / 9;
		return f_mass;
	}
	
	public static double MASS_TO_PERCENT_CP(int calorie, double mass) {
		double cp_percent = (mass * 4) / calorie;
		return cp_percent;
	}
	
	public static double MASS_TO_PERCENT_FAT(int calorie, double mass) {
		double cp_percent = (mass * 9) / calorie;
		return cp_percent;
	}

	public int getCalorie() {
		return calorie;
	}

	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}

	public double getCarb() {
		return carb;
	}

	public void setCarb(double carb) {
		this.carb = carb;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getFat() {
		return fat;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	public ArrayList<Menu> getMenu() {
		return menu;
	}

	//Method to retrieve the latest Menu object. Today's Menu object - LX
	public Menu getTodayMenu() {
		Menu t_menu = null;
		for(Menu i_menu: this.menu) {
			if (i_menu.getDay() == Menu.getTodayDay())
				t_menu = i_menu;
		}
		if(t_menu == null) {
			Menu n_menu = new Menu("Today's Menu");
			menu.add(n_menu);
			t_menu = n_menu;
		}

		return t_menu;
	}

	//Adding this method to be able to retrieve/recreate Profile settings - LX
	public String toJSON() {
		String json = "{ \"calorie\": " + getCalorie() + ", " + "\"carb\":" + getCarb() + ", "
				+ "\"protein\":" + getProtein() + ", " + "\"fat\":" + getFat() + " }";
		return json;
	}

}
