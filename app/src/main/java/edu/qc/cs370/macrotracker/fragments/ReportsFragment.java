package edu.qc.cs370.macrotracker.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import edu.qc.cs370.macrotracker.R;
import org.json.JSONObject;

public class ReportsFragment extends Fragment {

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
    // Setup any handles to view objects here
    final TextView testingRequest = getView().findViewById(R.id.testText);
    RequestQueue queue = Volley.newRequestQueue(getContext());
    String url = "https://jsonplaceholder.typicode.com/users/1";

    JsonObjectRequest jsonResponse = new JsonObjectRequest(Request.Method.GET, url, null,
        new Listener<JSONObject>() {
          @Override
          public void onResponse(JSONObject response) {
            testingRequest.setText("Response: " + response.toString());
          }
        }, new ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        // Handle error
      }
    });

  }
}
