package com.example.cinemaapp.fragments;

import android.annotation.SuppressLint;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.cinemaapp.DataBase.MyDatabase;

import com.example.cinemaapp.R;
import com.example.cinemaapp.movies.Item;
import com.example.cinemaapp.movies.MyAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private com.example.cinemaapp.DataBase.MyDatabase DataBaseManager;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DataBaseManager = new MyDatabase(getContext());
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        List<Item> items = new ArrayList<Item>();
        DataBaseManager.OpenDBfilms();
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.poster1);
        imageList.add(R.drawable.poster2);
        imageList.add(R.drawable.poster3);
        imageList.add(R.drawable.poster4);
        imageList.add(R.drawable.poster5);
        imageList.add(R.drawable.poster6);
        imageList.add(R.drawable.poster7);
        imageList.add(R.drawable.poster8);
        imageList.add(R.drawable.poster9);
        imageList.add(R.drawable.poster10);
        imageList.add(R.drawable.poster11);







        int len = DataBaseManager.getDatabaseSize();
        String tempName = null;
        String tempDescription = null;
        String tempAge = null;
        int tempImage = 0;
        List<String> tempList = new ArrayList<>();
        for (int i = 1; i <= len; i++)
        {
            tempList = DataBaseManager.getFilmInfo(i);
            tempImage = Integer.parseInt(tempList.get(3));
            tempName = tempList.get(0);
            tempDescription = tempList.get(1);
            tempAge = tempList.get(2);
            items.add(new Item(tempName, tempAge + "+", imageList.get(tempImage-1)));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAdapter(getContext(), items));
        DataBaseManager.close();
        return view;
    }
}