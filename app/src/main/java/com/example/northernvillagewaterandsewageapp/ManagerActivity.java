package com.example.northernvillagewaterandsewageapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;


public class ManagerActivity extends AppCompatActivity {//implements NavigationView.OnNavigationItemSelectedListener {

    protected ListView worklistListView;
    protected Button troubleshoot;

    private RequestQueue mQueue;
    FloatingActionButton fab_add_any, fab_add_service, fab_add_driver, fab_add_resident, fab_add_message;
    TextView tv_service, tv_driver, tv_resident, tv_message;
    Animation FabOpen, FabClose, FabRClockwise, FabRAntiClockwise;
    private DrawerLayout sideBar;
    private ActionBarDrawerToggle toggle;

    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        mQueue = Volley.newRequestQueue(this);

        //debug
//        troubleshoot = findViewById(R.id.troubleshooterButton);


        fab_add_any = findViewById(R.id.floatingActionButtonAddAny);
        fab_add_service = findViewById(R.id.floatingActionButtonAddService);
        fab_add_driver = findViewById(R.id.floatingActionButtonAddDriver);
        fab_add_resident = findViewById(R.id.floatingActionButtonAddResident);
        fab_add_message = findViewById(R.id.floatingActionButtonAddMessage);

        fab_add_service.setClickable(false);
        fab_add_driver.setClickable(false);
        fab_add_resident.setClickable(false);

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        FabRAntiClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);

        tv_service = findViewById(R.id.textViewAddService);
        tv_driver = findViewById(R.id.textViewAddDriver);
        tv_resident = findViewById(R.id.textViewAddResident);
        tv_message = findViewById(R.id.textViewAddMessage);

        fab_add_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putMessage();
            }
        });

        fab_add_any.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {
                    fab_add_service.startAnimation(FabClose);
                    fab_add_driver.startAnimation(FabClose);
                    fab_add_resident.startAnimation(FabClose);
                    fab_add_message.startAnimation(FabClose);
                    fab_add_any.startAnimation(FabRAntiClockwise);

                    fab_add_service.setClickable(false);
                    fab_add_driver.setClickable(false);
                    fab_add_resident.setClickable(false);

                    tv_service.startAnimation(FabClose);
                    tv_driver.startAnimation(FabClose);
                    tv_resident.startAnimation(FabClose);
                    tv_message.startAnimation(FabClose);

                    isOpen = false;
                } else {
                    fab_add_service.startAnimation(FabOpen);
                    fab_add_driver.startAnimation(FabOpen);
                    fab_add_resident.startAnimation(FabOpen);
                    fab_add_message.startAnimation(FabOpen);
                    fab_add_any.startAnimation(FabRClockwise);

                    fab_add_service.setClickable(true);
                    fab_add_driver.setClickable(true);
                    fab_add_resident.setClickable(true);

                    tv_service.startAnimation(FabOpen);
                    tv_driver.startAnimation(FabOpen);
                    tv_resident.startAnimation(FabOpen);
                    tv_message.startAnimation(FabOpen);

                    isOpen = true;
                }
            }
        });

        fab_add_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manualDemand();
            }
        });
        fab_add_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDriver();
            }
        });
        fab_add_resident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResident();
            }
        });

        /*sideBar = findViewById(R.id.sideBarManager);
        NavigationView navigationView = findViewById(R.id.nav_view_manager);

        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, sideBar, R.string.open, R.string.close);
        sideBar.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        setUpManagerUI();

        loadListView();
    }

    protected void setUpManagerUI() {
        worklistListView = findViewById(R.id.WorklistListView);

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
