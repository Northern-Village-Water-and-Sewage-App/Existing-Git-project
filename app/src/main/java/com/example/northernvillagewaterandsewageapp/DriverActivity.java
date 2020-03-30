package com.example.northernvillagewaterandsewageapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.northernvillagewaterandsewageapp.Fragments.DriverReportFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.DriverTimeEstimateFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.ManualDemandFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class DriverActivity extends AppCompatActivity {//implements NavigationView.OnNavigationItemSelectedListener{

    protected ListView driverWorklistListView;
    protected Button addServiceButton;
    protected Button driverReportButton;
    private DrawerLayout sideBarDriver;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        driverWorklistListView = findViewById(R.id.DriverWorklistListView);
        addServiceButton = findViewById(R.id.manualserviceButton);
        driverReportButton = findViewById(R.id.driverMakeReportButton);
        sideBarDriver = findViewById(R.id.sideBar);
        NavigationView navigationView = findViewById(R.id.design_navigation_view);

        setUpDriverUI();

        loadListView();

        toggle = new ActionBarDrawerToggle(this, sideBarDriver, R.string.open, R.string.close);
        //navigationView.setNavigationItemSelectedListener(this);
        sideBarDriver.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.languages:
                goToSettingsActivity();
                return true;
            case R.id.logout:
                goToLogin();
                return true;
            default:
        }
        sideBarDriver.closeDrawer(GravityCompat.START);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    // Goes to settings activity
    private void goToSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

}
