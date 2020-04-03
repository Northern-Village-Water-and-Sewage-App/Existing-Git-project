package com.example.northernvillagewaterandsewageapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.northernvillagewaterandsewageapp.Fragments.LogoutFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.ManualDemandFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.SeeTownMessageFragment;
import com.example.northernvillagewaterandsewageapp.ObjectClasses.TankStatus;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class ResidentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected Button deliveryButton;
    protected Button analyticsButton;
    protected Button waterAlarm;
    protected Button sewageAlarm;
    protected ProgressBar progressBar;
    protected TextView waterStatus;
    protected TextView sewageStatus;
    private DrawerLayout sideBarResident;
    private ActionBarDrawerToggle toggle;
    protected FloatingActionButton infoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident);


        deliveryButton = findViewById(R.id.manualDeliveryButton);
        analyticsButton = findViewById(R.id.analyticsButton);
        waterAlarm = findViewById(R.id.waterAlarm);
        sewageAlarm = findViewById(R.id.sewageAlarm);
        progressBar = findViewById(R.id.progressBar);
        waterStatus = findViewById(R.id.txtViewStatusWater);
        sewageStatus = findViewById(R.id.txtViewStatusSewage);
        infoButton = findViewById(R.id.townInfoFloatingActionButton);

        sideBarResident = findViewById(R.id.sideBarResident);
        NavigationView navigationView = findViewById(R.id.nav_view_resident);

        toggle = new ActionBarDrawerToggle(this, sideBarResident, R.string.open, R.string.close);
        navigationView.setNavigationItemSelectedListener(this);
        sideBarResident.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        updateInfo();
        setUpResidentUI();

    }

    //Gets updated tank info with their respective color indications
    protected void updateInfo()
    {
        int newWaterHeight = new TankStatus().getWaterHeight();
        //int newWaterAlarm = new TankStatus().getWaterAlarm();
        int newSewageAlarm = new TankStatus().getSewageAlarm();
        progressBar.setProgress(newWaterHeight);

        // Color-wise indication for water level
        if ((0 <= newWaterHeight && newWaterHeight <= 30)) {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));  // Critically low water level
            waterAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            waterStatus.setText(R.string.tank_status_critical);
        }
        else if ((30 < newWaterHeight && newWaterHeight <= 65)) {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(255, 204, 0)));  // Medium water level
            waterAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255,204,0	)));
            waterStatus.setText(R.string.tank_status_warning);
        }
        else {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));  // Optimum to full water level
            waterAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            waterStatus.setText(R.string.tank_status_ok);
        }

        // Color-wise indication for sewage alarm
        switch (newSewageAlarm) {
            case (0):
                sewageAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));  // Low sewage
                sewageStatus.setText(R.string.tank_status_ok);
                break;
            case (1):
                sewageAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255,204,0	)));  // Medium sewage
                sewageStatus.setText(R.string.tank_status_warning);
                break;
            case (2):
                sewageAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.RED));  // High sewage
                sewageStatus.setText(R.string.tank_status_critical);
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

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSeeMessageFragment();
            }
        });

    }

    //                  ------------------------- Navigation bar related stuff -------------------------
    @Override
    public void onBackPressed() {
        if (sideBarResident.isDrawerOpen(GravityCompat.START)) {
            sideBarResident.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout:
                logoutFragment();
                break;
            case R.id.English:
                setAppLanguage("en");
                break;
            case R.id.French:
                setAppLanguage("fr");
                break;
            case R.id.Inuktitut:
                setAppLanguage("iu");
                break;
        }
        sideBarResident.closeDrawer(GravityCompat.START);
        return true;
    }

    // Function to change app language
    private void setAppLanguage(String language) {
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();

        config.setLocale(new Locale(language.toLowerCase()));

        resources.updateConfiguration(config, displayMetrics);
    }

    // Option to refresh
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.refresh_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.refreshItem:
                updateInfo();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
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

    //GoTo Login
    protected void goToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    //Open message fragment
    private void openSeeMessageFragment(){
        SeeTownMessageFragment seeTownMessageFragment = new SeeTownMessageFragment();
        seeTownMessageFragment.show(getSupportFragmentManager(), "Dialog");
    }

    //Open logout fragment
    private void logoutFragment(){
        LogoutFragment logoutFragment = new LogoutFragment();
        logoutFragment.show(getSupportFragmentManager(), "Dialog");
    }

}

