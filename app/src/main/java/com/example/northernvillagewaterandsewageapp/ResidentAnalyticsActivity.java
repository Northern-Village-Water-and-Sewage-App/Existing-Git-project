package com.example.northernvillagewaterandsewageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.northernvillagewaterandsewageapp.Fragments.ResidentUsageFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ResidentAnalyticsActivity extends AppCompatActivity {

    protected ListView myUsageListView;
    protected FloatingActionButton addUsageFloatingButton;
    protected Switch editSwitch;
    protected ProgressBar progressBarAnalytics;
    protected TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_analytics);

        addUsageFloatingButton = findViewById(R.id.addUseFloatingActionButton);
        editSwitch = findViewById(R.id.editUseSwitch);
        progressBarAnalytics = findViewById(R.id.progressBarAnalytics);
        textView = findViewById(R.id.myUsageTextView);

        /*editSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.editUseSwitch) {
                    addUsageFloatingButton.setEnabled(editSwitch.isChecked());
                }
            }
        });*/

        setUpUI();

    }

    public void setUpUI() {
        myUsageListView = findViewById(R.id.residentUseListView);

        addUsageFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUsage();
            }
        });

    }

    //Function to open fragment for adding/updating resident's water usage
    protected void addUsage() {
        ResidentUsageFragment residentUsageFragment = new ResidentUsageFragment();
        residentUsageFragment.show(getSupportFragmentManager(), "Dialog");
    }

}
