package com.example.northernvillagewaterandsewageapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.northernvillagewaterandsewageapp.ObjectClasses.User;
import com.example.northernvillagewaterandsewageapp.R;
import com.example.northernvillagewaterandsewageapp.SharedPreferenceHelper;

public class ResidentUsageFragment extends DialogFragment {

    protected EditText EstimatedUseEt;
    protected EditText usageTypeEt;
    protected Button CancelButton;
    protected Button SaveButton;
    protected SharedPreferenceHelper sharedPreferenceHelper;
    private String estimatedUsage;
    private String useType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_resident_usage, container, false);

        //connects the edit texts to the Java file
        usageTypeEt = view.findViewById(R.id.usageTypeEditText);
        EstimatedUseEt = view.findViewById(R.id.estimatedUseEditText);
        //connects the buttons to the Java file
        CancelButton = view.findViewById(R.id.cancelButton);
        SaveButton = view.findViewById(R.id.saveUseButton);

        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUsage();
            }
        });

        return view;
    }

    private void saveUsage() {
        estimatedUsage = EstimatedUseEt.getText().toString().trim();
        useType = usageTypeEt.getText().toString().trim();

        User usage = new User(estimatedUsage, useType);
        sharedPreferenceHelper.saveResidentUsage(usage, getString(R.string.usage_type), getString(R.string.estimated_usage));
    }
}
