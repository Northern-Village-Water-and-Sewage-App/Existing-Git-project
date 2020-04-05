package com.example.northernvillagewaterandsewageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ResidentAnalyticsActivity extends AppCompatActivity {

    protected ListView myUsageListView;
    protected FloatingActionButton addUsageFloatingButton;
    protected Switch editSwitch;
    protected ProgressBar firstProgressBar;
    protected ProgressBar secondProgressBar;
    protected TextView textView;
    protected SharedPreferenceHelper sharedPreferenceHelper;
    private int estimatedUsage;
    protected ArrayList<useItem> useAnalyticsList;
    protected ArrayList<Integer> numUses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_analytics);

        setUpUI();

    }

    @Override
    protected void onResume() {
        super.onResume();

        //loadData();

        /* old stuff for testing
        //get the useItem array to equal something, this is for testing, it should be gotten from the sharedProfile
        useItem use1 = new useItem("Shower", 15);
        useAnalyticsList.add(use1);
        useItem use2 = new useItem("Bath", 30);
        useAnalyticsList.add(use2);
        useItem use3 = new useItem("Dishes", 45);
        useAnalyticsList.add(use3);

         */

        //Make the numUses be as long as useAnalytics, and set them to 0
        for (int pos = 0; pos < useAnalyticsList.size(); pos++){
            numUses.add(0);
        }
        //loads the list view with the 2 lists useAnalyticsList and numUses
        loadListView();

        firstProgressBar.setProgress(20);
        //secondProgressBar.setProgress(estimatedUsage);

    }

    public void setUpUI() {

        addUsageFloatingButton = findViewById(R.id.addUseFloatingActionButton);
        editSwitch = findViewById(R.id.editUseSwitch);
        firstProgressBar = findViewById(R.id.progressBarAnalytics);
        secondProgressBar = findViewById(R.id.progressBarAnalytics2);
        textView = findViewById(R.id.myUsageTextView);
        myUsageListView = findViewById(R.id.residentUseListView);

        useAnalyticsList = new ArrayList<>();

        //for testing
        useItem use1 = new useItem("Shower", 15);
        useAnalyticsList.add(use1);

        numUses = new ArrayList<>();

        addUsageFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUsage("", 0, -1);
            }
        });

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

    }

    //LoadListViewFunction
    public void loadListView() {

        ArrayList<String> usageListListText = new ArrayList<>();

        //makes a list item
        String temp = "";

        for (int pos = 0; pos < useAnalyticsList.size(); pos++){
            temp = "\n";
            temp += useAnalyticsList.get(pos).getName();
            temp += "\npercent: ";
            temp += useAnalyticsList.get(pos).getUse();
            temp += "     Times: ";
            temp += numUses.get(pos);
            temp += "\n";
            usageListListText.add(temp);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usageListListText);
        myUsageListView.setAdapter(arrayAdapter);
    }

    //Function to open fragment for adding/updating resident's water usage
    protected void addUsage(String name, int use, int pos) {
        ResidentUsageFragment residentUsageFragment = new ResidentUsageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putInt("use", use);
        bundle.putInt("pos", pos);
        residentUsageFragment.setArguments(bundle);
        residentUsageFragment.show(getSupportFragmentManager(), "Dialog");
    }

    //loads the data from the shared preferences

    private void loadData(){
        //get the list
        SharedPreferences sharedPreferences = this.getSharedPreferences("UsePreferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("use_list", null);
        Type type = new TypeToken<ArrayList<useItem>>() {}.getType();
        useAnalyticsList = gson.fromJson(json, type);

        if (useAnalyticsList == null){
            useAnalyticsList = new ArrayList<>();
        }

    }


}
