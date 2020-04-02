package com.example.northernvillagewaterandsewageapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.northernvillagewaterandsewageapp.R;

import org.json.JSONArray;

public class DriverReportFragment extends DialogFragment {

    protected Integer complaintType;
    protected Integer companyComplaint;
    protected EditText complaint;
    protected Button cancelDriverReport;
    protected Button confirmDriverReport;

    private RequestQueue mQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_driver_report, container, false);

        //connects the XML file to the Java
        complaint = view.findViewById(R.id.complaintDriverReportEditText);
        cancelDriverReport = view.findViewById(R.id.cancelGetReportsButton);
        confirmDriverReport = view.findViewById(R.id.ReportsByCompanyButton);

        mQueue = Volley.newRequestQueue(getActivity());

        complaintType = 0;
        companyComplaint = 0;

        //gets the drop down menu to work for the complaint types
        Spinner complaintTypeSpinner = view.findViewById(R.id.complaintTypeSpinner);
        complaintTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                complaintType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                complaintType = 0;
            }
        });
        //gets the drop down menu to work for the companies
        Spinner companySpinner = view.findViewById(R.id.companyComplaintSpinner);
        complaintTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                companyComplaint = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                companyComplaint = 0;
            }
        });

        //gets the cancel button to dismiss the fragment when pressed
        cancelDriverReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        //what happens when this button is pressed?
        confirmDriverReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gets complaint when the button is pressed
                String complaintText = complaint.getText().toString();
                //Checks to see that the complaint is not empty
                if (complaintText != "") {
                    //adds the report, by a json to the database
                    String url = "http://13.59.214.194:5000/add_report/{complainttype}/{company}/{complaint}".replace("{complainttype}", Integer.toString(complaintType +1)).replace("{company}", Integer.toString(companyComplaint + 1)).replace("{complaint}", complaintText);
                    final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {}
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                }
                            });
                    mQueue.add(request);
                    //Note that it worked
                    Toast.makeText(getActivity(), "Report added", Toast.LENGTH_SHORT).show();

                    //dismiss the fragment if this went through
                    getDialog().dismiss();
                }
                else
                    Toast.makeText(getActivity(), "Invalid Entry", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
