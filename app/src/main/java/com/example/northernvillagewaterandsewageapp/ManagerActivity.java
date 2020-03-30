package com.example.northernvillagewaterandsewageapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.northernvillagewaterandsewageapp.Fragments.CreateDriverFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.CreateResidentFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.DisableUserFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.GetReportFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.ManagerTimeEstimateFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.ManualDemandFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.MessageFragment;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ManagerActivity extends AppCompatActivity {//implements NavigationView.OnNavigationItemSelectedListener{

    protected Button addServiceButton;
    protected ListView worklistListView;
    protected Integer HiddenOption;
    private DrawerLayout sideBarManager;
    private ActionBarDrawerToggle toggle;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        sideBarManager = findViewById(R.id.sideBar);
        NavigationView navigationView = findViewById(R.id.design_navigation_view);

        mQueue = Volley.newRequestQueue(this);

        setUpManagerUI();

        loadListView();

        toggle = new ActionBarDrawerToggle(this, sideBarManager, R.string.open, R.string.close);
        //navigationView.setNavigationItemSelectedListener(this);
        sideBarManager.addDrawerListener(toggle);
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
        sideBarManager.closeDrawer(GravityCompat.START);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setUpManagerUI() {
        addServiceButton = findViewById(R.id.manualserviceButton);
        worklistListView = findViewById(R.id.WorklistListView);

        //a drop down menu to hide less used features
        Spinner hiddenOptionsSpinner = findViewById(R.id.managerHiddenOptionsSpinner);
        hiddenOptionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //gets the selection of the drop down menu
                HiddenOption = position;

                //uses the selection to open up the right fragment
                if (HiddenOption == 1) {
                    addResident();
                } else if (HiddenOption == 2) {
                    addDriver();
                } else if (HiddenOption == 3) {
                    disableUser();
                } else if (HiddenOption == 4) {
                    getReports();
                } else if (HiddenOption == 5) {
                    putMessage();
                } else if (HiddenOption == 6) {
                    goToAnalytics();
                }

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


    private void loadListView() {
        String url = "http://13.59.214.194:5000/get_work_list/";
        final ArrayList<String> worklistListText = new ArrayList<>();
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject e;
                    for (int pos = 0; pos < response.length(); pos++) {
                        String temp = "";
                        e = response.getJSONObject(pos);
                        temp += "Time Stamp: " + e.getString("timestamp") + "\n";
                        temp += "Resident: " + e.getString("username") + "\n";
                        temp += "House Number: " + e.getString("house_number") + "\n";
                        temp += "Tank Type: " + e.getString("tank_type") + "\n";
                        temp += "Time Estimate: " + e.getString("estimate");
                        worklistListText.add("\n" + temp + "\n");
                    }
                    arrayAdapter.addAll(worklistListText);
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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        mQueue.add(request);
    }

    // Function that shows the manager analytics when its button is clicked
    protected void goToAnalytics() {
        Intent manAnalyticsIntent = new Intent(ManagerActivity.this, ResidentAnalyticsActivity.class);
        startActivity(manAnalyticsIntent);
    }
    // Function to add a new resident in the database
    protected void addResident() {
        CreateResidentFragment createUser = new CreateResidentFragment();
        createUser.show(getSupportFragmentManager(), "Dialog");
    }
    // Function to disable an existing resident from the database
    protected void disableUser() {
        DisableUserFragment disableUserFragment = new DisableUserFragment();
        disableUserFragment.show(getSupportFragmentManager(), "Dialog");
    }
    // Function to add or remove a driver from the database
    protected void addDriver() {
        CreateDriverFragment addDriverFragment = new CreateDriverFragment();
        addDriverFragment.show(getSupportFragmentManager(), "Dialog");
    }

    // Function to get manager reports
    protected void getReports() {
        GetReportFragment getReportFragment = new GetReportFragment();
        getReportFragment.show(getSupportFragmentManager(), "Dialog");
    }

    // Function to make the messaging system work
    protected void putMessage() {
        MessageFragment messageFragment = new MessageFragment();
        messageFragment.show(getSupportFragmentManager(), "Dialog");
    }

    // Function to make manual demand work
    protected void manualDemand() {
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
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        goToLogin();
    }

    //GoTo Login
    protected void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    // Goes to settings activity
    private void goToSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

}
