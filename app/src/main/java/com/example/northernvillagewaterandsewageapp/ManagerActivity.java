package com.example.northernvillagewaterandsewageapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ManagerActivity extends AppCompatActivity {

    protected Button analyticsButton;
    protected FloatingActionButton addButton;
    protected FloatingActionButton removeButton;
    protected Button getReportsButton;
    protected Button addServiceButton;
    protected Button disableResidentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        analyticsButton = findViewById(R.id.analyticsButton);
        addButton = findViewById(R.id.addResidentDriverfloatingButton);
        removeButton = findViewById(R.id.removeResidentDriverfloatingButton);
        getReportsButton = findViewById(R.id.getReportButton);
        addServiceButton = findViewById(R.id.manualserviceButton);
        disableResidentButton = findViewById(R.id.disableresidentButton);

        // Adding a new resident or driver
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addResident_Driver();
            }
        });

        // Removing an existing resident or driver
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeResident_Driver();
            }
        });

        analyticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToAnalytics();
            }
        });

    }

    protected void goToAnalytics()
    {
        Intent manAnalyticsIntent = new Intent(ManagerActivity.this, ResidentAnalyticsActivity.class);
        startActivity(manAnalyticsIntent);
    }
    // Function to add a new resident or driver in the database
    protected void addResident_Driver()
    {

    }
    // Function to remove an existing resident or driver from the database
    protected void removeResident_Driver()
    {

    }
}
