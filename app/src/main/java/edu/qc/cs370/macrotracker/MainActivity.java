package edu.qc.cs370.macrotracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import edu.qc.cs370.macrotracker.fragments.ReportsFragment;
import edu.qc.cs370.macrotracker.fragments.SettingsFragment;
import edu.qc.cs370.macrotracker.fragments.SummaryFragment;

public class MainActivity extends AppCompatActivity {

  FragmentManager fragmentManager = getSupportFragmentManager();
  private TextView mTextMessage;

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {
    /*
    Bottom navigation not working at the moment, getting the following error:

    java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.TextView.setText(int)' on a null object reference
        at edu.qc.cs370.macrotracker.MainActivity$1.onNavigationItemSelected(MainActivity.java:32)

    Solution at: https://stackoverflow.com/questions/34892929/attempt-to-invoke-virtual-method-void-android-widget-textview-settextjava-lang

    -DV
    */

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

    // Serve up the summary fragment automatically
    switchToSummaryFragment(fragmentManager);

    mTextMessage = (TextView) findViewById(R.id.message);
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
}
