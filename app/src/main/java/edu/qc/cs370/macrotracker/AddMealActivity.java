package edu.qc.cs370.macrotracker;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AddMealActivity extends AppCompatActivity {
//  LinearLayout listOfCurrentFoodInMeal = findViewById(R.id.listOfCurrentFoodInMeal);
  String scannedUPC;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_meal);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 1) {
      if(resultCode == RESULT_OK) {
        scannedUPC = data.getStringExtra("upc");
      }
    }

    // Testing to see if the UPC was sent successfully from the barcode activity
    String upcMessage = "Scanned the following UPC: " + scannedUPC;
    Toast.makeText(this, upcMessage, Toast.LENGTH_SHORT).show();
  }

  public void scanBarcode(View view) {
    Intent intent = new Intent(this, BarcodeScannerActivity.class);
    startActivityForResult(intent, 1);
  }

  public void addFoodToMeal(View view) {
    String message = "The added food items will appear above as a list.";
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }
}
