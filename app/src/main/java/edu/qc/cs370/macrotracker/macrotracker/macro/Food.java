package edu.qc.cs370.macrotracker.macrotracker.macro;

public class Food {
	// Changed foodId from int to String because UPC may have a leading 0 and that will cause Interger.parseInt(String s) to throw an error
	protected String foodId;
	protected String foodName;
	protected double weight = 0, calorie, carb, protein, fat;

	// Constructor without weight
	public Food(String id, String name, double calorie, double fat, double carb, double protein) {
		this.foodId = id;
		this.foodName = name;
		this.calorie = calorie;
		this.carb = carb;
		this.protein = protein;
		this.fat = fat;
	}

	// Constructor with weight
	public Food(String id, String name, double calorie, double fat, double carb, double protein, double weight) {
		this.foodId = id;
		this.foodName = name;
		this.calorie = calorie;
		this.carb = carb;
		this.protein = protein;
		this.fat = fat;
		this.weight = weight;
	}

	public String toString() {
		/* Commenting out since we don't need json at the moment. -DV
		String json = "{ \"id\":" + foodId + ", \"name\":\"" + foodName + "\", " + "\"weight\": " + weight + ", "
				+ "\"calories\": " + getCalorie() + ", " + "\"carb\":" + getCarb() + ", "
				+ "\"protein\":" + getProtein() + ", " + "\"fat\":" + getFat() + " }";
		return json;
		*/

		return getNameAndWeight() + ", " + getSlashLine();
	}

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
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
		return calorie;
	}

	public void setCalorie(double calorie) {
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

	// Added the below two toString methods for adding to the listview in the AddMeal Activity - DV
	public String getNameAndWeight() {
		return foodName + " (" + (int) weight + " g)";
	}

	public String getSlashLine() {
		return (int) (calorie * (weight / 100))+ "/" + (int) (fat * (weight / 100)) + "/" + (int) (carb * (weight / 100)) + "/" + (int) (protein * (weight / 100));
	}
}
