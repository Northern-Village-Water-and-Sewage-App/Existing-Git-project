package com.example.northernvillagewaterandsewageapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.northernvillagewaterandsewageapp.DBHelper;
import com.example.northernvillagewaterandsewageapp.R;

import java.awt.font.TextAttribute;

public class DisableUserFragment extends DialogFragment {

    protected EditText UsernameEditText;
    protected EditText timeDisableUserEditText;
    protected TextView disableUserMessage;
    protected Button CancelDisableUserButton;
    protected Button ConfirmDisableUserButton;
    protected Integer TimeTypeDisableUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_disable_user, container, false);

        //attaches the XML file to the java
        UsernameEditText = view.findViewById(R.id.UserIDDisableUserEditText);
        timeDisableUserEditText = view.findViewById(R.id.timeDIsableUserEditText);
        CancelDisableUserButton = view.findViewById(R.id.cancelDisableUserButton);
        ConfirmDisableUserButton = view.findViewById(R.id.confirmDisableUserButton);
        disableUserMessage = view.findViewById(R.id.disableUserMessage);

        //gets the dropdown menu to work
        Spinner TimeTypeSpinner = view.findViewById(R.id.timesDisableUserSpinner);
        TimeTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TimeTypeDisableUser = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //sets the info for disabling the user in DB
        ConfirmDisableUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = UsernameEditText.getText().toString().trim();
                int disableTime = Integer.parseInt(timeDisableUserEditText.getText().toString());
                int disableTimeDuration = TimeTypeDisableUser;

                //DBHelper dbHelper = null;
                //dbHelper.disableResident(userName, disableTime, disableTimeDuration);

                //disableUserMessage.setText(userName + " disabled for " + disableTime);
                disableUserMessage.setText("Disabled!");
            }
        });

        //gets the dismiss button to get rid of the fragment and go back to whatever called this.
        CancelDisableUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

       return view;
    }
}
