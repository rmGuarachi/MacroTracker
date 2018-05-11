package edu.qc.cs370.macrotracker.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.qc.cs370.macrotracker.R;
import edu.qc.cs370.macrotracker.macro.*;

public class LouSummaryFragment extends Fragment {
  Date currentDate = Calendar.getInstance().getTime();
  SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM dd, yyyy");
  String formattedDate;
  Menu menu;

  public LouSummaryFragment() {

  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_summary, parent, false);


    //Populate Meal from a given profile's menu - LX
    List<Meal> today_Menu = menu.getMeals();

    ListView mealsOfTheDayList = view.findViewById(R.id.mealsOfTheDayList);
    List<HashMap<String, String>> listItems = new ArrayList<>();
    ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.list_item, today_Menu);

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
    float currentFat = (float)menu.getFat();
    float currentCarbs = (float)menu.getCarb();
    float currentProtein = (float)menu.getCalorie();

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