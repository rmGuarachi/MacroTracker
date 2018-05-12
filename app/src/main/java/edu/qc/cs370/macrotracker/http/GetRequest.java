package edu.qc.cs370.macrotracker.http;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.view.View;
import android.widget.TextView;

public class GetRequest {
  // Initialize the requestQueue, used for actually sending out the response.
  private static RequestQueue requestQueue;
  // Initialize the stringRequest, this will be changed to JSONRequest later on.
  private static StringRequest stringRequest;
  // Initializing a variable to set the incoming TextView to, from the calling activity.
  private static TextView textView;

  public static void testRequest(Context context, TextView text, String url) {
    // Setting the RequestQueue variable to the context of the calling fragment.
    requestQueue = Volley.newRequestQueue(context);
    // Setting the TextView variable to the view of the calling fragment.
    textView = text;
    // The actual request to the API
    stringRequest = new StringRequest(Request.Method.GET, url, new Listener<String>() {
      @Override
      public void onResponse(String response) {
        // On successful reponse, set the TextView to the entire output of the response (in this case, JSON).
        textView.setText(response.toString());
      }
    }, new ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        textView.setText(error.toString());
      }
    });

    // Add the StringRequest to the queue -- this is what actually makes the request! This is also asynchronous.
    requestQueue.add(stringRequest);
  }
}
