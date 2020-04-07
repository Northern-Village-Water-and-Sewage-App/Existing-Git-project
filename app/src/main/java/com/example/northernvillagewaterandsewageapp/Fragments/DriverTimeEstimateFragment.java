package com.example.northernvillagewaterandsewageapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.northernvillagewaterandsewageapp.DriverActivity;
import com.example.northernvillagewaterandsewageapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DriverTimeEstimateFragment extends DialogFragment {


    protected Button NoneButton;
    protected Button OnTheWayButton;
    protected Button BeforeBreakButton;
    protected Button TodayButton;
    protected Button TomorrowButton;
    protected Button CompleteButton;
    protected Button CancelButton;
    private int timeEstimate;

    private RequestQueue mQueue;
    private Integer pk;

    public interface VolleyCallBackFragment {
        void onSuccess();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //attaches the XML file to this java file
        View view = inflater.inflate(R.layout.fragment_driver_time_estimate, container, false);
        pk = getArguments().getInt("pk");
        //attach the buttons and edit text to the java file
        NoneButton = view.findViewById(R.id.noneDriverTimeEstimateButton);
        OnTheWayButton = view.findViewById(R.id.onTheWayDriverTimeEstimateButton);
        BeforeBreakButton = view.findViewById(R.id.beforeBreakDriverTimeEstimateButton);
        TodayButton = view.findViewById(R.id.todayDriverTimeEstimateButton);
        TomorrowButton = view.findViewById(R.id.tomorrowDriverTimeEstimateButton);
        CompleteButton = view.findViewById(R.id.completeDriverTimeEstimateButton);
        CancelButton = view.findViewById(R.id.cancelDriverTimeEstimateButton);
        mQueue = Volley.newRequestQueue(getActivity());

        //gets the drop down menu to work
        timeEstimate = 0;
        
        /*Spinner timeEstimatesSpinner = view.findViewById(R.id.timeEstimatesSpinner);
        timeEstimatesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timeEstimate = position;

                switch (timeEstimate) {
                    case 1: // None
                        Enter(6, new VolleyCallBackFragment() {
                            @Override
                            public void onSuccess() {
                                getDialog().dismiss();
                            }
                        });
                        break;
                    case 2: // On the Way
                        Enter(2, new VolleyCallBackFragment() {
                            @Override
                            public void onSuccess() {
                                getDialog().dismiss();
                            }
                        });
                        break;
                    case 3: // Before Break
                        Enter(3, new VolleyCallBackFragment() {
                            @Override
                            public void onSuccess() {
                                getDialog().dismiss();
                            }
                        });
                        break;
                    case 4: // Today
                        Enter(4, new VolleyCallBackFragment() {
                            @Override
                            public void onSuccess() {
                                getDialog().dismiss();
                            }
                        });
                        break;
                    case 5: // Tomorrow
                        Enter(5, new VolleyCallBackFragment() {
                            @Override
                            public void onSuccess() {
                                getDialog().dismiss();
                            }
                        });
                        break;
                    case 6: // Complete
                        Enter(1, new VolleyCallBackFragment() {
                            @Override
                            public void onSuccess() {
                                getDialog().dismiss();
                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        //dismisses the activity if the cancel button is pressed
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

       return view;
    }

    protected void Enter(Integer estimate, final VolleyCallBackFragment callBack){
        //2-6

        if(estimate == 1)
        {

                final String url_complete = "http://54.201.85.48:32132/demand_completed/{pk}".replace("{pk}",pk.toString());
                JsonObjectRequest request_complete = new JsonObjectRequest(Request.Method.GET,  url_complete, null,  new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response_complete){
                        callBack.onSuccess();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
            mQueue.add(request_complete);
            return;
        }
        else
        {
            String url_update = "http://54.201.85.48:32132/update_demand/<pk>/<time_estimate_fk>".replace("<pk>",pk.toString()).replace("<time_estimate_fk>",estimate.toString());
           // Toast.makeText(getActivity(), url_update, Toast.LENGTH_LONG).show();
            JsonObjectRequest request_update = new JsonObjectRequest(Request.Method.GET,  url_update, null,  new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response_update) {
                    callBack.onSuccess();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
            mQueue.add(request_update);
        }

        timeEstimate = 0;
        //Update database with new time estimate.                           //Not figured out yet
    }

}
