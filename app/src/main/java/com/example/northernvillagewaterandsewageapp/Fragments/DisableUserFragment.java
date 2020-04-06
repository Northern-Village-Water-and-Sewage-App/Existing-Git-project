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
import com.example.northernvillagewaterandsewageapp.DBHelper;
import com.example.northernvillagewaterandsewageapp.R;

import org.json.JSONArray;

import java.awt.font.TextAttribute;

public class DisableUserFragment extends DialogFragment {

    protected EditText usernameIDDisableUserEditText;
    protected Button disableUserButton;
    protected Button enableUserButton;

    private RequestQueue mQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_disable_user, container, false);

        //attaches the XML file to the java
        usernameIDDisableUserEditText = view.findViewById(R.id.UserIDDisableUserEditText);
        disableUserButton = view.findViewById(R.id.disableUserButton);
        enableUserButton = view.findViewById(R.id.enableUserButton);

        mQueue = Volley.newRequestQueue(getActivity());

        //sets the info for disabling the user in DB
        disableUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = usernameIDDisableUserEditText.getText().toString().trim();

                if (userName != "") {
                    //disables user
                    String url = "http://54.201.85.48:32132/disable_resident/<user_name>".replace("<user_name>", userName);
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
                    Toast.makeText(getActivity(), "Resident " + userName + " disabled", Toast.LENGTH_SHORT).show();

                    //dismiss the fragment if this went through
                    getDialog().dismiss();
                } else
                    Toast.makeText(getActivity(), "Invalid Entry", Toast.LENGTH_SHORT).show();


            }
        });

        //gets the dismiss button to get rid of the fragment and go back to whatever called this.
        enableUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = usernameIDDisableUserEditText.getText().toString().trim();
                if (userName != "") {
                    //enables user
                    String url = "http://54.201.85.48:32132/enable_resident/<user_name>".replace("<user_name>", userName);
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
                    Toast.makeText(getActivity(), "Resident " + userName + " enabled", Toast.LENGTH_SHORT).show();

                    //dismiss the fragment if this went through
                    getDialog().dismiss();
                } else
                    Toast.makeText(getActivity(), "Invalid Entry", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
