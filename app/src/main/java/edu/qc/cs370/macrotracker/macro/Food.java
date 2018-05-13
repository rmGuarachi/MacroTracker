package edu.qc.cs370.macrotracker.macro;

public class Food {
	protected int foodId;
	protected String foodName;
	protected double weight, calorie, carb, protein, fat;

	// Constructor without weight
	public Food(int id, String name, double calorie, double fat, double carb, double protein) {
		this.foodId = id;
		this.foodName = name;
		this.calorie = calorie;
		this.carb = carb;
		this.protein = protein;
		this.fat = fat;
	}

	// Constructor with weight
	public Food(int id, String name, double calorie, double fat, double carb, double protein, double weight) {
		this.foodId = id;
		this.foodName = name;
		this.calorie = calorie;
		this.carb = carb;
		this.protein = protein;
		this.fat = fat;
		this.weight = weight;
	}

	public String toString() {
		String json = "{ \"id\":" + foodId + ", \"name\":\"" + foodName + "\", " + "\"weight\": " + weight + ", "
				+ "\"calories\": " + getCalorie() + ", " + "\"carb\":" + getCarb() + ", "
				+ "\"protein\":" + getProtein() + ", " + "\"fat\":" + getFat() + " }";
		return json;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getCalorie() {
		return calorie * weight;
	}

	public void setCalorie(double calorie) {
		this.calorie = calorie;
	}

	public double getCarb() {
		return carb * weight;
	}

	public void setCarb(double carb) {
		this.carb = carb;
	}

	public double getProtein() {
		return protein * weight;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getFat() {
		return fat * weight;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	// Added the below two toString methods for adding to the listview in the AddMeal Activity - DV
	public String getNameAndWeight() {
		return foodName + " (" + (int) weight + " g)";
	}

	public String getSlashLine() {
		return (int) calorie + "/" + (int) fat + "/" + (int) carb + "/" + (int) protein;
	}
}
