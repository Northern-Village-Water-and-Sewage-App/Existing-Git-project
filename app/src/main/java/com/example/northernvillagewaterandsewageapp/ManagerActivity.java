package com.example.northernvillagewaterandsewageapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ManagerActivity extends AppCompatActivity {

    protected Button addServiceButton;
    protected ListView worklistListView;
    protected Integer HiddenOption;
    private RequestQueue mQueue;
    FloatingActionButton fab_add_any, fab_add_service, fab_add_driver, fab_add_resident;
    TextView tv_service, tv_driver, tv_resident;
    Animation FabOpen, FabClose, FabRClockwise, FabRAntiClockwise;

    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        mQueue = Volley.newRequestQueue(this);

        fab_add_any = findViewById(R.id.floatingActionButtonAddAny);
        fab_add_service = findViewById(R.id.floatingActionButtonAddService);
        fab_add_driver = findViewById(R.id.floatingActionButtonAddDriver);
        fab_add_resident = findViewById(R.id.floatingActionButtonAddResident);

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

        fab_add_any.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {
                    fab_add_service.startAnimation(FabClose);
                    fab_add_driver.startAnimation(FabClose);
                    fab_add_resident.startAnimation(FabClose);
                    fab_add_any.startAnimation(FabRAntiClockwise);

                    fab_add_service.setClickable(false);
                    fab_add_driver.setClickable(false);
                    fab_add_resident.setClickable(false);

                    tv_service.startAnimation(FabClose);
                    tv_driver.startAnimation(FabClose);
                    tv_resident.startAnimation(FabClose);

                    isOpen = false;
                } else {
                    fab_add_service.startAnimation(FabOpen);
                    fab_add_driver.startAnimation(FabOpen);
                    fab_add_resident.startAnimation(FabOpen);
                    fab_add_any.startAnimation(FabRClockwise);

                    fab_add_service.setClickable(true);
                    fab_add_driver.setClickable(true);
                    fab_add_resident.setClickable(true);

                    tv_service.startAnimation(FabOpen);
                    tv_driver.startAnimation(FabOpen);
                    tv_resident.startAnimation(FabOpen);

                    isOpen = true;
                }
            }
        });

        setUpManagerUI();

        loadListView();
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
            default:
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
