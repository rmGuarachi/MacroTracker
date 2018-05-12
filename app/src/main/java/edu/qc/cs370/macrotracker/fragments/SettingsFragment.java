package edu.qc.cs370.macrotracker.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import edu.qc.cs370.macrotracker.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SettingsFragment extends Fragment {

  public SettingsFragment() {
    // Required constructor
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_settings, parent, false);

    ListView settingsList = view.findViewById(R.id.settingsList);

    HashMap<String, String> settingOptionsWithEnteredInfo = new HashMap<>();
    settingOptionsWithEnteredInfo.put("Calories", "2400");
    settingOptionsWithEnteredInfo.put("Fat", "60");
    settingOptionsWithEnteredInfo.put("Carbs", "305");
    settingOptionsWithEnteredInfo.put("Protein", "160");

    List<HashMap<String, String>> listItems = new ArrayList<>();
    SimpleAdapter adapter = new SimpleAdapter(
        getContext(),
        listItems,
        R.layout.list_item,
        new String[] {"First Line", "Second Line"},
        new int[] {R.id.mainItemTitle, R.id.subItemTitle});

    Iterator it = settingOptionsWithEnteredInfo.entrySet().iterator();
    while(it.hasNext()) {
      HashMap<String, String> resultsMap = new HashMap<>();
      Map.Entry pair = (Map.Entry) it.next();
      resultsMap.put("First Line", pair.getKey().toString());
      resultsMap.put("Second Line", pair.getValue().toString());
      listItems.add(resultsMap);
    }

    settingsList.setAdapter(adapter);

    return view;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    // Setup any handles to view objects here
  }
}
