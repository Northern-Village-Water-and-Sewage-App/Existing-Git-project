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

public class ResidentUsageFragment extends DialogFragment {

    protected EditText EstimatedUseEt;
    protected EditText DishWashesEt;
    protected EditText WashesEt;
    protected EditText ShowersEt;
    protected Button UpdateButton;
    protected Button AddUseButton;
    protected Button CancelButton;
    protected Button SaveButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_resident, container, false);

        //connects the edit texts to the Java file
        EstimatedUseEt = view.findViewById(R.id.estimatedUseEditText);
        DishWashesEt = view.findViewById(R.id.dishesEditText);
        WashesEt = view.findViewById(R.id.washesEditText);
        ShowersEt = view.findViewById(R.id.showersEditText);
        //connects the buttons to the Java file
        UpdateButton = view.findViewById(R.id.updateUseButton);
        AddUseButton = view.findViewById(R.id.addUseButton);
        CancelButton = view.findViewById(R.id.cancelButton);
        SaveButton = view.findViewById(R.id.saveUseButton);

        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }
}
