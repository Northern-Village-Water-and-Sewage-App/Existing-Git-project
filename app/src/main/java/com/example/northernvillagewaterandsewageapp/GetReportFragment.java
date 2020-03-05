package com.example.northernvillagewaterandsewageapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class GetReportFragment extends DialogFragment {

    protected EditText EmailAddressEditText;
    protected Integer Company;
    protected Button GetAllReportsButton;
    protected Button GetCompanyReportsButton;
    protected Button CancelGetReportButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_get_report, container, false);

        //connects the XML file to the Java
        EmailAddressEditText = view.findViewById(R.id.emailAddressReportEditText);
        GetAllReportsButton = view.findViewById(R.id.allReportsButton);
        GetCompanyReportsButton = view.findViewById(R.id.ReportsByCompanyButton);
        CancelGetReportButton = view.findViewById(R.id.cancelGetReportsButton);

        //gets the drop down menu to work
        Spinner companyReportsSpinner = view.findViewById(R.id.companyReportSpinner);
        companyReportsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Company = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //gets the cancel button to dismiss the fragment when pressed
        CancelGetReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        //what happens when this button is pressed?
        GetAllReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //what happens when this button is pressed?
        GetCompanyReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

       return view;
    }
}
