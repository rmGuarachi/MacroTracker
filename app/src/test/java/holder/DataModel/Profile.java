package holder.DataModel;

import java.util.ArrayList;
import java.util.Calendar;

public class Profile {
    //Daily recomended values
    // TODO DRV kCal, Carb, Protein, Fat
    static final double WATER = 16;

    int calorie; // Daily kCal target
    double carb, protein, fat, water; // Daily targets in g and oz
    // Apply sort by date
    ArrayList<Menu> menus; // List of menus belong to this profile

    public Profile() {
        this(2200, 343, 99, 65, WATER);
    }

    public Profile(int calorie, double carb, double protein, double fat) {
        this(calorie, carb, protein, fat, WATER);
    }

    public Profile(int calorie, double carb, double protein, double fat, double water) {
        this.calorie = calorie;
        this.carb = carb;
        this.protein = protein;
        this.fat = fat;
        this.water = water;

        menus = new ArrayList<Menu>();
        menus.add(new Menu());
    }

    //TODO getTodayMenu
    public Menu getTodayMenu() {
        Menu today_menu = null;
        String today = Menu.getDTF().format(Calendar.getInstance().getTime() );
        for(Menu i: menus) {
            if(i.getDay() == today)
                today_menu = i;
        }
        if(today_menu == null) {
            today_menu = new Menu();
            menus.add(today_menu);
        }

        return today_menu;
    }
    public ArrayList<Menu> getMenus() { return menus; }

    public int getCalorie() { return calorie; }
    public void setCalorie(int calorie) { this.calorie = calorie; }
    public double getCarb() { return carb; }
    public void setCarb(double carb) { this.carb = carb; }
    public double getProtein() { return protein; }
    public void setProtein(double protein) { this.protein = protein; }
    public double getFat() { return fat; }
    public void setFat(double fat) { this.fat = fat; }
    public double getWater() { return water; }
    public void setWater(double water) { this.water = water; }
}
