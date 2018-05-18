package holder.DataModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Menu {
    //Using SimpleDataFormat because java.lang.NoClassDefFoundError: Failed resolution of: Ljava/time/format/DateTimeFormatter; - LX
    static final SimpleDateFormat DTF = new SimpleDateFormat("dd/MM");

    String menuName;
    Date day;
    ArrayList<Meal> meals;

    public Menu() {
        menuName = "";
        day = Calendar.getInstance().getTime();
        meals = new ArrayList<Meal>();
        meals.add(new Meal());
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
    }

    public static SimpleDateFormat getDTF() { return DTF; }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDay() {
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
