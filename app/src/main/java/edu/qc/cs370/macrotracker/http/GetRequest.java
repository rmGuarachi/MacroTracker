package edu.qc.cs370.macrotracker.http;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.view.View;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

/*   URL FORMAT
       UPC search - http://ec2-18-188-255-3.us-east-2.compute.amazonaws.com:8080/MacroTrackerServletv1/foodupc?upc={ENTER UPC HERE}
       Name search - http://ec2-18-188-255-3.us-east-2.compute.amazonaws.com:8080/MacroTrackerServletv1/foods?name={ENTER NAME HERE}
 */

public class

GetRequest {
  // Initialize the requestQueue, used for actually sending out the response.
  private static RequestQueue requestQueue;
  // Initialize the JsonObjectRequest, this will be changed to JSONRequest later on.
  private static JsonObjectRequest jsonRequest;
  // Setting up the TextView array to set the text views after scanning.
  private static TextView[] views;

  public static void getViaUPC(Context context, TextView[] passedViews, String upc) {
    // The URL endpoint for UPC API calls
    String url = "http://ec2-18-188-255-3.us-east-2.compute.amazonaws.com:8080/MacroTrackerServletv1/foodupc?upc=" + upc;

    // Setting the RequestQueue variable to the context of the calling fragment.
    requestQueue = Volley.newRequestQueue(context);
    // Setting up the TextView array to set the information from this method
    views = passedViews;
    // The actual request to the API
    jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject response) {
        // On successful response, set the TextView to the entire output of the response (in this case, JSON).
        try {
          // Deconstructing the json data into separate strings and setting them into the array that will be returned.

          // Parsing the name of the food item; capitalizing the first letter, lowercasing the remaining name, and removing the UPC
          String foodName = response.getString("name").toLowerCase();
          foodName = foodName.substring(0, 1).toUpperCase() + foodName.substring(1, foodName.indexOf(","));
          views[0].setText(foodName);

          String foodCals = response.getString("Energy");
          views[1].setText(foodCals);

          // Very sloppy way of removing the decimal point from the fat, carbs, and protein to fit inside the text views.
          String foodFat = response.getString("Total lipid (fat)");

          if (foodFat.compareTo("--") != 0 && foodFat.indexOf(".") != -1) {
            foodFat = foodFat.substring(0, foodFat.indexOf("."));
          } else {
            foodFat = "0";
          }
          views[2].setText(foodFat);

          String foodCarbs = response.getString("Carbohydrate, by difference");

          if (foodCarbs.compareTo("--") != 0 && foodCarbs.indexOf(".") != -1) {
            foodCarbs = foodCarbs.substring(0, foodCarbs.indexOf("."));
          } else {
            foodCarbs = "0";
          }
          views[3].setText(foodCarbs);

          String foodProtein = response.getString("Protein");

          if (foodProtein.compareTo("--") != 0 && foodProtein.indexOf(".") != -1) {
            foodProtein = foodProtein.substring(0, foodProtein.indexOf("."));
          } else {
            foodProtein = "0";
          }
          views[4].setText(foodProtein);

          Log.i("ScannedFood", response.toString());
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
      }
    });

    // Add the JsonObjectRequest to the queue -- this is what actually makes the request! This is also asynchronous.
    requestQueue.add(jsonRequest);
  }
}
