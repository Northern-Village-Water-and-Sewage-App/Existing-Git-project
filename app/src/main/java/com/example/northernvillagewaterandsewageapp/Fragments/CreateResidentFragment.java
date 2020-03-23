package com.example.northernvillagewaterandsewageapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.northernvillagewaterandsewageapp.DBHelper;
import com.example.northernvillagewaterandsewageapp.ObjectClasses.User;
import com.example.northernvillagewaterandsewageapp.R;

import java.util.Random;

public class CreateResidentFragment extends DialogFragment {

    protected EditText UsernameEditText;
    protected EditText HouseNumberEditText;
    protected EditText PinEditText;
    protected Button RandomPinButton;
    protected Button AddUserButton;
    protected Button CancelAddUserButton;
    protected Button RemoveUserButton;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_resident, container, false);

        //connects the edit texts to the Java file
        UsernameEditText = view.findViewById(R.id.driverNameEditText);
        HouseNumberEditText = view.findViewById(R.id.housNumEditText);
        PinEditText = view.findViewById(R.id.driverPinEditText);
        //connects the buttons to the Java file
        RandomPinButton = view.findViewById(R.id.pinButton);
        AddUserButton = view.findViewById(R.id.addUserButton);
        CancelAddUserButton = view.findViewById(R.id.cancelAddUserButton);
        RemoveUserButton = view.findViewById(R.id.removeUserButton);

        //Makes buttons do something on click
        RandomPinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int randomInt = random.nextInt(99999);
                PinEditText.setText(Integer.toString(randomInt));
            }
        });
        AddUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gets the parameters for the new user
                String username = UsernameEditText.getText().toString();
                String houseNum = HouseNumberEditText.getText().toString();
                //makes sure the pin isn't empty, or it would crash, and also that nothing else is empty
                if (PinEditText.getText().toString() !="" && username != "" && houseNum != "") {
                    //gets the pin, needs to be done after it checks if it is empty, or parse int will crash the app
                    Integer pin = Integer.parseInt(PinEditText.getText().toString());
                    //adds a user by using the db helper
                    DBHelper dbhelper = new DBHelper(getActivity());
                    dbhelper.addUser(new User(username, pin, 1, houseNum));
                    //Note that it worked
                    Toast toast = Toast.makeText(getActivity(), "User added", Toast.LENGTH_SHORT);
                    toast.show();
                    //dismiss the fragment if this went through
                    getDialog().dismiss();
                }
                //There is a bug here, it goes to the login page                                                        **************************************
                else{
                    Toast toast = Toast.makeText(getActivity(), "Invalid Entry", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
        CancelAddUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        RemoveUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gets the parameters for the new user
                String username = UsernameEditText.getText().toString();
                //removes user by using dbhelper
                DBHelper dbhelper = new DBHelper(getActivity());
                dbhelper.removeUser(username);
                //Note that it worked
                Toast toast = Toast.makeText(getActivity(), "User removed", Toast.LENGTH_SHORT);
                toast.show();
                //dismiss the fragment if this went through
                getDialog().dismiss();
            }
        });





       return view;
    }
}
