package edu.qc.cs370.macrotracker.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;
import edu.qc.cs370.macrotracker.R;
import edu.qc.cs370.macrotracker.macro.Food;
import java.util.ArrayList;

public class CustomFoodItemAdapter extends BaseAdapter {
  private Context context;
  private ArrayList<Food> foodItems;

  public CustomFoodItemAdapter(Context context, ArrayList<Food> foodItems) {
    this.context = context;
    this.foodItems = foodItems;
  }

  @Override
  public int getCount() {
    return foodItems.size();
  }

  @Override
  public Food getItem(int position) {
    return foodItems.get(position);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View twoLineListItem;
    if (convertView == null) {
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      twoLineListItem = inflater.inflate(R.layout.small_list_item, null);
    } else {
      twoLineListItem = convertView;
    }

    /*
    TextView mainText = twoLineListItem.getText1();
    TextView subText = twoLineListItem.getText2();
    */

    TextView mainText = twoLineListItem.findViewById(R.id.mainItemTitle);
    TextView subText= twoLineListItem.findViewById(R.id.subItemTitle);

    mainText.setText(foodItems.get(position).getNameAndWeight());
    subText.setText(foodItems.get(position).getSlashLine());

    return twoLineListItem;
  }
}
