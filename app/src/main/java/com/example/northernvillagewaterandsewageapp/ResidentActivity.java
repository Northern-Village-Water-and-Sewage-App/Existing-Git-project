package com.example.northernvillagewaterandsewageapp;


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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.northernvillagewaterandsewageapp.Fragments.LogoutFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.ManualDemandFragment;
import com.example.northernvillagewaterandsewageapp.Fragments.SeeTownMessageFragment;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class ResidentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected Button waterAlarm;
    protected Button sewageAlarm;
    protected ProgressBar progressBar;
    protected TextView waterStatus;
    protected TextView sewageStatus;
    private DrawerLayout sideBarResident;
    private ActionBarDrawerToggle toggle;
    protected ImageView infoButton;
    protected SharedPreferenceHelper sharedPreferencehelper;
    protected TextView deliveryEstimateTextView;
    private String ResidentName;
    private RequestQueue mQueue;
    private int ResidentPin;
    private float newWaterHeight;
    private int newWaterHeightPercentage;
    private String deliveryEstimate;
    private String tankType;
    protected TextView textViewRemainingWater;
    protected TextView residentHeaderName;

    String newSewageAlarm ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident);

        waterAlarm = findViewById(R.id.waterAlarm);
        sewageAlarm = findViewById(R.id.sewageAlarm);
        progressBar = findViewById(R.id.progressBar);
        waterStatus = findViewById(R.id.txtViewStatusWater);
        sewageStatus = findViewById(R.id.txtViewStatusSewage);
        infoButton = findViewById(R.id.townInfoFloatingActionButton);
        sharedPreferencehelper = new SharedPreferenceHelper(ResidentActivity.this);
        ResidentName = sharedPreferencehelper.getUserName(getString(R.string.user_name));
        mQueue = Volley.newRequestQueue(this);
        TankStatus(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                updateTank();
            }
        });
        sideBarResident = findViewById(R.id.sideBarResident);
        deliveryEstimateTextView = findViewById(R.id.deliveryEstimateTextView);
        textViewRemainingWater = findViewById(R.id.textViewRemainingWater);
        NavigationView navigationView = findViewById(R.id.nav_view_resident);

        //residentHeaderName = findViewById(R.id.residentHeaderNameTV);
        //residentHeaderName.setText(ResidentName);

        toggle = new ActionBarDrawerToggle(this, sideBarResident, R.string.open, R.string.close);
        navigationView.setNavigationItemSelectedListener(this);
        sideBarResident.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        updateInfo();
        setUpResidentUI();
    }

    @Override
    protected void onResume() {
        TankStatus(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                updateInfo();
            }
        });
        super.onResume();
    }
    public interface VolleyCallBack {
        void onSuccess();
    }
    // Tank status pulled from db
    public void TankStatus(final VolleyCallBack callBack) {

        String url = "http://54.201.85.48:32132/get_tank_info/{username}".replace("{username}", ResidentName);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject user = response.getJSONObject(0);
                    newWaterHeight = user.getLong("water_tank_height");
                    newWaterHeightPercentage = user.getInt("water_tank_height_percentage");
                    newSewageAlarm = user.getString("sewage_tank_status");
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
                    }
                });
        mQueue.add(request);
    }

    //Gets updated tank info with their respective color indications
    protected void updateInfo() {
        updateTank();
        updateDelivery();
    }

    //this is the message in the middle of the screen
    protected void updateDelivery() {
        String url = "http://54.201.85.48:32132/get_work_list_estimate_for_resident/{username}".replace("{username}", ResidentName);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    StringBuilder displayString = new StringBuilder();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject user = response.getJSONObject(i);
                        deliveryEstimate = user.getString("estimate");
                        tankType = user.getString("tank_type");
                        displayString.append("\n" + getString(R.string.deleverytype) + ": \t\t\t\t\t" + tankType + "\n" + getString(R.string.deliveryestimate) + ": \t\t\t{estimate}"
                                .replace("{estimate}", deliveryEstimate));
                    }
                    deliveryEstimateTextView.setText(displayString.toString());
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

    //makes the message for the delivery, this is the  one in the under the vertical bar, multiplying by 10 to make it seem more realistic
    protected void updateTank() {
        textViewRemainingWater.setText(getString(R.string.waterremaining) + ": \n{100} Liters".replace("{100}", String.valueOf(newWaterHeight * 10)));
        progressBar.setProgress(newWaterHeightPercentage);

        // Color-wise indication for water level
        if ((0 <= newWaterHeightPercentage && newWaterHeightPercentage <= 30)) {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));  // Critically low water level
            waterAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            waterStatus.setText(R.string.tank_status_critical);
        } else if ((30 < newWaterHeightPercentage && newWaterHeightPercentage <= 65)) {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(255, 204, 0)));  // Medium water level
            waterAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255, 204, 0)));
            waterStatus.setText(R.string.tank_status_warning);
        } else {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));  // Optimum to full water level
            waterAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            waterStatus.setText(R.string.tank_status_ok);
        }

        // Color-wise indication for sewage alarm
        switch (newSewageAlarm) {
            case ("OK"):
                sewageAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));  // Low sewage
                sewageStatus.setText(R.string.tank_status_ok);
                break;
            case ("WARNING"):
                sewageAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255, 204, 0)));  // Medium sewage
                sewageStatus.setText(R.string.tank_status_warning);
                break;
            case ("CRITICAL"):
                sewageAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.RED));  // High sewage
                sewageStatus.setText(R.string.tank_status_critical);
                break;
        }
    }

    protected void setUpResidentUI() {

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
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.getDelivery:
                getManualDelivery();
                break;
            case R.id.resAnalytics:
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
        TankStatus(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                updateInfo();
            }
        });
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
                TankStatus(new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        updateInfo();
                    }});
                updateInfo();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    protected void getManualDelivery() {
        ManualDemandFragment manualDemandFragment = new ManualDemandFragment();
        manualDemandFragment.show(getSupportFragmentManager(), "Dialog");
    }

    protected void displayCurrentWater() {
        //dbHelper.GetTankInfo();
    }

    protected void displayCurrentSewage() {
        //tankStatus.getSewageAlarm();
    }

    protected void getTimeDeliveryEstimate() {

    }

    protected void goToAnalytics() {
        Intent resAnalyticsIntent = new Intent(ResidentActivity.this, ResidentAnalyticsActivity.class);
        startActivity(resAnalyticsIntent);
    }

    //GoTo Login
    protected void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    //Open message fragment
    private void openSeeMessageFragment() {
        SeeTownMessageFragment seeTownMessageFragment = new SeeTownMessageFragment();
        seeTownMessageFragment.show(getSupportFragmentManager(), "Dialog");
    }

    //Open logout fragment
    private void logoutFragment() {
        LogoutFragment logoutFragment = new LogoutFragment();
        logoutFragment.show(getSupportFragmentManager(), "Dialog");
    }
}

