package com.example.northernvillagewaterandsewageapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.northernvillagewaterandsewageapp.R;

public class DisableUserFragment extends DialogFragment {

    protected EditText UsernameEditText;
    protected EditText timeDisableUserEditText;
    protected Button CancelDisableUserButton;
    protected Button ConfirmDisableUserButton;
    protected Integer TimeTypeDisableUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_disable_user, container, false);

        //attaches the XML file to the java
        UsernameEditText = view.findViewById(R.id.UserIDDisableUserEditText);
        timeDisableUserEditText = view.findViewById(R.id.timeDIsableUserEditText);
        CancelDisableUserButton = view.findViewById(R.id.cancelDisableUserButton);
        ConfirmDisableUserButton = view.findViewById(R.id.confirmDisableUserButton);

        //get steh dropdown menu to work
        Spinner TimeTypeSpinner = view.findViewById(R.id.timesDisableUserSpinner);
        TimeTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TimeTypeDisableUser = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //gets the dismiss button to get rid of the fragment and go back to whatever called this.
        CancelDisableUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

       return view;
    }
}
