package com.example.northernvillagewaterandsewageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    protected String pinCheck = "123";
    protected String userType;
    protected Button englishButton, frenchButton, inuktitutButton;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        englishButton = findViewById(R.id.englishbutton);
        frenchButton = findViewById(R.id.frenchbutton);
        inuktitutButton = findViewById(R.id.inuktitutbutton);

        //getSupportFragmentManager().beginTransaction()
        //        .replace(android.R.id.content, new SettingsFragment())
        //        .commit();

        //shows back button
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        settingsUI();

    }

    // sets the desired language for the app
    private void setAppLanguage(String language) {
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();

        config.setLocale(new Locale(language.toLowerCase()));

        resources.updateConfiguration(config, displayMetrics);
    }

    // Changes app's language depending on the option pressed
    protected void settingsUI() {

        englishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppLanguage("en");
            }
        });
        frenchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppLanguage("fr");
            }
        });
        inuktitutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppLanguage("iu");
            }
        });
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

        goToLoginActivity();

        SharedPreferences sharedPreferences =
                getSharedPreferences(getString(R.string.userInfo), Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(getString(R.string.user_name), null);
        String userPin = sharedPreferences.getString(getString(R.string.user_pin), null);

    }

    //To go to Login activity
    public void goToLoginActivity()
    {
        Intent intent_L = new Intent(this, LoginActivity.class);
        startActivity(intent_L);
    }
    //To go to Manager activity
    public void goToManagerActivity()
    {
        Intent intent_M = new Intent(this, ManagerActivity.class);
        startActivity(intent_M);
    }
    //To go to Resident activity
    public void goToResidentActivity()
    {
        Intent intent_R = new Intent(this, ResidentActivity.class);
        startActivity(intent_R);
    }
    //To go to Driver activity
    public void goToDriverActivity()
    {
        Intent intent_D = new Intent(this, DriverActivity.class);
        startActivity(intent_D);
    }
}
