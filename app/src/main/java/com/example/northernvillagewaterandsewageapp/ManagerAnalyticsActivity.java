package com.example.northernvillagewaterandsewageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.northernvillagewaterandsewageapp.Fragments.ManagerTimeEstimateFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManagerAnalyticsActivity extends AppCompatActivity {

    EditText averageWaitTimeET, waterWaitTimeET, sewageWaitTimeET, averageWaitLastMonthET, waterWaitTimeLastET, sewageWaitTimeLastET, callsThisMonthET, callsLastMonthET;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_analytics);
        mQueue = Volley.newRequestQueue(this);

        averageWaitTimeET = findViewById(R.id.averageWaitTimeET);
        waterWaitTimeET = findViewById(R.id.waterWaitTimeET);
        sewageWaitTimeET = findViewById(R.id.sewageWaitTimeET);
        averageWaitLastMonthET = findViewById(R.id.averageWaitLastMonthET);
        waterWaitTimeLastET = findViewById(R.id.waterWaitTimeLastET);
        sewageWaitTimeLastET = findViewById(R.id.sewageWaitTimeLastET);
        callsThisMonthET = findViewById(R.id.callsThisMonthET);
        callsLastMonthET = findViewById(R.id.callsLastMonthET);

        setupInfo();
    }

    public void setupInfo(){
        String url = "http://54.201.85.48:32132/get_monthly_stats/";
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject stats = response.getJSONObject(0);
                    averageWaitTimeET.setText(stats.getString("this_month_all_avg"));
                    //makes clicking on an item from the worklist pull up the manager time estimate fragment, with the information it needs to update the database
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
