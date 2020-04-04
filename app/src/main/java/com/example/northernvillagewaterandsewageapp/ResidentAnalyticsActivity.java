package com.example.northernvillagewaterandsewageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.northernvillagewaterandsewageapp.Fragments.ResidentUsageFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ResidentAnalyticsActivity extends AppCompatActivity {

    protected ListView myUsageListView;
    protected FloatingActionButton addUsageFloatingButton;
    protected Switch editSwitch;
    protected ProgressBar progressBarAnalytics;
    protected TextView textView;
    protected SharedPreferenceHelper sharedPreferenceHelper;
    private int estimatedUsage;
    private int dishes;
    private int washes;
    private int showers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_analytics);

        addUsageFloatingButton = findViewById(R.id.addUseFloatingActionButton);
        editSwitch = findViewById(R.id.editUseSwitch);
        progressBarAnalytics = findViewById(R.id.progressBarAnalytics);
        textView = findViewById(R.id.myUsageTextView);
        myUsageListView = findViewById(R.id.residentUseListView);

        editSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.editUseSwitch:
                        if (editSwitch.isChecked()) {
                            addUsageFloatingButton.setEnabled(true);
                        }
                        else {
                            addUsageFloatingButton.setEnabled(false);
                        }
                }
            }
        });

        progressBarAnalytics.setProgress(20);
        loadListView();
        setUpUI();

    }

    //LoadListViewFunction
    protected void loadListView() {
        sharedPreferenceHelper = new SharedPreferenceHelper(ResidentAnalyticsActivity.this);

        String estimatedUsage = sharedPreferenceHelper.getEstUsage(getString(R.string.estimated_usage));
        String dishes = sharedPreferenceHelper.getDishes(getString(R.string.dish_washes));
        String washes = sharedPreferenceHelper.getWashes(getString(R.string.washes));
        String showers = sharedPreferenceHelper.getShowers(getString(R.string.showers));

        ArrayList<String> usagelistListText = new ArrayList<>();

        //makes a list item

            String temp = "";
            temp += "Estimated Usage: " + estimatedUsage + "\n";
            temp += "Dish washes: " + dishes + "\n";
            temp += "Washes: " + washes + "\n";
            temp += "Showers: " + showers + "\n";

            usagelistListText.add(temp);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usagelistListText);
        myUsageListView.setAdapter(arrayAdapter);
    }

    public void setUpUI() {

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
