package com.example.northernvillagewaterandsewageapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.northernvillagewaterandsewageapp.ObjectClasses.User;


public class SharedPreferenceHelper {
    private SharedPreferences sharedPreferences;
    private String name_key;
    private String pin_key;
    public SharedPreferenceHelper(Context context)
    {
        sharedPreferences = context.getSharedPreferences("ProfilePreference", Context.MODE_PRIVATE);
    }
    public void saveUser( User profile, String name_key, String pin_key, String type_key)//save a new profile
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();

        editor.putString(name_key, profile.getUserName());//save the name under profileName
        editor.putInt(pin_key, profile.getPIN());
        editor.putInt(type_key, profile.getUserType());
        editor.apply();//save the changes
    }
    //get the name
    public String getUserName(String name)
    {
        return sharedPreferences.getString(name, null);
    }
    public String getUserPin(String pin)//get the pin
    {
        return sharedPreferences.getString(pin, null);
    }
    public String getUserType(String type)
    {
        return sharedPreferences.getString(type, null);
    }
    public void deleteProfile()//remove the profile and all its saved data
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void saveResidentUsage(User usage, String estUse_key, String dishes_key, String washes_key, String showers_key) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(estUse_key, usage.getEstimatedUsage());//save the name under profileName
        editor.putString(dishes_key, usage.getDishes());
        editor.putString(washes_key, usage.getWashes());
        editor.putString(showers_key, usage.getShowers());
        editor.apply();//save the changes
    }
    public String getEstUsage(String usage) {
        return sharedPreferences.getString(usage, null);
    }
    public String getDishes(String dishes) {
        return sharedPreferences.getString(dishes, null);
    }
    public String getWashes(String washes) {
        return sharedPreferences.getString(washes, null);
    }
    public String getShowers(String showers) {
        return sharedPreferences.getString(showers, null);
    }

}
