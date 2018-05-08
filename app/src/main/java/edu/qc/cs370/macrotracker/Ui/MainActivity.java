package edu.qc.cs370.macrotracker.Ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.qc.cs370.macrotracker.R;

public class MainActivity extends AppCompatActivity {
    // Gets the current date
    SimpleDateFormat formatter = new SimpleDateFormat("EEEE dd, yyyy");
    Date date = new Date();

    // Sets up the nav bar
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
        = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_summary:
                    mTextMessage.setText(R.string.nav_summary);
                    return true;
                case R.id.nav_reports:
                    mTextMessage.setText(R.string.nav_reports);
                    return true;
                case R.id.nav_settings:
                    mTextMessage.setText(R.string.nav_settings);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting the current date to the top of the main activity
        TextView currentDate = findViewById(R.id.currentDateTextView);
        currentDate.setText(formatter.format(date));

        // Setting the current and remaining macros
        TextView currentFat = findViewById(R.id.remainingFat);
        currentFat.setText("46" + "/" + "60");
        TextView currentCarbs = findViewById(R.id.remainingCarbs);
        currentCarbs.setText("200" + "/" + "240");
        TextView currentProtein = findViewById(R.id.remainingProtein);
        currentProtein.setText("112" + "/" + "160");

        // Setting up demo line graph
        GraphView graph = findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
            new DataPoint(0, 1),
            new DataPoint(1, 5),
            new DataPoint(2, 3),
            new DataPoint(3, 2),
            new DataPoint(4, 6)
        });
        graph.addSeries(series);

        // Attaching on-click listener to fab
        FloatingActionButton addMealBtn = findViewById(R.id.addMealBtn);
        addMealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBarcodeScanner();
            }
        });

        // Sets up the nav bar
        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void startBarcodeScanner() {
        Intent intent = new Intent(this, BarcodeScannerActivity.class);
        startActivity(intent);
    }
}
