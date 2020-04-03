package com.example.northernvillagewaterandsewageapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.northernvillagewaterandsewageapp.ObjectClasses.User;
import com.example.northernvillagewaterandsewageapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class RemoveResidentFragment extends DialogFragment {

    protected EditText UsernameEditText;
    protected Button CancelButton;
    protected Button RemoveUserButton;

    private RequestQueue mQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_remove_resident, container, false);

        //connects the edit texts to the Java file
        UsernameEditText = view.findViewById(R.id.residentNameEditText);
        //connects the buttons to the Java file
        CancelButton = view.findViewById(R.id.cancelRemoveResidentButton);
        RemoveUserButton = view.findViewById(R.id.removeResidentButton);

        mQueue = Volley.newRequestQueue(getActivity());

        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        RemoveUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gets the parameters for the new user
                String username = UsernameEditText.getText().toString();
                if (!username.equals("")) {
                    //removes a user by using the db
                    String url = "http://13.59.214.194:5000/remove_resident/{username}".replace("{username}", username);
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
                    Toast.makeText(getActivity(), "Resident " + username + " removed", Toast.LENGTH_SHORT).show();

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
