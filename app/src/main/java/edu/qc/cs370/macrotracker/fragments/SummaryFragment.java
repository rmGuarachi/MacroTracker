package edu.qc.cs370.macrotracker.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import edu.qc.cs370.macrotracker.macro.Meal;
import edu.qc.cs370.macrotracker.macro.Menu;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import edu.qc.cs370.macrotracker.R;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SummaryFragment extends Fragment {
  Date currentDate = Calendar.getInstance().getTime();
  SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE MMMM dd, yyyy");
  String formattedDate;

  //Specific profile's Menu object; this enables viewing previous day's records - LX
  Menu menu = new Menu();

  public SummaryFragment() {
    // Required constructor
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_summary, parent, false);

    /* Commenting out my previous code in order to add Louis' changes - DV
    ListView mealsOfTheDayList = view.findViewById(R.id.mealsOfTheDayList);
    HashMap<String, String> mealsList = new HashMap<>();
    mealsList.put("Breakfast", "465/12/58/32");
    mealsList.put("Lunch", "410/15/35/38");
    mealsList.put("Dinner", "535/3/75/50");

    List<HashMap<String, String>> listItems = new ArrayList<>();
    SimpleAdapter adapter = new SimpleAdapter(
        getContext(),
        listItems,
        R.layout.list_item,
        new String[] {"First Line", "Second Line"},
        new int[] {R.id.mainItemTitle, R.id.subItemTitle});

    Iterator it = mealsList.entrySet().iterator();
    while(it.hasNext()) {
      HashMap<String, String> resultsMap = new HashMap<>();
      Map.Entry pair = (Map.Entry) it.next();
      resultsMap.put("First Line", pair.getKey().toString());
      resultsMap.put("Second Line", pair.getValue().toString());
      listItems.add(resultsMap);
    }

    mealsOfTheDayList.setAdapter(adapter);
    */

    //Populate Meal from a given profile's menu - LX
    List<Meal> today_Menu = menu.getMeals();

    ListView mealsOfTheDayList = view.findViewById(R.id.mealsOfTheDayList);
    //Use ArrayAdapter to avoid HashMap and ArrayList of same information - LX
    ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, today_Menu);

    mealsOfTheDayList.setAdapter(adapter);

    return view;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    // Setting up the current date
    formattedDate = dateFormatter.format(currentDate);

    TextView currentDate = getView().findViewById(R.id.currentDate);
    currentDate.setText(formattedDate);

    // Begin pie chart code
    PieChart pieChart = getView().findViewById(R.id.pieChart);
    /* We will use the below code, but leaving up dummy data for now. - DV
    // Using methods provided by Menu, Meal classes - LX
    float currentFat = (float) menu.getFat();
    float currentCarbs = (float) menu.getCarb();
    float currentProtein = (float) menu.getCalorie();
    */

    float currentFat = 45.0f;
    float currentCarbs = 195.0f;
    float currentProtein = 115.0f;

    List<PieEntry> entries = new ArrayList<>();
    entries.add(new PieEntry(currentFat, "Fat"));
    entries.add(new PieEntry(currentCarbs, "Carbs"));
    entries.add(new PieEntry(currentProtein, "Protein"));

    PieDataSet dataSet = new PieDataSet(entries, "");

    // Setting up the pie chart colors
    ContextCompat.getColor(getContext(), R.color.colorFat);
    ContextCompat.getColor(getContext(), R.color.colorCarbs);
    ContextCompat.getColor(getContext(), R.color.colorProtein);
    dataSet.setColors(new int[] {R.color.colorFat, R.color.colorCarbs, R.color.colorProtein}, getContext());

    PieData pieData = new PieData(dataSet);
    pieData.setValueTextSize(24.0f);
    ContextCompat.getColor(getContext(), R.color.colorBlack);
    pieData.setValueTextColor(R.color.colorBlack);

    // Setting data and styling the piechart
    pieChart.setData(pieData);
    pieChart.setEntryLabelColor(R.color.colorBlack);
    pieChart.setHoleRadius(0.0f);
    pieChart.setUsePercentValues(true);
    pieChart.setTransparentCircleRadius(0.0f);
    pieChart.setUsePercentValues(false);
    pieChart.setTouchEnabled(false);

    //TODO create an onclick listener to switch the view between percent and actual value

    // Workaround to remove the description label on the bottom right corner
    Description description = new Description();
    description.setText("");
    pieChart.setDescription(description);

    // Removing the chart legend
    Legend legend = pieChart.getLegend();
    legend.setEnabled(false);

    pieChart.invalidate();
  }
}