package edu.qc.cs370.macrotracker.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import edu.qc.cs370.macrotracker.R;
import edu.qc.cs370.macrotracker.http.GetRequest;

public class ReportsFragment extends Fragment {
  public ReportsFragment() {
    // Required constructor
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_reports, parent, false);
    TextView testText = view.findViewById(R.id.testText);
    String url = "https://jsonplaceholder.typicode.com/users/1";

    GetRequest.testRequest(getContext(), testText, url);

    return view;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    // Setup any handles to view objects here
  }
}
