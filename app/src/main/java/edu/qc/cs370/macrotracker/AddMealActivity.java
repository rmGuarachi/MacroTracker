package edu.qc.cs370.macrotracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import edu.qc.cs370.macrotracker.components.CustomFoodItemAdapter;
import edu.qc.cs370.macrotracker.http.GetRequest;
import edu.qc.cs370.macrotracker.macro.Food;
import edu.qc.cs370.macrotracker.macro.Meal;

public class AddMealActivity extends AppCompatActivity {
  String scannedUPC;
  Meal meal = new Meal("Empty Meal");

  // Implementing a custom adapter to get the simple list item 2 layout functionality back. - DV
  CustomFoodItemAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_meal);

    ListView foodItemsList = findViewById(R.id.foodItemsList);

    // Implementing a custom adapter to get the simple list item 2 layout functionality back. - DV
    adapter = new CustomFoodItemAdapter(this, meal.getFoods());
    foodItemsList.setAdapter(adapter);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 1) {
      if(resultCode == RESULT_OK) {
        scannedUPC = data.getStringExtra("upc");
      }
    }

    TextView foodName = findViewById(R.id.foodName);
    TextView amountOfCalories = findViewById(R.id.amountOfCalories);
    TextView amountOfFat = findViewById(R.id.amountOfFat);
    TextView amountOfCarbs = findViewById(R.id.amountOfCarbs);
    TextView amountOfProtein = findViewById(R.id.amountOfProtein);
    TextView[] foodInformationViews = {foodName, amountOfCalories, amountOfFat, amountOfCarbs, amountOfProtein};

    //Set default weight of food to 100g initially. -LX
    //This default weight should/could later be changed to the USDA recommended serving size - LX
    // TODO find the USDA recommended serving size and plug into line 106.
    TextView weightOfFood = findViewById(R.id.weight);
    weightOfFood.setText("100");

    GetRequest.getViaUPC(this, foodInformationViews, scannedUPC);
  }

  public void scanBarcode(View view) {
    Intent intent = new Intent(this, BarcodeScannerActivity.class);
    startActivityForResult(intent, 1);
  }

  public void addFoodToMeal(View view) {
    //Food objects will now have a weight given by users. -LX

    TextView foodName = findViewById(R.id.foodName);
    TextView amountOfCalories = findViewById(R.id.amountOfCalories);
    TextView amountOfFat = findViewById(R.id.amountOfFat);
    TextView amountOfCarbs = findViewById(R.id.amountOfCarbs);
    TextView amountOfProtein = findViewById(R.id.amountOfProtein);
    TextView weightOfFood = findViewById(R.id.weight); //Enter weight of food.

    String fdName = foodName.getText().toString();
    double fdCals = Double.parseDouble(amountOfCalories.getText().toString());
    double fdFat = Double.parseDouble(amountOfFat.getText().toString());
    double fdCarbs = Double.parseDouble(amountOfCarbs.getText().toString());
    double fdProtein = Double.parseDouble(amountOfProtein.getText().toString());
    //Enter weight of food. -LX
    double fdWeight = Double.parseDouble(weightOfFood.getText().toString());

    Food food = new Food(scannedUPC, fdName, fdCals, fdFat, fdCarbs, fdProtein, fdWeight);
    Log.i("AddedFood", food.getNameAndWeight() + ", " + food.getSlashLine());
    meal.addFood(food);
    adapter.notifyDataSetChanged();
  }
}
