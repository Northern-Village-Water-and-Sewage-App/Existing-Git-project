package com.example.northernvillagewaterandsewageapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.Random;

public class CreateResidentFragment extends DialogFragment {

    protected EditText UsernameEditText;
    protected EditText HouseNumberEditText;
    protected EditText PinEditText;
    protected EditText tankNumEditText;
    protected Button RandomPinButton;
    protected Button AddUserButton;
    protected Button CancelAddUserButton;
    protected Button RemoveUserButton;

    private RequestQueue mQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_resident, container, false);

        //connects the edit texts to the Java file
        UsernameEditText = view.findViewById(R.id.driverNameEditText);
        HouseNumberEditText = view.findViewById(R.id.housNumEditText);
        PinEditText = view.findViewById(R.id.driverPinEditText);
        tankNumEditText = view.findViewById(R.id.tankNumEditText);
        //connects the buttons to the Java file
        RandomPinButton = view.findViewById(R.id.pinButton);
        AddUserButton = view.findViewById(R.id.addUserButton);
        CancelAddUserButton = view.findViewById(R.id.cancelAddUserButton);
        RemoveUserButton = view.findViewById(R.id.removeUserButton);

        mQueue = Volley.newRequestQueue(getActivity());

        //Makes buttons do something on click
        RandomPinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int randomInt = random.nextInt(999);
                PinEditText.setText(Integer.toString(randomInt));
            }
        });

        AddUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gets the parameters for the new user
                String username = UsernameEditText.getText().toString();
                String houseNum = HouseNumberEditText.getText().toString();
                String userPin = PinEditText.getText().toString();
                String tankNum = tankNumEditText.getText().toString();
                if (username != "" && houseNum != "" && tankNum != "" && userPin != "") {
                    //adds a user by using the db helper
                    String url = "http://13.59.214.194:5000/add_resident/{username}/{userpin}".replace("{username}", username).replace("{userpin}", PinEditText.getText().toString());
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
                    Toast.makeText(getActivity(), "Resident added", Toast.LENGTH_SHORT).show();

                    //dismiss the fragment if this went through
                    getDialog().dismiss();
                }
                else
                    Toast.makeText(getActivity(), "Invalid Entry", Toast.LENGTH_SHORT).show();
            }
        });

        CancelAddUserButton.setOnClickListener(new View.OnClickListener() {
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
                //removes user by using dbhelper
                DBHelper dbhelper = new DBHelper(getActivity());
                dbhelper.removeUser(username);
                //Note that it worked
                Toast.makeText(getActivity(), "User removed", Toast.LENGTH_SHORT).show();
                //dismiss the fragment if this went through
                getDialog().dismiss();
            }
        });
       return view;
    }
}
