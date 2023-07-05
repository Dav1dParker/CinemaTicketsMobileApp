package com.example.cinemaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinemaapp.DataBase.MyDatabase;
import com.example.cinemaapp.movies.PosterHandler;

import java.util.ArrayList;
import java.util.List;

public class Ticket extends AppCompatActivity {

    private MyDatabase DataBaseManager;
    private PosterHandler posterHandler;
    private int id;
    private String currentDate = "Сегодня";
    private int nubmerOfTickets = 1;
    private TextView counter;
    private TextView summ;
    private TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
        id = Integer.parseInt(arguments.get("id").toString());
        setContentView(R.layout.activity_ticket);
        DataBaseManager = new MyDatabase(this);
        DataBaseManager.OpenDBfilms();
        List<String> infoList = new ArrayList<>();
        infoList = DataBaseManager.getFilmInfo(id);

        posterHandler = new PosterHandler();
        List<Integer> imageList = posterHandler.getPosters();
        ImageView poster = (ImageView) findViewById(R.id.ticketPoster);
        poster.setImageResource(imageList.get(id - 1));

        TextView name = (TextView) findViewById(R.id.ticketName);
        name.setText(infoList.get(0));

        DataBaseManager.close();

        time = (TextView) findViewById(R.id.ticketSetTime);

        CalendarView calender = (CalendarView) findViewById(R.id.ticketCalendarView);
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                currentDate = dayOfMonth + "." + (month + 1) + "." + year;

            }
        });
    }

    public void plus(View view) {
        nubmerOfTickets += 1;
        counter = findViewById(R.id.ticketCount);
        counter.setText(Integer.toString(nubmerOfTickets));
        summ = findViewById(R.id.ticketResult);
        summ.setText(Integer.toString(nubmerOfTickets * 300) + "₽");
    }

    public void minus(View view) {
        if (nubmerOfTickets == 1) {
            Toast.makeText(this, "Количество билетов не может быть меньше 1", Toast.LENGTH_SHORT).show();
        } else {
            nubmerOfTickets -= 1;
            counter = findViewById(R.id.ticketCount);
            counter.setText(Integer.toString(nubmerOfTickets));
            summ = findViewById(R.id.ticketResult);
            summ.setText(Integer.toString(nubmerOfTickets * 300) + "₽");
        }
    }

    public void ticketComplete(View view) {
        DataBaseManager.OpenDBTickets();
        DataBaseManager.insertTicket(id, PosterHandler.UserName, currentDate, time.getText().toString(), nubmerOfTickets, nubmerOfTickets * 300);
        Toast.makeText(this, "Спасибо за покупку!", Toast.LENGTH_LONG).show();
        //DataBaseManager.close();
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }
}