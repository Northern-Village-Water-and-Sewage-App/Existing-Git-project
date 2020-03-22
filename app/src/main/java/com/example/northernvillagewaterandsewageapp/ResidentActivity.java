package com.example.northernvillagewaterandsewageapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
    protected Button waterAlarm;
    protected Button sewageAlarm;
    protected ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident);

        int waterLevel = new TankStatus().getWaterHeight();

        deliveryButton = findViewById(R.id.manualDeliveryButton);
        analyticsButton = findViewById(R.id.analyticsButton);
        waterAlarm = findViewById(R.id.waterAlarm);
        sewageAlarm = findViewById(R.id.sewageAlarm);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(waterLevel);

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

        switch (item.getItemId()) {
            case R.id.subitem1:
                goToLogin();
                return true;
            case R.id.subitem2:
                goToSettingsActivity();
                return true;
            case R.id.subitem3:
                updateInfo();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    //Gets updated tank info with their respective color indications
    protected void updateInfo()
    {
        int newWaterHeight = new TankStatus().getWaterHeight();
        int newWaterAlarm = new TankStatus().getWaterAlarm();
        int newSewageAlarm = new TankStatus().getSewageAlarm();
        progressBar.setProgress(newWaterHeight);

        // Color-wise indication for water level
        if ((0 <= newWaterHeight && newWaterHeight <= 30)) {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));  // Critically low water level
        }
        else if ((30 < newWaterHeight && newWaterHeight <= 65)) {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(255, 165, 0)));  // Medium water level
        }
        else {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));  // Optimum to full water level
        }

        // Color-wise indication for water alarm
        if ((0 <= newWaterAlarm && newWaterAlarm <= 30)) {
            waterAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.RED));  // Critically low water level
        }
        else if ((30 < newWaterAlarm && newWaterAlarm <= 65)) {
            waterAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255,165,0	)));  // Medium water level
        }
        else {
            waterAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));  // Optimum to full water level
        }

        // Color-wise indication for sewage alarm
        switch (newSewageAlarm) {
            case (0):
                sewageAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));  // Low sewage
                break;
            case (1):
                sewageAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255,165,0	)));  // Medium sewage
                break;
            case (2):
                sewageAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.RED));  // High sewage
                break;
        }
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

