package com.example.northernvillagewaterandsewageapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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
import com.example.northernvillagewaterandsewageapp.ObjectClasses.User;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    protected EditText userName;
    protected EditText userPin;
    protected String pinCheck = "123";
    protected String userType;
    protected Button loginButton;
    private int pinInput;
    private DrawerLayout sideBarLogin;
    private ActionBarDrawerToggle toggleLogin;
    protected CheckBox rememberMe;
    protected SharedPreferenceHelper sharedPreferenceHelper;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.UserNameText);
        userPin = findViewById(R.id.PinText);
        loginButton = findViewById(R.id.LoginButton);
        rememberMe = findViewById(R.id.rememberMeCheckBox);

        sideBarLogin = findViewById(R.id.sideBar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggleLogin = new ActionBarDrawerToggle(this, sideBarLogin, R.string.open, R.string.close);
        sideBarLogin.addDrawerListener(toggleLogin);
        toggleLogin.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPreferenceHelper = new SharedPreferenceHelper(LoginActivity.this);
        /*SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");
        if (checkbox.equals("true")) {
            setUserPinFromUsername(userName.getText().toString().trim());

        sharedPreferences = getSharedPreferences(getString(R.string.userInfo), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getBoolean("remember", false)) {
            rememberMe.setChecked(true);
        }
        else {
            rememberMe.setChecked(false);
        }

        userName.setText(sharedPreferences.getString("user name", ""));
        userPin.setText(sharedPreferences.getString(getString(R.string.user_pin), ""));

        userName.addTextChangedListener(loginTextWatcher);
        userPin.addTextChangedListener(loginTextWatcher);

        mQueue = Volley.newRequestQueue(this);

    }

    // Makes the login button clickable only when both fields are full
    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = userName.getText().toString().trim();
            String pinInput = userPin.getText().toString();
            loginButton.setEnabled(!usernameInput.isEmpty() && !pinInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setUserPinFromUsername(userName.getText().toString().trim());
            }
        });

    }

    // to remember login details
    private void rememberMe() {
        // Option to keep user logged in
        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (compoundButton.isChecked()) {

                    editor.putString(getString(R.string.user_name), userName.getText().toString().trim());
                    editor.putString(getString(R.string.user_pin), Integer.toString(pinInput));
                    editor.putBoolean("remember", true);
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                }
                else if (!compoundButton.isChecked()) {

                    editor.putBoolean("remember", false);
                    editor.apply();
                }
            }
        });
    }

    private void setUserPinFromUsername(String username) {
        String url = "http://13.59.214.194:5000/get_user/{username}".replace("{username}", username);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject user = response.getJSONObject(0);
                    pinCheck = user.getString("pin");
                    userType = user.getString("user_type");

                    String pinInput = userPin.getText().toString();
                    if (pinCheck.equals(pinInput)) {
                        switch (userType) {
                            case "manager":
                                saveInfo(0);
                                goToManagerActivity();
                                break;
                            case "resident":
                                saveInfo(1);
                                goToResidentActivity();
                                break;
                            case "driver":
                                saveInfo(2);
                                goToDriverActivity();
                                break;
                        }
                    } else
                        Toast.makeText(LoginActivity.this, "Incorrect Username/Password", Toast.LENGTH_SHORT).show();
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

    //                  -------------------------Navigation bar related stuff------------------------
    @Override
    public void onBackPressed() {
        if (sideBarLogin.isDrawerOpen(GravityCompat.START)) {
            sideBarLogin.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggleLogin.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
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
        sideBarLogin.closeDrawer(GravityCompat.START);
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

    // Saves login info
    public void saveInfo(int type)
    {
        User user = new User(userName.getText().toString().trim(),Integer.parseInt(userPin.getText().toString()), type, "");
        sharedPreferenceHelper.saveUser(user, getString(R.string.user_name), getString(R.string.pin), getString(R.string.user_type));
    }

    public void goToManagerActivity() {
        Intent managerIntent = new Intent(LoginActivity.this, ManagerActivity.class);
        startActivity(managerIntent);
    }

    public void goToResidentActivity() {
        Intent residentIntent = new Intent(LoginActivity.this, ResidentActivity.class);
        startActivity(residentIntent);
    }

    public void goToDriverActivity() {
        Intent driverIntent = new Intent(LoginActivity.this, DriverActivity.class);
        startActivity(driverIntent);
    }

    // Goes to settings activity
    private void goToSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
