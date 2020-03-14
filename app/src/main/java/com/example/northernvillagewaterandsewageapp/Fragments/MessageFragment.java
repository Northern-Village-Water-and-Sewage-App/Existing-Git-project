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

public class MessageFragment extends DialogFragment {


    protected EditText Message;
    protected Button CancelButton;
    protected Button EnterButton;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //attaches the XML file to this java file
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        //attach the buttons and edit text to the java file
        Message = view.findViewById(R.id.messageEditText);
        CancelButton = view.findViewById(R.id.cancelMessageButton);
        EnterButton = view.findViewById(R.id.enterMessageButton);

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
                                                                                                                                                    //****************************
            }
        });

       return view;
    }
}
