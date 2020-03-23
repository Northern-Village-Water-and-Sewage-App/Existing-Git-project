package com.example.northernvillagewaterandsewageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.northernvillagewaterandsewageapp.Fragments.SettingsFragment;
import com.example.northernvillagewaterandsewageapp.ObjectClasses.User;

import java.util.jar.Attributes;

public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

        //shows back button
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        SharedPreferences sharedPreferences =
                getSharedPreferences(getString(R.string.userInfo), Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString(getString(R.string.user_name), null);
        String userPin = sharedPreferences.getString(getString(R.string.user_pin), null);

        int pin = Integer.parseInt(userPin);
        int userType = new DBHelper().returnUserType(userName, pin);

        if (userType == 0) {
            goToManagerActivity();
        }
        else if (userType == 1) {
            goToResidentActivity();
        }
        else if (userType == 2) {
            goToDriverActivity();
        }
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
