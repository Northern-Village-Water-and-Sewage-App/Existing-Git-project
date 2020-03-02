package com.example.northernvillagewaterandsewageapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ResidentActivity extends AppCompatActivity {

    protected Button deliveryButton;
    protected Button analyticsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident);

        deliveryButton = findViewById(R.id.manualDeliveryButton);
        analyticsButton = findViewById(R.id.analyticsButton);

    }

    protected void displayCurrentWater()
    {

    }
    protected void displayCurrentSewage()
    {

    }
    protected void getTimeDeliveryEstimate()
    {

    }
    protected void goToAnalytics()
    {
        Intent resAnalyticsIntent = new Intent(ResidentActivity.this, ResidentAnalyticsActivity.class);
        startActivity(resAnalyticsIntent);
    }
}

