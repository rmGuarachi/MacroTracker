package edu.qc.cs370.macrotracker;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import edu.qc.cs370.macrotracker.http.GetRequest;
import edu.qc.cs370.macrotracker.macro.Food;
import edu.qc.cs370.macrotracker.macro.Meal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Text;

public class AddMealActivity extends AppCompatActivity {
  String scannedUPC;
  Meal meal = new Meal("Empty Meal");
  ArrayAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_meal);

    ListView foodItemsList = findViewById(R.id.foodItemsList);

    //Using ArrayAdapter instead of SimpleAdapter to accept ArrayList<Food> from meal. -LX
    /*
    HashMap<String, String> foodsList = new HashMap<>();
    List<HashMap<String, String>> listItems = new ArrayList<>();

    SimpleAdapter adapter = new SimpleAdapter(
        this,
        listItems,
        R.layout.small_list_item,
        new String[] {"First Line", "Second Line"},
        new int[] {R.id.mainItemTitle, R.id.subItemTitle});

    Iterator it = foodsList.entrySet().iterator();
    while(it.hasNext()) {
      HashMap<String, String> resultsMap = new HashMap<>();
      Map.Entry pair = (Map.Entry) it.next();
      resultsMap.put("First Line", pair.getKey().toString());
      resultsMap.put("Second Line", pair.getValue().toString());
      listItems.add(resultsMap);
    }
    */

    //Populate Menu from a given Menu' meal - LX
    //tar_meal no longer used, Meal object's food list is directly passed to ArrayAdapter -LX
    //List<Food> tar_meal = meal.getFoods();

    //Use ArrayAdapter to avoid HashMap and ArrayList of same information - LX
    // TODO The hashmap was for mapping the elements into the custom list_item layout, we can
    // TODO use the ArrayAdapter but we need to see if there's a way to also map into the custom
    // TODO layout; simple_list_item_1 is currently printing the entire food Object to string as a
    // TODO JSON object for some reason. Need to read the docs to see if that's the adapter or the
    // TODO the ListView layout. - DV
    //
    // Food object's have a toString() that print out the object as a JSON object.
    // This was intended for the web app we originally spoke of. - LX

    adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, meal.getFoods());
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
    TextView weightOfFood = findViewById(R.id.weight);
    weightOfFood.setText("100");

    GetRequest.getViaUPC(this, foodInformationViews, scannedUPC);
  }

  public void scanBarcode(View view) {
    Intent intent = new Intent(this, BarcodeScannerActivity.class);
    startActivityForResult(intent, 1);
  }

  public void addFoodToMeal(View view) {
    // TODO While the meal gets correctly added and the listview is successfully updated after pressing the button,
    // TODO the actual meal info is shown on the front end as all zeros, but the actual Food object has the correct
    // TODO information, as can be seen from the Log.i below. - DV
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
    //Enter wieght of food.
    double fdWeight = Double.parseDouble(weightOfFood.getText().toString());

    Food food = new Food(scannedUPC, fdName, fdCals, fdFat, fdCarbs, fdProtein, fdWeight);
    Log.i("AddedFood", food.getNameAndWeight() + ", " + food.getSlashLine());
    meal.addFood(food);
    adapter.notifyDataSetChanged();
  }
}
