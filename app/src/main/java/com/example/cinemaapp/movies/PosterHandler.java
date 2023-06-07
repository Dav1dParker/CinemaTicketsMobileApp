package com.example.cinemaapp.movies;

import com.example.cinemaapp.R;

import java.util.ArrayList;
import java.util.List;

public class PosterHandler {
    public List<Integer> getPosters()
    {
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
        return imageList;
    }

    public static String UserName = null;
}
