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
import com.example.northernvillagewaterandsewageapp.LoginActivity;
import com.example.northernvillagewaterandsewageapp.R;
import com.example.northernvillagewaterandsewageapp.SharedPreferenceHelper;

import org.json.JSONArray;

public class ManualDemandFragment extends DialogFragment {


    protected Integer demandType = 0;
    protected EditText username;
    protected Button cancelButton;
    protected Button enterButton;
    protected SharedPreferenceHelper sharedPreferenceHelper;
    private String ResidentName;

    private RequestQueue mQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //attaches the XML file to this java file
        View view = inflater.inflate(R.layout.fragment_manual_demand, container, false);

        //attach the buttons and edit text to the java file
        username = view.findViewById(R.id.usernameDemandEditText);
        cancelButton = view.findViewById(R.id.cancelManualDemandButton);
        enterButton = view.findViewById(R.id.enterButton);

        sharedPreferenceHelper = new SharedPreferenceHelper(getContext());

        ResidentName = sharedPreferenceHelper.getUserName(getString(R.string.user_name));
        username.setText(ResidentName);
        mQueue = Volley.newRequestQueue(getActivity());

        //Gets the drop down menu
        Spinner demandTypeSpinner = view.findViewById(R.id.demandTypeSpinner);

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


        //dismisses the activity if the cancel button is pressed
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        //what happens when the enter button is pressed?
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                if (demandType ==0){
                    String demand = "1";
                    usedatabase(demand, name);
                }
                else if (demandType ==1){
                    String demand = "2";
                    usedatabase(demand, name);
                }
                else{
                    String demand = "1";
                    String demand1 = "2";
                    usedatabase(demand, name);
                    usedatabase(demand1, name);
                }

            }
        });

       return view;
    }

    protected void usedatabase(String demand, String username){
        if (username != "" ) {
            //adds a user
            String url = "http://54.201.85.48:32132/add_manual_demand/<user_name>/<demand_type>".
                    replace("<user_name>", username).
                    replace("<demand_type>", demand);
            final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
            mQueue.add(request);
            //Note that it worked
            Toast.makeText(getActivity(), "Request Successful", Toast.LENGTH_SHORT).show();

            //dismiss the fragment if this went through
            getDialog().dismiss();
        } else
            Toast.makeText(getActivity(), "Invalid Entry", Toast.LENGTH_SHORT).show();
    }
}
