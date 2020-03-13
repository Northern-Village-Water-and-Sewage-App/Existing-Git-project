package com.example.northernvillagewaterandsewageapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ManagerActivity extends AppCompatActivity {

    protected Button addServiceButton;
    protected ListView worklistListView;
    protected Integer HiddenOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        setUpManagerUI();

        loadListView();
    }

    protected void setUpManagerUI()
    {
        addServiceButton = findViewById(R.id.manualserviceButton);
        worklistListView = findViewById(R.id.WorklistListView);

        //a drop down menu to hide less used features
        Spinner hiddenOptionsSpinner = findViewById(R.id.managerHiddenOptionsSpinner);
        hiddenOptionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //gets the selection of the drop down menu
                HiddenOption = position;

                //uses the selectrion to open up the right fragment
                if (HiddenOption == 1){addResident();}
                else if (HiddenOption == 2){addDriver();}
                else if (HiddenOption == 3){disableUser();}
                else if (HiddenOption == 4){getReports();}
                else if (HiddenOption == 5){putMessage();}
                else if (HiddenOption == 6){goToAnalytics();}

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        worklistListView.setAdapter(arrayAdapter);

        //makes clicking on an item from the worklist pull up the manager time estimate fragment, with the information it needs to update the database
        worklistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //need to pass other stuff here to make this work                                                                                                            ************************
                ManagerTimeEstimateFragment managerTimeEstimateFragment = new ManagerTimeEstimateFragment();
               managerTimeEstimateFragment.show(getSupportFragmentManager(), "Dialog");
            }
        });
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
    protected void disableUser()
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
        MessageFragment messageFragment = new MessageFragment();
        messageFragment.show(getSupportFragmentManager(), "Dialog");
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
