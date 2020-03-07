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
    protected Integer userType;

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

                //Pseudo code, send username to database, get back pin and usertype, if pin matches use the database usertype to goto next activity                     //NEED CODE HERE

                //for testing, gets the usertype from the username edittext instead of the database                                                                     //FOR TESTING, TO BE REMOVED
                userType = Integer.parseInt(userName.getText().toString());

                //depending on usertype goes to different activities
                if (userType == 1){goToManagerActivity();}
                else if (userType == 2){goToResidentActivity();}
                else if (userType == 3){goToDriverActivity();}
            }
        });
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
