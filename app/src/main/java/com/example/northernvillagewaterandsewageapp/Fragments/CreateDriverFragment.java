package com.example.northernvillagewaterandsewageapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.northernvillagewaterandsewageapp.R;

public class CreateDriverFragment extends DialogFragment {

    protected EditText UsernameEditText;
    protected EditText PinEditText;
    protected Button RandomPinButton;
    protected Button AddUserButton;
    protected Button CancelAddUserButton;
    protected Button RemoveUserButton;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_driver, container, false);

        //connects the edit texts to the Java file
        UsernameEditText = view.findViewById(R.id.driverNameEditText);
        PinEditText = view.findViewById(R.id.driverPinEditText);
        //connects the buttons to the Java file
        RandomPinButton = view.findViewById(R.id.pinButton);
        AddUserButton = view.findViewById(R.id.addDriverButton);
        CancelAddUserButton = view.findViewById(R.id.cancelAddDriverButton);
        RemoveUserButton = view.findViewById(R.id.removeDriverButton);

        //Makes buttons do something on click
        RandomPinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                                                                                                                                                    //******
            }
        });
        AddUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                                                                                           //******

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
                                                                                                                                                                    //*******
            }
        });





       return view;
    }
}
