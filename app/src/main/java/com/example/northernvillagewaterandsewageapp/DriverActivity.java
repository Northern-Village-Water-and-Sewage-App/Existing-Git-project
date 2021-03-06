package com.example.northernvillagewaterandsewageapp;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.northernvillagewaterandsewageapp.Fragments.DriverReportFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.DriverTimeEstimateFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.LogoutFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.ManagerTimeEstimateFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.ManualDemandFragment;
import com.example.northernvillagewaterandsewageapp.ObjectClasses.DeliveryList;
import com.example.northernvillagewaterandsewageapp.ObjectClasses.WorkList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class DriverActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    protected ListView driverWorklistListView;
    TextView tv_service, tv_report;
    FloatingActionButton fab_add_any, fab_add_service, fab_add_report;
    Animation FabOpen, FabClose, FabRClockwise, FabRAntiClockwise;
    private DrawerLayout sideBar;
    private ActionBarDrawerToggle toggle;
    private RequestQueue mQueue;
    boolean isOpen = false;
    ArrayList<String> worklistListText;
    ArrayList<Integer> worklistListInt;
    ArrayList<DeliveryList> deliveryLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        
        mQueue = Volley.newRequestQueue(this);

        connectJava();
        connectedButtons();
    }


    // Function to make manual demand work
    protected void manualDemand(){
        ManualDemandFragment manualDemandFragment = new ManualDemandFragment();
        manualDemandFragment.show(getSupportFragmentManager(), "Dialog");
    }
    @Override
    protected void onResume() {
        getDeliveryList(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                loadListView();
            }
        });
        super.onResume();
    }
    public interface VolleyCallBack {
        void onSuccess();
    }

    //LoadListViewFunction
    protected void loadListView(){
        final DeliveryListAdapter adapter = new DeliveryListAdapter(this, R.layout.custom_adapter_layout_driver, deliveryLists);

        driverWorklistListView.setAdapter(adapter);

        //makes clicking on an item from the worklist pull up the manager time estimate fragment, with the information it needs to update the database
        driverWorklistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("pk",worklistListInt.get(position));
                DriverTimeEstimateFragment driverTimeEstimateFragment = new DriverTimeEstimateFragment();
                driverTimeEstimateFragment.setArguments(bundle);
                driverTimeEstimateFragment.show(getSupportFragmentManager(), "Dialog");
            }
        });
    }

    //                  ------------------------- Navigation bar related stuff -------------------------
    @Override
    public void onBackPressed() {
        if (sideBar.isDrawerOpen(GravityCompat.START)) {
            sideBar.closeDrawer(GravityCompat.START);
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
        getDeliveryList(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                loadListView();
            }
        });
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
                getDeliveryList(new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        loadListView();
                    }
                });
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    //Open report fragment
    protected void openReportFragment(){
        DriverReportFragment driverReportFragment = new DriverReportFragment();
        driverReportFragment.show(getSupportFragmentManager(), "Dialog");
    }

    //GoTo Login
    protected void goToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    //Open logout fragment
    private void logoutFragment(){
        LogoutFragment logoutFragment = new LogoutFragment();
        logoutFragment.show(getSupportFragmentManager(), "Dialog");
    }

    // with custom adapter
    private void getDeliveryList(final VolleyCallBack callBack) {
        worklistListInt = new ArrayList<Integer>();
        deliveryLists = new ArrayList<>();
        String url = "http://54.201.85.48:32132/get_work_list/";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    worklistListInt = new ArrayList<Integer>();
                    for(int i = 0; i < response.length(); i++)
                    {
                        JSONObject user = response.getJSONObject(i);
                        String temp = "";
                        DeliveryList object = new DeliveryList(user.getString("timestamp"), user.getString("house_number"), user.getString("tank_type"), user.getString("estimate"));
                        deliveryLists.add(object);
                        worklistListInt.add(user.getInt("pk"));
                    }
                    callBack.onSuccess();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(DriverActivity.this, "Failed: "+error.toString(), Toast.LENGTH_LONG).show();
                    }

                });
        mQueue.add(request);

    }

    public void externalLoadList() {
        getDeliveryList(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                loadListView();
            }
        });
    }

    protected void connectJava(){
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
    }

    protected void connectedButtons(){

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

        sideBar = findViewById(R.id.sideBarDriver);
        NavigationView navigationView = findViewById(R.id.nav_view_driver);

        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, sideBar, R.string.open, R.string.close);
        sideBar.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.driverHeaderNameTV);
        navUsername.setText("Hello");

    }

}
