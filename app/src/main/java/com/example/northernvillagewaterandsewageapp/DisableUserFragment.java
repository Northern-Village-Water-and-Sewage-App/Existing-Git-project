package com.example.northernvillagewaterandsewageapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DisableUserFragment extends DialogFragment {

    protected EditText UsernameEditText;
    protected EditText PinEditText;
    protected Button RandomPinButton;
    protected Button AddUserButton;
    protected Button CancelAddUserButton;
    protected Button RemoveUserButton;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_disable_user, container, false);



       return view;
    }
}
