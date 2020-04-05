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
import com.example.northernvillagewaterandsewageapp.ObjectClasses.useItem;
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
    protected ArrayList<useItem> useAnalyticsList;
    protected ArrayList<Integer> numUses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_analytics);

        //get the useItem array to equal something, this is for testing, it should be gotten from the sharedProfile
        useItem use1 = new useItem("Shower", 15);
        useItem use2 = new useItem("Bath", 30);
        useItem use3 = new useItem("Dishes", 45);
        useAnalyticsList.add(use1);
        useAnalyticsList.add(use2);
        useAnalyticsList.add(use3);

        //Make the numUses be as long as useAnalytics, and set them to 0 (this is good code)
        for (int pos = 0; pos < useAnalyticsList.size(); pos++){
            numUses.add(0);
        }

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

        ArrayList<String> usagelistListText = new ArrayList<>();

        //makes a list item
            String temp = "";

        for (int pos = 0; pos < useAnalyticsList.size(); pos++){
            temp += useAnalyticsList.get(pos).getName();
            temp += "\npercent: ";
            temp += useAnalyticsList.get(pos).getUse();
            temp += "     Times: ";
            temp += numUses.get(pos);
        }

            temp += "Estimated Usage: " + estimatedUsage + "\n";

            usagelistListText.add(temp);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usagelistListText);
        myUsageListView.setAdapter(arrayAdapter);
    }

    public void setUpUI() {

        addUsageFloatingButton = findViewById(R.id.addUseFloatingActionButton);
        editSwitch = findViewById(R.id.editUseSwitch);
        progressBarAnalytics = findViewById(R.id.progressBarAnalytics);
        textView = findViewById(R.id.myUsageTextView);
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
