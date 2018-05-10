package edu.qc.cs370.macrotracker;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class AddMealActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_meal);
  }

  public void scanBarcode(View view) {
    Intent intent = new Intent(this, BarcodeScannerActivity.class);
    startActivity(intent);
  }

  public void addFoodToMeal(View view) {
    String message = "The added food items will appear above as a list.";
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }
}
