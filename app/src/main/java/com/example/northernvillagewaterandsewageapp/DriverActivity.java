package com.example.northernvillagewaterandsewageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.northernvillagewaterandsewageapp.Fragments.DriverReportFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.DriverTimeEstimateFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.ManualDemandFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DriverActivity extends AppCompatActivity {//implements NavigationView.OnNavigationItemSelectedListener{

    protected ListView driverWorklistListView;
    TextView tv_service, tv_report;
    FloatingActionButton fab_add_any, fab_add_service, fab_add_report;
    Animation FabOpen, FabClose, FabRClockwise, FabRAntiClockwise;

    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        driverWorklistListView = findViewById(R.id.DriverWorklistListView);

        fab_add_any = findViewById(R.id.floatingActionButtonAddAnyDriver);
        fab_add_service = findViewById(R.id.floatingActionButtonAddServiceDriver);
        fab_add_report = findViewById(R.id.floatingActionButtonAddReportDelivery);

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        FabRAntiClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);

        tv_service = findViewById(R.id.textViewAddServiceDriver);
        tv_report = findViewById(R.id.textViewAddReportDelivery);

        fab_add_any.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen){
                    fab_add_service.startAnimation(FabClose);
                    fab_add_report.startAnimation(FabClose);
                    fab_add_any.startAnimation(FabRAntiClockwise);

                    fab_add_service.setClickable(false);

                    tv_service.startAnimation(FabClose);
                    tv_report.startAnimation(FabClose);

                    isOpen = false;
                } else{
                    fab_add_service.startAnimation(FabOpen);
                    fab_add_report.startAnimation(FabOpen);
                    fab_add_any.startAnimation(FabRClockwise);

                    fab_add_service.setClickable(true);

                    tv_service.startAnimation(FabOpen);
                    tv_report.startAnimation(FabOpen);

                    isOpen = true;
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpDriverUI();

        loadListView();

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

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    protected void setUpDriverUI()
    {
        fab_add_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualDemand();
            }
        });
        fab_add_report.setOnClickListener(new View.OnClickListener() {
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
