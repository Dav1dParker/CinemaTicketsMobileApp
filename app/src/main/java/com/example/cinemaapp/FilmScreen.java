package com.example.cinemaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinemaapp.DataBase.MyDatabase;
import com.example.cinemaapp.movies.PosterHandler;

import java.util.ArrayList;
import java.util.List;

public class FilmScreen extends AppCompatActivity {
    private MyDatabase DataBaseManager;
    private PosterHandler posterHandler;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
        id = Integer.parseInt(arguments.get("id").toString());
        setContentView(R.layout.activity_film_screen);
        DataBaseManager = new MyDatabase(this);
        DataBaseManager.OpenDBfilms();
        List<String> infoList = new ArrayList<>();
        infoList = DataBaseManager.getFilmInfo(id);
        TextView description = (TextView) findViewById(R.id.buyDescription);
        description.setText(infoList.get(1));

        posterHandler = new PosterHandler();
        List<Integer> imageList = posterHandler.getPosters();
        ImageView poster = (ImageView) findViewById(R.id.buyPoster);
        poster.setImageResource(imageList.get(id-1));
        TextView name = (TextView) findViewById(R.id.buyTitle);
        name.setText(infoList.get(0));
        TextView age = (TextView) findViewById(R.id.buyAge);
        age.setText(infoList.get(2) + "+");
        DataBaseManager.close();
    }

    public void buyTicket(View view)
    {
        Intent intent = new Intent(this, Ticket.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}