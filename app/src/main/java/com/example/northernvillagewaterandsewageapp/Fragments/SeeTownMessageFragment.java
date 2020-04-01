package com.example.northernvillagewaterandsewageapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.northernvillagewaterandsewageapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SeeTownMessageFragment extends DialogFragment {


    protected TextView message;
    protected String temp;
    private RequestQueue mQueue;
    protected View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //attaches the XML file to this java file
        view = inflater.inflate(R.layout.fragment_see_town_message, container, false);

        //attach the buttons and edit text to the java file
        message = view.findViewById(R.id.townMessageTextView);

        mQueue = Volley.newRequestQueue(getActivity());

       return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        //sets up a string to hold the message
        temp = "";

        //gets the last message from the database
        String url = "http://13.59.214.194:5000/get_latest_message/";
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject e;
                    e = response.getJSONObject(0);
                    temp = e.getString("messages");

                    message.setText(temp);

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
}
