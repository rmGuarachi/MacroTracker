package edu.qc.cs370.macrotracker;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import edu.qc.cs370.macrotracker.db.Food;
import edu.qc.cs370.macrotracker.db.FoodDao;
import edu.qc.cs370.macrotracker.db.FoodDatabase;
import edu.qc.cs370.macrotracker.db.User;
import edu.qc.cs370.macrotracker.fragments.ReportsFragment;
import edu.qc.cs370.macrotracker.fragments.SettingsFragment;
import edu.qc.cs370.macrotracker.fragments.SummaryFragment;

public class MainActivity extends AppCompatActivity {

  public static FoodDatabase foodDatabase; // created by RG
  FragmentManager fragmentManager = getSupportFragmentManager();
  private TextView textMessage;
  public static User user; // created by RG
  public static List<Food> foods;
  public static int user_total_fat = 0;
  public static int user_total_protein = 0;
  public static int user_total_carbs = 0;
  public static ArrayList<edu.qc.cs370.macrotracker.macro.Food> foodItems;

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_summary:
          // mTextMessage.setText(R.string.title_summary);
          switchToSummaryFragment(fragmentManager);
          return true;
        case R.id.navigation_reports:
          // mTextMessage.setText(R.string.title_reports);
          switchToReportsFragment(fragmentManager);
          return true;
        case R.id.navigation_settings:
          // mTextMessage.setText(R.string.title_settings);
          switchToSettingsFragment(fragmentManager);
          return true;
      }
      return false;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //database connection will init and will allow dbconn to run on mainThread - RG
    foodDatabase = Room.databaseBuilder(getApplicationContext(), FoodDatabase.class, "macroTrackerdb2").allowMainThreadQueries().build();

    //Call to main controller to get the value we need for contorller
    new MainController(foodDatabase);

    // Serve up the summary fragment automatically
    switchToSummaryFragment(fragmentManager);

    textMessage = findViewById(R.id.message);
    BottomNavigationView navigation = findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    setUserInit();
    updateFoods();
  }

  // Boilerplate methods to switch between fragments

  public void switchToSummaryFragment(FragmentManager fragmentManager) {
    fragmentManager.beginTransaction().replace(R.id.fragmentsContainer, new SummaryFragment()).commit();
  }

  public void switchToReportsFragment(FragmentManager fragmentManager) {
    fragmentManager.beginTransaction().replace(R.id.fragmentsContainer, new ReportsFragment()).commit();
  }

  public void switchToSettingsFragment(FragmentManager fragmentManager) {
    fragmentManager.beginTransaction().replace(R.id.fragmentsContainer, new SettingsFragment()).commit();
  }

  
  public void addMeal(View view) {
    Intent intent = new Intent(this, AddMealActivity.class);
    startActivity(intent);
  }

  public static void updateFoods(){
    // written by RG
    foodItems = new ArrayList<>();
    int fat = 0;
    int carbs = 0;
    int protein = 0;
    foods = foodDatabase.foodDao().getFoods(makeStringTodayDate());
    if (foods.size() != 0){
      for(int i = 0; i < foods.size(); i++){
        edu.qc.cs370.macrotracker.macro.Food food = new edu.qc.cs370.macrotracker.macro.Food();
        food.setCalorie(foods.get(i).getCalorie());
        food.setCarb(foods.get(i).getCarbs());
        food.setFat(foods.get(i).getFat());
        food.setFoodName(foods.get(i).getName());
        food.setProtein(foods.get(i).getProtein());
        food.setWeight(foods.get(i).getGrams());
        fat += foods.get(i).getFat();
        carbs += foods.get(i).getCarbs();
        protein += foods.get(i).getProtein();
        foodItems.add(food);
      }
      user_total_carbs = carbs;
      user_total_fat = fat;
      user_total_protein = protein;
    }
  }

  public static void addFoods(edu.qc.cs370.macrotracker.macro.Food food){
    // written by RG
    Food dbFood = new Food();
    dbFood.setCalorie(food.getCalorie());
    dbFood.setCarbs(food.getCarb());
    dbFood.setFat(food.getFat());
    dbFood.setName(food.getFoodName());
    dbFood.setProtein(food.getProtein());
    dbFood.setGrams((int) food.getWeight());
    dbFood.setDate(makeStringTodayDate());

    MainActivity.foodDatabase.foodDao().addFood(dbFood);

    updateFoods();
  }

  public static String makeStringTodayDate(){
    // written by RG
    Date date = new Date(); // your date
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.format(date);
  }

  public void setUserInit(){
    // method created by RG
    int users = foodDatabase.userDao().getUsers().size();
    user = new User();
    if (users == 0){
      user.setName("User");
      foodDatabase.userDao().addUser(user);
    }
    else {
      user = foodDatabase.userDao().getUsers().get(0);
    }
  }
}
