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

                //Pseudo code: send username to database, get back pin and user type, if pin matches use the database user type to goto next activity                     //NEED CODE HERE

                //for testing, gets the user type from the username edit text instead of the database                                                                     //FOR TESTING, TO BE REMOVED
                String userInput = userName.getText().toString().trim();
                int pinInput = Integer.parseInt(userPin.getText().toString());

                Toast toast = Toast.makeText(getApplicationContext(), "User does not exist!", Toast.LENGTH_LONG);

                //depending on user type goes to different activities
                if (!userInput.equals("Matt") || (pinInput != 111))
                {
                    toast.show();
                }
                else{
                    goToManagerActivity();
                }
                if (!userInput.equals("Red") || (pinInput != 222))
                {
                       toast.show();
                }
                else{
                    goToResidentActivity();
                }
                if (!userInput.equals("Dean") || (pinInput != 333))
                {
                        toast.show();
                }
                else {
                    goToDriverActivity();
                }

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
