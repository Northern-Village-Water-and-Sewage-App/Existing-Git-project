package com.example.northernvillagewaterandsewageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    protected EditText userName;
    protected EditText userPin;
    protected Button loginButton;
    SharedPreferences sharedPreferences;
    static final String myPreference = "myPrefKey";
    static final String Name = "nameKey";
    static final String Pin = "pinKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.UserNameText);
        userPin = findViewById(R.id.PinText);
        loginButton = findViewById(R.id.LoginButton);

        userName.addTextChangedListener(loginTextWatcher);
        userPin.addTextChangedListener(loginTextWatcher);

    }

    public void save(View v)
    {
        String usernameInput = userName.getText().toString().trim();
        String pinInput = userPin.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Name, usernameInput);
        editor.putString(Pin, pinInput);
        editor.commit();
    }

    // Makes the login button clickable only when both fields are full
    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = userName.getText().toString().trim();
            String pinInput = userPin.getText().toString();
            loginButton.setEnabled(!usernameInput.isEmpty() && !pinInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };

    @Override
    protected void onResume() {
        super.onResume();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pseudo code: send username to database, get back pin and user type. If pin matches, it uses the database user type to go to next activity                     //NEED CODE HERE

                //for testing, gets the user type from the username edit text instead of the database                                                                     //FOR TESTING, TO BE REMOVED
                if (validateInput_Manager())
                {
                    goToManagerActivity();
                }
                else if (validateInput_Resident())
                {
                    goToResidentActivity();
                }
                else if (validateInput_Driver())
                {
                    goToDriverActivity();
                }
            }
        });
    }

    // Checks for valid manager name and pin input
    public boolean validateInput_Manager()
    {
        Toast toastInvalid = Toast.makeText(getApplicationContext(), "User does not exist!", Toast.LENGTH_SHORT);
        String usernameInput = userName.getText().toString().trim();
        String pinInput = userPin.getText().toString();

        int Pin = Integer.parseInt(userPin.getText().toString());
        if (usernameInput.equals("Matt") && (Pin == 111)){
            userName.setError(null); userPin.setError(null);
            return true;
        }
        else {
            toastInvalid.show();
            return false;
        }

    }
    // Checks for valid resident name and pin input
    public boolean validateInput_Resident()
    {
        Toast toastInvalid = Toast.makeText(getApplicationContext(), "User does not exist!", Toast.LENGTH_SHORT);
        String usernameInput = userName.getText().toString().trim();
        String pinInput = userPin.getText().toString();

        int Pin = Integer.parseInt(userPin.getText().toString());
        if (!usernameInput.equals("Red") || !(Pin == 222)){
            toastInvalid.show();
            return true;
        }
        else {
            userName.setError(null); userPin.setError(null);
            return false;
        }

    }
    // Checks for valid driver name and pin input
    public boolean validateInput_Driver()
    {
        Toast toastInvalid = Toast.makeText(getApplicationContext(), "User does not exist!", Toast.LENGTH_SHORT);
        String usernameInput = userName.getText().toString().trim();
        String pinInput = userPin.getText().toString();

        int Pin = Integer.parseInt(userPin.getText().toString());
        if (usernameInput.equals("Dean") && (Pin == 333)){
            userName.setError(null); userPin.setError(null);
            return true;
        }
        else {
            toastInvalid.show();
            return false;
        }

    }
    public void goToManagerActivity()
    {
        Intent managerIntent = new Intent(LoginActivity.this, ManagerActivity.class);
        startActivity(managerIntent);
    }
    public void goToResidentActivity()
    {
        Intent residentIntent = new Intent(LoginActivity.this, ResidentActivity.class);
        startActivity(residentIntent);
    }
    public void goToDriverActivity()
    {
        Intent driverIntent = new Intent(LoginActivity.this, DriverActivity.class);
        startActivity(driverIntent);
    }
}
