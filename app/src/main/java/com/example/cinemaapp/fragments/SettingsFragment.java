package com.example.cinemaapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.cinemaapp.DataBase.DataBaseManager;
import com.example.cinemaapp.MainActivity;
import com.example.cinemaapp.R;

public class SettingsFragment extends Fragment {
    public Button buttonDB;
    private com.example.cinemaapp.DataBase.DataBaseManager DataBaseManager;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DataBaseManager = new DataBaseManager(getContext());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        buttonDB = view.findViewById(R.id.buttonInSettings);

        buttonDB.setOnClickListener(view1 -> {
            Toast.makeText(getContext(), "Все зарегестрированные пользователи успешно удалены",
                    Toast.LENGTH_SHORT).show();
            DataBaseManager.killDB();
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
