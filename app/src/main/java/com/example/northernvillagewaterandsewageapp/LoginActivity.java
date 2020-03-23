package com.example.northernvillagewaterandsewageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {


    protected EditText userName;
    protected EditText userPin;
    protected String pinCheck = "123";
    protected String userType;
    protected Button loginButton;

    public static final String userInfo = "infoKey";
    public static final String Name = "nameKey";
    public static final String Pin = "pinKey";

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.UserNameText);
        userPin = findViewById(R.id.PinText);
        loginButton = findViewById(R.id.LoginButton);

        userName.addTextChangedListener(loginTextWatcher);
        userPin.addTextChangedListener(loginTextWatcher);

        mQueue = Volley.newRequestQueue(this);

    }

    public void saveInfo(View v) {
        SharedPreferences sharedPreferences = getSharedPreferences(userInfo, Context.MODE_PRIVATE);
        String usernameInput = userName.getText().toString().trim();
        String pinInput = userPin.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Name, usernameInput);
        editor.putString(Pin, pinInput);
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
}
