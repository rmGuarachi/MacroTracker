package edu.qc.cs370.macrotracker.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import edu.qc.cs370.macrotracker.R;
import java.util.Date;
import java.util.List;

public class SummaryFragment extends Fragment {
  Date currentDate = Calendar.getInstance().getTime();
  SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM dd, yyyy");
  String formattedDate;

  public SummaryFragment() {
    // Required constructor
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_summary, parent, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    // Setting up the current date
    formattedDate = dateFormatter.format(currentDate);

    TextView currentDate = getView().findViewById(R.id.currentDate);
    currentDate.setText(formattedDate);

    // Begin pie chart code
    PieChart pieChart = getView().findViewById(R.id.pieChart);
    float currentFat = 29.0f;
    float currentCarbs = 168.0f;
    float currentProtein = 120.0f;

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