package com.example.northernvillagewaterandsewageapp.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.northernvillagewaterandsewageapp.ObjectClasses.User;
import com.example.northernvillagewaterandsewageapp.ObjectClasses.useItem;
import com.example.northernvillagewaterandsewageapp.R;
import com.example.northernvillagewaterandsewageapp.ResidentAnalyticsActivity;
import com.example.northernvillagewaterandsewageapp.SharedPreferenceHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ResidentUsageFragment extends DialogFragment {

    protected EditText EstimatedUseEt;
    protected EditText usageTypeEt;
    protected Button CancelButton;
    protected Button SaveButton;
    protected SharedPreferenceHelper sharedPreferenceHelper;
    private int estimatedUsage;
    private String useType;
    protected int use;
    protected int pos;
    protected String name;
    protected  ArrayList<useItem> newList;

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
        estimatedUsage = Integer.parseInt(EstimatedUseEt.getText().toString().trim());
        useType = usageTypeEt.getText().toString().trim();

        useItem useitem = new useItem(useType, estimatedUsage);

        //make the listArray
        ArrayList<useItem> newList = new ArrayList<>();
        loadData();

        //either adds a new item, if the position was set to -1, or replaces the item if the position another number
        if (pos == -1){
            newList.add(useitem);
        }
        else{
            newList.set(pos, useitem);
        }

        saveDate();

    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            //get the info out of the bundle
            use = bundle.getInt("use");
            pos = bundle.getInt("pos");
            name = bundle.getString("name");

            //set the info
            usageTypeEt.setText(name);
            EstimatedUseEt.setText(Integer.toString(use));
        }
    }

    private void saveDate(){
        //Use shared preferences to save
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("UsePreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(newList);
        editor.putString("use_list", json);
        editor.apply();

        //reloads the list on the resident analytics activity
        ((ResidentAnalyticsActivity)getActivity()).loadListView();
        //dismisses the fragment
        getDialog().dismiss();

    }

    private void loadData(){
        //get the list
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("UsePreferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("use_list", null);
        Type type = new TypeToken<ArrayList<useItem>>() {}.getType();
        newList = gson.fromJson(json, type);

        if (newList == null){
            newList = new ArrayList<>();
        }

    }
}
