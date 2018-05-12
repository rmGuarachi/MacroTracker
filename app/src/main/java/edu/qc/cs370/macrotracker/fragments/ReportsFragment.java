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
    final View view = inflater.inflate(R.layout.fragment_reports, parent, false);

    // TODO try to figure out how to incorporate an onClick attribute for HTTP requests
    // TODO instead of having to use onClickListeners (since they force you to declare
    // TODO variables as final, etc.) - DV

    Button requestBtn = view.findViewById(R.id.requestBtn);
    final TextView testText = view.findViewById(R.id.testText);
    final String url = "https://jsonplaceholder.typicode.com/users/1";

    requestBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // The below returns a string response, as opposed to a JSON object response, for testing purposes. - DV
        GetRequest.testRequest(getContext(), testText, url);
      }
    });

    return view;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    // Setup any handles to view objects here
  }
}
