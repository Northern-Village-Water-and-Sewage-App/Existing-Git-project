package com.example.northernvillagewaterandsewageapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DriverTimeEstimateFragment extends DialogFragment {


    protected Button NoneButton;
    protected Button OnTheWayButton;
    protected Button BeforeBreakButton;
    protected Button TodayButton;
    protected Button TomorrowButton;
    protected Button IgnoreButton;
    protected Button CancelButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //attaches the XML file to this java file
        View view = inflater.inflate(R.layout.fragment_driver_time_estimate, container, false);

        //attach the buttons and edit text to the java file
        NoneButton = view.findViewById(R.id.noneDriverTimeEstimateButton);
        OnTheWayButton = view.findViewById(R.id.onTheWayDriverTimeEstimateButton);
        BeforeBreakButton = view.findViewById(R.id.beforeBreakDriverTimeEstimateButton);
        TodayButton = view.findViewById(R.id.todayDriverTimeEstimateButton);
        TomorrowButton = view.findViewById(R.id.tomorrowDriverTimeEstimateButton);
        IgnoreButton = view.findViewById(R.id.ignoreDriverTimeEstimateButton);
        CancelButton = view.findViewById(R.id.cancelDriverTimeEstimateButton);

        //If the button is pressed, it uses a integer to say which button was pressed, and then uses the same function but with different inputs
        NoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Enter(0);
            }
        });

        OnTheWayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Enter(1);
            }
        });

        BeforeBreakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Enter(2);
            }
        });

        TodayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Enter(3);
            }
        });

        TomorrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Enter(4);
            }
        });

        IgnoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Enter(5);
            }
        });


        //dismisses the activity if the cancel button is pressed
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });



       return view;
    }

    protected void Enter(int estimate){
        //Update database with new time estimate.                           //Not figured out yet
    }
}
