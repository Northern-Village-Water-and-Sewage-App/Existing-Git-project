package com.example.northernvillagewaterandsewageapp;


import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.ListView;
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
import com.example.northernvillagewaterandsewageapp.Fragments.DriverTimeEstimateFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.GetReportFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.LogoutFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.ManagerTimeEstimateFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.ManualDemandFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.MessageFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.RemoveDriverFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.RemoveResidentFragment;
import com.example.northernvillagewaterandsewageapp.ObjectClasses.DeliveryList;
import com.example.northernvillagewaterandsewageapp.ObjectClasses.WorkList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;


public class ManagerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected ListView worklistListView;

    private RequestQueue mQueue;
    FloatingActionButton fab_add_any, fab_add_service, fab_add_driver, fab_add_resident, fab_add_message;
    TextView tv_service, tv_driver, tv_resident, tv_message;
    Animation FabOpen, FabClose, FabRClockwise, FabRAntiClockwise;
    private DrawerLayout sideBar;
    private ActionBarDrawerToggle toggle;
    ArrayList<Integer> worklistListInt;
    ArrayList<WorkList> managerLists;

    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        mQueue = Volley.newRequestQueue(this);
        getWorkList(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                loadWorkList();
            }
        });



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

        sideBar = findViewById(R.id.sideBarManager);
        NavigationView navigationView = findViewById(R.id.nav_view_manager);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, sideBar, R.string.open, R.string.close);
        sideBar.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.managerHeaderNameTV);
        navUsername.setText("Your Text Here");

        setUpManagerUI();

        loadWorkList(); // with custom adapter

    }

    protected void setUpManagerUI() {
        worklistListView = findViewById(R.id.WorklistListView);

        //shows back button
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume() {
        getWorkList(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                loadWorkList();
            }
        });
        super.onResume();
    }
    public interface VolleyCallBack {
        void onSuccess();
    }


    //LoadListViewFunction
    public void loadWorkList(){
        final WorkListAdapter adapter = new WorkListAdapter(this, R.layout.custom_adapter_layout_manager, managerLists);

        worklistListView.setAdapter(adapter);

        //makes clicking on an item from the worklist pull up the manager time estimate fragment, with the information it needs to update the database
        worklistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //need to pass other stuff here to make this work
                // ************************
                Bundle bundle = new Bundle();
                bundle.putInt("pk",worklistListInt.get(position));
                ManagerTimeEstimateFragment driverTimeEstimateFragment = new ManagerTimeEstimateFragment();
                driverTimeEstimateFragment.setArguments(bundle);
                driverTimeEstimateFragment.show(getSupportFragmentManager(), "Dialog");
            }
        });
    }

    // with custom adapter
    public void getWorkList(final VolleyCallBack callBack) {
        worklistListInt = new ArrayList<Integer>();
        managerLists = new ArrayList<>();
        String url = "http://54.201.85.48:32132/get_work_list/";

        final WorkListAdapter adapter = new WorkListAdapter(this, R.layout.custom_adapter_layout_driver, managerLists);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    worklistListInt = new ArrayList<Integer>();
                    for(int i = 0; i < response.length(); i++)
                    {
                        JSONObject user = response.getJSONObject(i);
                        WorkList object = new WorkList(user.getString("timestamp"), user.getString("house_number"), user.getString("tank_type"), user.getString("estimate"));
                        managerLists.add(object);
                        worklistListInt.add(user.getInt("pk"));
                    }
                    callBack.onSuccess();
                    adapter.addAll(managerLists);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(ManagerActivity.this, "Failed: "+error.toString(), Toast.LENGTH_LONG).show();
                    }

                });
        mQueue.add(request);

    }

    //                  ------------------------- Navigation bar related stuff -------------------------
    @Override
    public void onBackPressed() {
        if (sideBar.isDrawerOpen(GravityCompat.START)) {
            sideBar.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.disable_resident:
                disableUser();
                break;
            case R.id.remove_resident:
                removeResident();
                break;
            case R.id.remove_driver:
                removeDriver();
                break;
            case R.id.reports:
                getReports();
                break;
            case R.id.managerAnalytics:
                goToAnalytics();
                break;
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
        sideBar.closeDrawer(GravityCompat.START);
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
                getWorkList(new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        loadWorkList();
                    }
                });
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    // Function that shows the manager analytics when its button is clicked
    protected void goToAnalytics() {
        Intent manAnalyticsIntent = new Intent(ManagerActivity.this, ManagerAnalyticsActivity.class);
        startActivity(manAnalyticsIntent);
    }

    // Function to add a new resident in the database
    protected void addResident() {
        CreateResidentFragment createUser = new CreateResidentFragment();
        createUser.show(getSupportFragmentManager(), "Dialog");
    }

    // Function to remove a resident from the database
    protected void removeResident() {
        RemoveResidentFragment removeResidentFragment = new RemoveResidentFragment();
        removeResidentFragment.show(getSupportFragmentManager(), "Dialog");
    }

    // Function to disable an existing resident from the database
    protected void disableUser() {
        DisableUserFragment disableUserFragment = new DisableUserFragment();
        disableUserFragment.show(getSupportFragmentManager(), "Dialog");
    }

    // Function to add a driver from the database
    protected void addDriver() {
        CreateDriverFragment addDriverFragment = new CreateDriverFragment();
        addDriverFragment.show(getSupportFragmentManager(), "Dialog");
    }

    // Function to remove a driver from the database
    protected void removeDriver() {
        RemoveDriverFragment removeDriverFragment = new RemoveDriverFragment();
        removeDriverFragment.show(getSupportFragmentManager(), "Dialog");
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

    //Open logout fragment
    private void logoutFragment() {
        LogoutFragment logoutFragment = new LogoutFragment();
        logoutFragment.show(getSupportFragmentManager(), "Dialog");
    }

    public void externalLoadList(){
        getWorkList(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                loadWorkList();
            }
        });

    }

}


//    NOT NEEDED ANYMORE...

    /*private void loadListView() {
        String url = "http://54.201.85.48:32132/get_work_list/";
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
                        temp += "Time estimate: " + e.getString("estimate");
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
    }*/