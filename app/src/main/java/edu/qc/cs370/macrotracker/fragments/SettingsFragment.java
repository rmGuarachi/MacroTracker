package edu.qc.cs370.macrotracker.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import edu.qc.cs370.macrotracker.R;
import java.util.ArrayList;

public class SettingsFragment extends Fragment {
  ArrayAdapter adapter;

  public SettingsFragment() {
    // Required constructor
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_settings, parent, false);

    ListView settingsList = view.findViewById(R.id.settingsList);

    // TODO Create a CustomSettingsAdapter java class. - DV
    // Implementing an arrayadapter for settings list view. - DV
    final ArrayList<String> settings = new ArrayList<>();
    settings.add("Name: ");
    settings.add("DOB: ");
    settings.add("Weight: ");
    settings.add("Calories: ");
    settings.add("Fat: ");
    settings.add("Carbs: ");
    settings.add("Protein: ");

    adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, settings);
    settingsList.setAdapter(adapter);

    // Adding an action listener for the list view. - DV
    settingsList.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO add the on click events for each list item here. - DV
      }
    });

    return view;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    // Setup any handles to view objects here
  }
}
