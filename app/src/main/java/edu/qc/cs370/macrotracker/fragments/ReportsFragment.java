package edu.qc.cs370.macrotracker.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import edu.qc.cs370.macrotracker.MainController;
import edu.qc.cs370.macrotracker.R;
import edu.qc.cs370.macrotracker.macro.Profile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportsFragment extends Fragment {
  SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

  public ReportsFragment() {
    // Required constructor
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_reports, parent, false);

    return view;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    LineChart caloriesChart = getView().findViewById(R.id.calorieReportChart);
    LineChart fatChart = getView().findViewById(R.id.fatReportChart);
    LineChart carbsChart = getView().findViewById(R.id.carbsReportChart);
    LineChart proteinChart = getView().findViewById(R.id.proteinReportChart);

    List<Entry> caloriesEntries = new ArrayList<Entry>();
    List<Entry> fatEntries = new ArrayList<Entry>();
    List<Entry> carbsEntries = new ArrayList<Entry>();
    List<Entry> proteinEntries = new ArrayList<Entry>();

    // TODO Figure out how to set the x-axis to the days of the week -- Entry constructor can't take Date, only int
    //---------------------------------------------------
    //Automate line graph values by using the number of entries inside of a Menu object.
    //The x coordinates would be the index inside a Menu object(Menu acts similar to stack)
    //The y coordinates are the corresponding calorie, fat, carb, and protien. - LX
    Profile profile = MainController.getProfile();
    for(int i = 0; i < profile.getMenu().size(); i++) {
      caloriesEntries.add( new Entry(i + 1, (int)(profile.getMenu().get(i).getCalorie() * 100) ) );
      fatEntries.add( new Entry(i + 1, (int)(profile.getMenu().get(i).getFat() * 100) ) );
      carbsEntries.add( new Entry(i + 1, (int)(profile.getMenu().get(i).getCarb() * 100) ) );
      proteinEntries.add( new Entry(i + 1, (int)(profile.getMenu().get(i).getProtein() * 100) ) );
    }



    /*
    caloriesEntries.add(new Entry(1, 2000));
    caloriesEntries.add(new Entry(2, 2100));
    caloriesEntries.add(new Entry(3, 1800));
    caloriesEntries.add(new Entry(4, 1900));
    caloriesEntries.add(new Entry(5, 2200));
    caloriesEntries.add(new Entry(6, 2400));

    fatEntries.add(new Entry(1, 55));
    fatEntries.add(new Entry(2, 51));
    fatEntries.add(new Entry(3, 46));
    fatEntries.add(new Entry(4, 50));
    fatEntries.add(new Entry(5, 62));
    fatEntries.add(new Entry(6, 52));

    carbsEntries.add(new Entry(1, 204));
    carbsEntries.add(new Entry(2, 212));
    carbsEntries.add(new Entry(3, 244));
    carbsEntries.add(new Entry(4, 269));
    carbsEntries.add(new Entry(5, 302));
    carbsEntries.add(new Entry(6, 287));

    proteinEntries.add(new Entry(1, 185));
    proteinEntries.add(new Entry(2, 193));
    proteinEntries.add(new Entry(3, 201));
    proteinEntries.add(new Entry(4, 227));
    proteinEntries.add(new Entry(5, 199));
    proteinEntries.add(new Entry(6, 203));
    */

    LineDataSet caloriesDataSet = new LineDataSet(caloriesEntries, "");
    LineDataSet fatDataSet = new LineDataSet(fatEntries, "");
    LineDataSet carbsDataSet = new LineDataSet(carbsEntries, "");
    LineDataSet proteinDataSet = new LineDataSet(proteinEntries, "");

    LineData caloriesData = new LineData(caloriesDataSet);
    LineData fatData = new LineData(fatDataSet);
    LineData carbsData = new LineData(carbsDataSet);
    LineData proteinData = new LineData(proteinDataSet);

    caloriesChart.setData(caloriesData);
    fatChart.setData(fatData);
    carbsChart.setData(carbsData);
    proteinChart.setData(proteinData);

    Description description = new Description();
    description.setText("");
    caloriesChart.setDescription(description);
    fatChart.setDescription(description);
    carbsChart.setDescription(description);
    proteinChart.setDescription(description);

    Legend caloriesLegend = caloriesChart.getLegend();
    caloriesLegend.setEnabled(false);
    Legend fatLegend = fatChart.getLegend();
    fatLegend.setEnabled(false);
    Legend carbsLegend = carbsChart.getLegend();
    carbsLegend.setEnabled(false);
    Legend proteinLegend = proteinChart.getLegend();
    proteinLegend.setEnabled(false);

    caloriesChart.setPinchZoom(false);
    fatChart.setPinchZoom(false);
    carbsChart.setPinchZoom(false);
    proteinChart.setPinchZoom(false);

    caloriesChart.setDrawGridBackground(false);
    fatChart.setDrawGridBackground(false);
    carbsChart.setDrawGridBackground(false);
    proteinChart.setDrawGridBackground(false);

    caloriesChart.invalidate();
    fatChart.invalidate();
    carbsChart.invalidate();
    proteinChart.invalidate();
  }


}
