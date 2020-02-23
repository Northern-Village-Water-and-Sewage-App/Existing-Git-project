package com.example.northernvillagewaterandsewageapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ManualDemandFragment extends DialogFragment {


    protected Integer demandType =-1;
    protected Integer timeEstimate =0;
    protected EditText HouseNumber;
    protected Button CancelButton;
    protected Button EnterButton;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //attaches the XML file to this java file
        View view = inflater.inflate(R.layout.fragment_manual_demand, container, false);

        //attach the buttons and edit text to the java file
        HouseNumber = view.findViewById(R.id.housNumEditText);
        CancelButton = view.findViewById(R.id.cancelManualDemandButton);
        EnterButton = view.findViewById(R.id.enterButton);

        //Gets the drop down menu
        Spinner demandTypeSpinner = view.findViewById(R.id.demandTypeSpinner);
        Spinner timeEstimateSpinner = view.findViewById(R.id.timeEstimateSpinner);


        //Finds what item is selected from the drop down menu for the demand type
        demandTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                demandType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Finds what item is selected from the drop down menu for the demand type
        timeEstimateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timeEstimate = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //dismisses the activity if the cancel button is pressed
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        //what happens when the enter button is pressed?
        EnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                                                                                                                                    //****************************
            }
        });

       return view;
    }
}
