package com.example.northernvillagewaterandsewageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class DriverActivity extends AppCompatActivity {

    protected ListView driverWorklistListView;
    protected Button addServiceButton;
    protected Button driverReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        driverWorklistListView = findViewById(R.id.DriverWorklistListView);
        addServiceButton = findViewById(R.id.manualserviceButton);
        driverReportButton = findViewById(R.id.driverMakeReportButton);

        setUpDriverUI();

        loadListView();
    }


    protected void setUpDriverUI()
    {
        addServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualDemand();
            }
        });
        driverReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReportFragment();
            }
        });
    }

    // Function to make manual demand work
    protected void manualDemand(){
        ManualDemandFragment manualDemandFragment = new ManualDemandFragment();
        manualDemandFragment.show(getSupportFragmentManager(), "Dialog");
    }

    //LoadListViewFunction
    protected void loadListView(){
        ArrayList<String> worklistListText = new ArrayList<>();

        //makes a list item                                                               //Fix here to make the list view correct
        for(int i = 0; i < 5; i++)
        {
            String temp = "";
            temp+= "House Number: " + i * 49 + 8 + "\n";
            temp+= "Service: " + "Water" + "\n";
            temp+= "Time Estimate: " + "None" + "\n";

            worklistListText.add(temp);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, worklistListText);
        driverWorklistListView.setAdapter(arrayAdapter);

        //makes clicking on an item from the worklist pull up the manager time estimate fragment, with the information it needs to update the database
        driverWorklistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //need to pass other stuff here to make this work                                                                                                            ************************
                DriverTimeEstimateFragment driverTimeEstimateFragment = new DriverTimeEstimateFragment();
               driverTimeEstimateFragment.show(getSupportFragmentManager(), "Dialog");
            }
        });
    }

    //Open report fragment
    protected void openReportFragment(){
        DriverReportFragment driverReportFragment = new DriverReportFragment();
        driverReportFragment.show(getSupportFragmentManager(), "Dialog");
    }


    //back navigation working, 3 next functions
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
        goToLogin();
    }
    //GoTo Login
    protected void goToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
