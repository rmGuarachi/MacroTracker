package edu.qc.cs370.macrotracker.Macro;

import java.util.ArrayList;

public class Profile {
	int calorie;
	double carb, protein, fat, water;
	ArrayList<Menu> menu;
	
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
}
