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

public class DriverReportFragment extends DialogFragment {

    protected Integer ComplaintType;
    protected EditText Complaint;
    protected Button CancelDriverReport;
    protected Button ConfirmDriverReport;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_driver_report, container, false);

        //connects the XML file to the Java
        Complaint = view.findViewById(R.id.complaintDriverReportEditText);
        CancelDriverReport = view.findViewById(R.id.cancelGetReportsButton);
        ConfirmDriverReport = view.findViewById(R.id.ReportsByCompanyButton);

        //gets the drop down menu to work
        Spinner complaintTypeSpinner = view.findViewById(R.id.companyReportSpinner);
        complaintTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ComplaintType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //gets the cancel button to dismiss the fragment when pressed
        CancelDriverReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        //what happens when this button is pressed?
        ConfirmDriverReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
