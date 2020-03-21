package com.example.northernvillagewaterandsewageapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.northernvillagewaterandsewageapp.Fragments.ManualDemandFragment;
import com.example.northernvillagewaterandsewageapp.ObjectClasses.TankStatus;

import java.util.Random;

public class ResidentActivity extends AppCompatActivity {

    protected Button deliveryButton;
    protected Button analyticsButton;
    protected ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident);

        deliveryButton = findViewById(R.id.manualDeliveryButton);
        analyticsButton = findViewById(R.id.analyticsButton);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(50);

        setUpResidentUI();

    }

    // Logout and settings menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.settings_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int newWaterHeight = new TankStatus().getWaterHeight();

        switch (item.getItemId()) {
            case R.id.subitem1:
                goToLogin();
                return true;
            case R.id.subitem2:
                goToSettingsActivity();
                return true;
            case R.id.subitem3:
                //updateInfo();
                progressBar.setProgress(newWaterHeight);
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    //Gets updated tank info
    protected void updateInfo()
    {
        //tankStatus.getSewageAlarm();
        //tankStatus.getWaterAlarm();
        //tankStatus.getWaterHeight();
    }

    protected void setUpResidentUI()
    {
        deliveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getManualDelivery();
            }
        });

        analyticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAnalytics();
            }
        });
    }

    protected void getManualDelivery()
    {
        ManualDemandFragment manualDemandFragment = new ManualDemandFragment();
        manualDemandFragment.show(getSupportFragmentManager(), "Dialog");
    }
    protected void displayCurrentWater()
    {
        //dbHelper.GetTankInfo();
    }
    protected void displayCurrentSewage()
    {
        //tankStatus.getSewageAlarm();
    }
    protected void getTimeDeliveryEstimate()
    {

    }
    protected void goToAnalytics()
    {
        Intent resAnalyticsIntent = new Intent(ResidentActivity.this, ResidentAnalyticsActivity.class);
        startActivity(resAnalyticsIntent);
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

    // Goes to settings activity
    private void goToSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

}

