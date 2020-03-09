package com.example.northernvillagewaterandsewageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    protected EditText userName;
    protected EditText userPin;
    protected Button enterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.UserNameText);
        userPin = findViewById(R.id.PinText);
        enterButton = findViewById(R.id.EnterButton);

    }

    @Override
    protected void onResume() {
        super.onResume();

        enterButton.setOnClickListener(new View.OnClickListener() {
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
        Toast toastEmpty = Toast.makeText(getApplicationContext(), "One of the fields is empty!", Toast.LENGTH_SHORT);
        String userInput = userName.getText().toString().trim();
        String PIN_input = userPin.getText().toString().trim();

        if (userInput.isEmpty() || PIN_input.isEmpty()) {
            toastEmpty.show();
            return false;
        }
        int Pin = Integer.parseInt(userPin.getText().toString());
        if (userInput.equals("Matt") && (Pin == 111)){
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
        Toast toastEmpty = Toast.makeText(getApplicationContext(), "One of the fields is empty!", Toast.LENGTH_SHORT);
        String userInput = userName.getText().toString().trim();
        String PIN_input = userPin.getText().toString().trim();

        if (userInput.isEmpty() || PIN_input.isEmpty()) {
            toastEmpty.show();
            return false;
        }
        int Pin = Integer.parseInt(userPin.getText().toString());
        if (userInput.equals("Red") && (Pin == 222)){
            userName.setError(null); userPin.setError(null);
            return true;
        }
        else {
            toastInvalid.show();
            return false;
        }

    }
    // Checks for valid driver name and pin input
    public boolean validateInput_Driver()
    {
        Toast toastInvalid = Toast.makeText(getApplicationContext(), "User does not exist!", Toast.LENGTH_SHORT);
        Toast toastEmpty = Toast.makeText(getApplicationContext(), "One of the fields is empty!", Toast.LENGTH_SHORT);
        String userInput = userName.getText().toString().trim();
        String PIN_input = userPin.getText().toString().trim();

        if (userInput.isEmpty() || PIN_input.isEmpty()) {
            toastEmpty.show();
            return false;
        }
        int Pin = Integer.parseInt(userPin.getText().toString());
        if (userInput.equals("Dean") && (Pin == 333)){
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
