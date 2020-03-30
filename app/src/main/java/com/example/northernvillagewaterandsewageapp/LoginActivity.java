package com.example.northernvillagewaterandsewageapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {//implements NavigationView.OnNavigationItemSelectedListener {


    protected EditText userName;
    protected EditText userPin;
    protected String pinCheck = "123";
    protected String userType;
    protected Button loginButton;
    private int pinInput;
    private DrawerLayout sideBarLogin;
    private ActionBarDrawerToggle toggleLogin;


    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.UserNameText);
        userPin = findViewById(R.id.PinText);
        loginButton = findViewById(R.id.LoginButton);
        /*sideBarLogin = findViewById(R.id.sideBar);
        NavigationView navigationView = findViewById(R.id.design_navigation_view);

        navigationView.setNavigationItemSelectedListener(this);
        toggleLogin = new ActionBarDrawerToggle(this, sideBarLogin, R.string.open, R.string.close);
        sideBarLogin.addDrawerListener(toggleLogin);
        toggleLogin.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        userName.addTextChangedListener(loginTextWatcher);
        userPin.addTextChangedListener(loginTextWatcher);

        mQueue = Volley.newRequestQueue(this);

    }

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.languages:
                goToSettingsActivity();
                return true;
        }
        sideBarLogin.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggleLogin.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    // Saves login info
    public void saveInfo(View v)
    {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.userInfo), Context.MODE_PRIVATE);
        String usernameInput = userName.getText().toString().trim();
        //String pinInput = userPin.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.user_name), usernameInput);
        editor.putString(getString(R.string.user_pin), Integer.toString(pinInput));
        editor.apply();
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
                                goToManagerActivity();
                                break;
                            case "resident":
                                goToResidentActivity();
                                break;
                            case "driver":
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
