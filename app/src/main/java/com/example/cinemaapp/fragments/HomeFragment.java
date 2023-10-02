package com.example.cinemaapp.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.DataBase.MyDatabase;
import com.example.cinemaapp.FilmScreen;
import com.example.cinemaapp.R;
import com.example.cinemaapp.movies.Item;
import com.example.cinemaapp.movies.MyAdapter;
import com.example.cinemaapp.movies.PosterHandler;
import com.example.cinemaapp.movies.SelectListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements SelectListener {
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
        PosterHandler posterHandler = new PosterHandler();
        List<Integer> imageList = posterHandler.getPosters();


        int len = DataBaseManager.getDatabaseSize();
        String tempName = null;
        String tempDescription = null;
        String tempAge = null;
        int tempImage = 0;
        List<String> tempList = new ArrayList<>();
        for (int i = 1; i <= len; i++) {
            tempList = DataBaseManager.getFilmInfo(i);
            tempName = tempList.get(0);
            tempAge = tempList.get(2);
            tempImage = Integer.parseInt(tempList.get(3));
            items.add(new Item(tempName, tempAge + "+", imageList.get(tempImage - 1), i));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAdapter(getContext(), items, this));
        DataBaseManager.close();
        return view;
    }

    @Override
    public void onItemClicked(Item myModel) {
        Intent intent = new Intent(getContext(), FilmScreen.class);
        intent.putExtra("id", myModel.getId());
        startActivity(intent);
    }
}