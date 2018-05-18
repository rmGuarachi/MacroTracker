package edu.qc.cs370.macrotracker.macrotracker;

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

import edu.qc.cs370.macrotracker.AddMealActivity;
import edu.qc.cs370.macrotracker.MainController;
import edu.qc.cs370.macrotracker.R;
import edu.qc.cs370.macrotracker.db.Food;
import edu.qc.cs370.macrotracker.db.FoodDatabase;
import edu.qc.cs370.macrotracker.db.User;
import edu.qc.cs370.macrotracker.fragments.ReportsFragment;
import edu.qc.cs370.macrotracker.fragments.SettingsFragment;
import edu.qc.cs370.macrotracker.fragments.SummaryFragment;

public class MainActivity extends AppCompatActivity {

  public static edu.qc.cs370.macrotracker.macrotracker.db.FoodDatabase foodDatabase;
  FragmentManager fragmentManager = getSupportFragmentManager();
  private TextView textMessage;
  public static edu.qc.cs370.macrotracker.macrotracker.db.User user;

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
    foodDatabase = Room.databaseBuilder(getApplicationContext(), edu.qc.cs370.macrotracker.macrotracker.db.FoodDatabase.class, "macroTrackerdb1").allowMainThreadQueries().build();

    //Call to main controller to get the value we need for contorller
    new edu.qc.cs370.macrotracker.macrotracker.MainController(foodDatabase);

    // Serve up the summary fragment automatically
    switchToSummaryFragment(fragmentManager);

    textMessage = findViewById(R.id.message);
    BottomNavigationView navigation = findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    setUserInit();
  }

  // Boilerplate methods to switch between fragments

  public void switchToSummaryFragment(FragmentManager fragmentManager) {
    fragmentManager.beginTransaction().replace(R.id.fragmentsContainer, new edu.qc.cs370.macrotracker.macrotracker.fragments.SummaryFragment()).commit();
  }

  public void switchToReportsFragment(FragmentManager fragmentManager) {
    fragmentManager.beginTransaction().replace(R.id.fragmentsContainer, new edu.qc.cs370.macrotracker.macrotracker.fragments.ReportsFragment()).commit();
  }

  public void switchToSettingsFragment(FragmentManager fragmentManager) {
    fragmentManager.beginTransaction().replace(R.id.fragmentsContainer, new edu.qc.cs370.macrotracker.macrotracker.fragments.SettingsFragment()).commit();
  }

  
  public void addMeal(View view) {
    Intent intent = new Intent(this, edu.qc.cs370.macrotracker.macrotracker.AddMealActivity.class);
    startActivity(intent);
  }

  public void setUserInit(){
    int users = foodDatabase.userDao().getUsers().size();
    user = new edu.qc.cs370.macrotracker.macrotracker.db.User();
    if (users == 0){
      user.setName("User");
      foodDatabase.userDao().addUser(user);
    }
    else {
      user = foodDatabase.userDao().getUsers().get(0);
    }
  }
}
