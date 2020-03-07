package com.example.northernvillagewaterandsewageapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ManagerActivity extends AppCompatActivity {

    protected Button analyticsButton;
    protected Button getReportsButton;
    protected Button addServiceButton;
    protected Button disableResidentButton;
    protected Button addResidentButton;
    protected Button addDriverButton;
    protected Button messageButton;
    protected ListView worklistListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        analyticsButton = findViewById(R.id.analyticsButton);
        addResidentButton = findViewById(R.id.addResidentButton);
        addDriverButton = findViewById(R.id.addDriverButton);
        getReportsButton = findViewById(R.id.getReportButton);
        addServiceButton = findViewById(R.id.manualserviceButton);
        disableResidentButton = findViewById(R.id.disableResidentButton);
        messageButton = findViewById(R.id.messageButton);
        worklistListView = findViewById(R.id.WorklistListView);


        setUpManagerUI();

        loadListView();
    }

    protected void setUpManagerUI()
    {
        // Adding a new resident
        addResidentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addResident();
            }
        });

        // Removing an existing resident or driver
        disableResidentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                disableResident();
            }
        });

        // Accessing the manager analytics
        analyticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToAnalytics();
            }
        });

        // Gets the manager reports
        getReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getReports();
            }
        });

        //Adding a new driver
        addDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDriver();
            }
        });

        //set the message
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putMessage();
            }
        });

        addServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualDemand();
            }
        });

        //shows back button
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }



    //LoadListViewFunction
    protected void loadListView(){
        ArrayList<String> worklistListText = new ArrayList<>();

        //makes a list item                                                                                     //Fix here to make the list view correct
        for(int i =0; i <5; i++){
            String temp = "";
            temp+= "House Number: " + i*49+8 + "\n";
            temp+= "Service: " + "Water" + "\n";
            temp+= "Time Estimate: " + "None" + "\n";

            worklistListText.add(temp);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, worklistListText);
        worklistListView.setAdapter(arrayAdapter);
    }

    // Function that shows the manager analytics when its button is clicked
    protected void goToAnalytics()
    {
        Intent manAnalyticsIntent = new Intent(ManagerActivity.this, ResidentAnalyticsActivity.class);
        startActivity(manAnalyticsIntent);
    }
    // Function to add a new resident in the database
    protected void addResident()
    {
        CreateResidentFragment createUser = new CreateResidentFragment();
        createUser.show(getSupportFragmentManager(), "Dialog");
    }
    // Function to disable an existing resident from the database
    protected void disableResident()
    {
        DisableUserFragment disableUserFragment = new DisableUserFragment();
        disableUserFragment.show(getSupportFragmentManager(), "Dialog");
    }
    // Function to add or remove a driver from the database
    protected void addDriver()
    {
        CreateDriverFragment addDriverFragment = new CreateDriverFragment();
        addDriverFragment.show(getSupportFragmentManager(), "Dialog");
    }

    // Function to get manager reports
    protected void getReports()
    {
        GetReportFragment getReportFragment = new GetReportFragment();
        getReportFragment.show(getSupportFragmentManager(), "Dialog");
    }

    // Function to make the messaging system work
    protected void putMessage(){

    }

    // Function to make manual demand work
    protected void manualDemand(){
        ManualDemandFragment manualDemandFragment = new ManualDemandFragment();
        manualDemandFragment.show(getSupportFragmentManager(), "Dialog");
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
