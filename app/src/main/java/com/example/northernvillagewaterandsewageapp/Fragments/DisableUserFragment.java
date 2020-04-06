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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.northernvillagewaterandsewageapp.DBHelper;
import com.example.northernvillagewaterandsewageapp.R;

import java.awt.font.TextAttribute;

public class DisableUserFragment extends DialogFragment {

    protected EditText usernameIDDisableUserEditText;
    protected Button disableUserButton;
    protected Button enableUserButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_disable_user, container, false);

        //attaches the XML file to the java
        usernameIDDisableUserEditText = view.findViewById(R.id.UserIDDisableUserEditText);
        disableUserButton = view.findViewById(R.id.disable_resident);
        enableUserButton = view.findViewById(R.id.enableUserButton);


        //sets the info for disabling the user in DB
        disableUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = usernameIDDisableUserEditText.getText().toString().trim();

                getDialog().dismiss();
            }
        });

        //gets the dismiss button to get rid of the fragment and go back to whatever called this.
        enableUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = usernameIDDisableUserEditText.getText().toString().trim();
                
                getDialog().dismiss();
            }
        });

       return view;
    }
}
