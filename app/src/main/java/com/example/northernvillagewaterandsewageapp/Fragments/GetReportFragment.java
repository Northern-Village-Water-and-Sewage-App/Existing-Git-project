package com.example.northernvillagewaterandsewageapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class
GetReportFragment extends DialogFragment {

    protected EditText emailAddressEditText;
    protected Integer company;
    protected Button getAllReportsButton;
    protected Button getCompanyReportsButton;
    protected Button cancelGetReportButton;

    private RequestQueue mQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_get_report, container, false);

        mQueue = Volley.newRequestQueue(getActivity());

        //connects the XML file to the Java
        emailAddressEditText = view.findViewById(R.id.emailAddressReportEditText);
        getAllReportsButton = view.findViewById(R.id.allReportsButton);
        getCompanyReportsButton = view.findViewById(R.id.ReportsByCompanyButton);
        cancelGetReportButton = view.findViewById(R.id.cancelGetReportsButton);

        //gets the drop down menu to work
        company = 0;
        Spinner companyReportsSpinner = view.findViewById(R.id.companyReportSpinner);
        companyReportsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                company = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //gets the cancel button to dismiss the fragment when pressed
        cancelGetReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        //what happens when this button is pressed?
        getAllReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://13.59.214.194:5000/get_reports/";
                final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject e;
                            //make the body of the email
                            String hold = "";
                            for (int pos = 0; pos < response.length(); pos++) {
                                e = response.getJSONObject(pos);
                                hold += "Company: " + e.getString("company_name");
                                hold += "  Complaint Type: " + e.getString("complaint_type");
                                hold += "\nComplaint: " + e.getString("complaint") + "\n\n";
                            }

                            //send the email stuff
                            String recipient = emailAddressEditText.getText().toString();
                            String subject  = "Reports from water and sewage delivery drivers";

                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_EMAIL, recipient);
                            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                            intent.putExtra(Intent.EXTRA_TEXT, hold);

                            intent.setType("message/rfc822");
                            startActivity(Intent.createChooser(intent, "Choose an email client"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });
                mQueue.add(request);

            }
        });

        //what happens when this button is pressed?
        getCompanyReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://13.59.214.194:5000/get_reports/";
                final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject e;
                            String hold = "";
                            for (int pos = 0; pos < response.length(); pos++) {
                                e = response.getJSONObject(pos);
                                //checks to see if the report is for that company

                                Toast.makeText(getActivity(), Integer.toString(company) + Integer.toString(companyNameToNum(e.getString("company_name"))),Toast.LENGTH_SHORT).show();
                                if (company == companyNameToNum(e.getString("company_name"))) {
                                    //makes the body of the email
                                    hold += "Company: " + e.getString("company_name");
                                    hold += "  Complaint Type: " + e.getString("complaint_type");
                                    hold += "\nComplaint: " + e.getString("complaint") + "\n\n";
                                }
                            }

                            //Puts the email together
                            String recipient = emailAddressEditText.getText().toString();
                            String subject  = "Reports for " + companyNumToName(company) + "by water and sewage delivery drivers";

                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_EMAIL, recipient);
                            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                            intent.putExtra(Intent.EXTRA_TEXT, hold);

                            intent.setType("message/rfc822");
                            startActivity(Intent.createChooser(intent, "Choose an email client"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });
                mQueue.add(request);
            }
        });

       return view;
    }

    //converts the number in the database into a company name
    private String companyNumToName(int i){
        if (i == 0){return "KMHB";}
        else if (i == 1){return "KSB";}
        else if (i == 2){return "KRG";}
        else {return "Makivik";}
    }

    private int companyNameToNum(String i){
        if (i == "KMHB"){return 0;}
        else if (i == "KSB"){return 1;}
        else if (i == "KRG"){return 2;}
        else if (i == "Makivik"){return 3;}
        else return 5;
    }

    //converts the number in the database into a complaint
    private String complaintNumToName(String i){
        if (i == "1"){return "Complaint";}
        else if (i == "2"){return "Broken Lights";}
        else if (i == "3"){return "Malfuctioning Lights";}
        else {return "No need to call";}
    }

    private void sendEmail(String recipient, String subject, String text){


    }

}
