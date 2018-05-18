package edu.qc.cs370.macrotracker.fragments;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import edu.qc.cs370.macrotracker.MainActivity;
import edu.qc.cs370.macrotracker.MainController;
import edu.qc.cs370.macrotracker.db.Food;
import edu.qc.cs370.macrotracker.db.User;
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
  TextView nameView;

  //Specific profile's Menu object; this enables viewing previous day's records - LX
  //Menu menu = new Menu(); //Variable no longer needed. Will use MainController - LX

  public SummaryFragment() {
    // Required constructor
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_summary, parent, false);

    // TODO Create a CustomSummaryMealItemsAdapter java class. - DV

    //Populate Meal from a given profile's menu - LX
    //List<Meal> today_Menu = menu.getMeals(); //Variable no longer needed. Will use MainController - LX

    ListView mealsOfTheDayList = view.findViewById(R.id.mealsOfTheDayList);
    //Use ArrayAdapter to avoid HashMap and ArrayList of same information - LX

    /* Commenting out this code for now, app crashes if left in. - DV
     */
    ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, MainController.getProfile().getTodayMenu().getMeals());

    mealsOfTheDayList.setAdapter(adapter);
    /**/
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

    /*************************************************************************/
    /* We will use the below code, but leaving up dummy data for now. - DV
    // Using methods provided by Menu, Meal classes - LX
    */

    Menu today_menu = MainController.getProfile().getTodayMenu();
    float currentFat = (float) today_menu.getFat();
    float currentCarbs = (float) today_menu.getCarb();
    float currentProtein = (float) today_menu.getProtein();

  /*
    float currentFat = 45.0f;
    float currentCarbs = 195.0f;
    float currentProtein = 115.0f;

    */

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

    try{
      User user = new User();
      user.setName("ny mets is saved in sqlite1");
      MainActivity.foodDatabase.userDao().addUser(user);
    } catch (SQLiteConstraintException E){

    }
    List<User> users = MainActivity.foodDatabase.userDao().getUsers();
    nameView = getView().findViewById(R.id.name);
    int num = users.size() - 1;
    nameView.setText(users.get(num).getName());
  }
}