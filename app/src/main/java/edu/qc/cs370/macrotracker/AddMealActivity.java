package edu.qc.cs370.macrotracker;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import edu.qc.cs370.macrotracker.http.GetRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Text;

public class AddMealActivity extends AppCompatActivity {
  String scannedUPC;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_meal);

    HashMap<String, String> foodsList = new HashMap<>();
    ListView foodItemsList = findViewById(R.id.foodItemsList);
    List<HashMap<String, String>> listItems = new ArrayList<>();

//    foodsList.put("Grilled chicken (4 oz)", "188/7/0/26");
//    foodsList.put("Cooked white rice (200 g)", "253/0/56/5");
//    foodsList.put("Steamed vegetables (300 g)", "106/1/24/6");

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
    TextView[] foodInfoTextViews = {foodName, amountOfCalories, amountOfFat, amountOfCarbs, amountOfProtein};
    GetRequest.getViaUPC(this, foodInfoTextViews, scannedUPC);
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
