package com.example.northernvillagewaterandsewageapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.FragmentTransaction;

import android.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.widget.TextView;

import com.example.northernvillagewaterandsewageapp.Fragments.SettingsFragment;

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
        goToManagerActivity();
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
