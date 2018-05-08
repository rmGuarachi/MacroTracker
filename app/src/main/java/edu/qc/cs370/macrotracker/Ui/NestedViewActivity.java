package edu.qc.cs370.macrotracker.Ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.qc.cs370.macrotracker.Macro.Profile;
import edu.qc.cs370.macrotracker.R;

public class NestedViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lx_nested);

        Profile profile = new Profile(2400, 800,800, 400);

        final TextView tvCal = (TextView) findViewById(R.id.menuCal);
        final TextView tvCarb = (TextView) findViewById(R.id.menuCarb);
        final TextView tvProtein = (TextView) findViewById(R.id.menuProtein);
        final TextView tvFat = (TextView) findViewById(R.id.menuFat);

        tvCal.setText(profile.getCalorie() + " cal");
        tvCarb.setText(profile.getCarb() + " g");
        tvProtein.setText(profile.getProtein() + " g");
        tvFat.setText(profile.getFat() + " g");
    }
}
