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
import com.example.cinemaapp.movies.Item2;
import com.example.cinemaapp.movies.MyAdapter2;
import com.example.cinemaapp.movies.PosterHandler;
import com.example.cinemaapp.movies.SelectListener;

import java.util.ArrayList;
import java.util.List;


public class AccountFragment extends Fragment implements SelectListener {
    private com.example.cinemaapp.DataBase.MyDatabase DataBaseManager;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DataBaseManager = new MyDatabase(getContext());
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview2);
        List<Item2> items = new ArrayList<Item2>();
        DataBaseManager.OpenDBTickets();
        PosterHandler posterHandler = new PosterHandler();
        List<Integer> imageList = posterHandler.getPosters();


        String tempName = null;
        String tempDescription = null;
        String tempAge = null;
        int tempImage = 0;
        String tempDate = null;
        String tempTime = null;
        int tempNumberOfTickets = 0;
        String tempSumm = null;
        int mvID = 0;

        ArrayList<ArrayList<String>> listOLists = new ArrayList<ArrayList<String>>();
        listOLists = DataBaseManager.getTicketInfo(PosterHandler.UserName);

        int lenA = listOLists.size();
        DataBaseManager.OpenDBfilms();
        for (int i = 0; i < lenA; i++) {
            ArrayList<String> onelist = listOLists.get(i);
            mvID = Integer.parseInt(onelist.get(0));
            List<String> filmlist = DataBaseManager.getFilmInfo(mvID);
            tempName = filmlist.get(0);
            tempAge = filmlist.get(2);
            tempImage = Integer.parseInt(filmlist.get(3));
            tempDate = onelist.get(1);
            tempTime = onelist.get(2);
            tempNumberOfTickets = Integer.parseInt(onelist.get(3));
            tempSumm = onelist.get(4);
            items.add(new Item2(tempName, tempAge + "+", imageList.get(tempImage - 1), mvID, tempDate, tempTime, tempNumberOfTickets, tempSumm));

        }


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAdapter2(getContext(), items, this));
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