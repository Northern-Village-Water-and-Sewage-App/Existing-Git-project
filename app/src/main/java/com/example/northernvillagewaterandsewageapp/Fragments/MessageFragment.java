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
import org.json.JSONException;
import org.json.JSONObject;

public class MessageFragment extends DialogFragment {

    protected EditText Message;
    protected Button CancelButton;
    protected Button EnterButton;

    private RequestQueue mQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //attaches the XML file to this java file
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        //attach the buttons and edit text to the java file
        Message = view.findViewById(R.id.messageEditText);
        CancelButton = view.findViewById(R.id.cancelMessageButton);
        EnterButton = view.findViewById(R.id.enterMessageButton);

        mQueue = Volley.newRequestQueue(getActivity());

        //dismisses the activity if the cancel button is pressed
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        //what happens when the enter button is pressed?
        EnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String townMessage = Message.getText().toString();

                if (townMessage != "") {
                    String url = "http://54.201.85.48:32132/add_message/{message}".replace("{message}", townMessage);
                    //Toast.makeText(getActivity(), townMessage, Toast.LENGTH_SHORT).show();
                    final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                JSONObject e;
                                e = response.getJSONObject(0);
                            }
                            catch (JSONException e){
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
                    //Note that it worked
                    Toast.makeText(getActivity(), "Message added", Toast.LENGTH_SHORT).show();
                    //dismiss the fragment if this went through
                    getDialog().dismiss();
                } else
                    Toast.makeText(getActivity(), "Invalid Entry", Toast.LENGTH_SHORT).show();
            }
        });

       return view;
    }
}
