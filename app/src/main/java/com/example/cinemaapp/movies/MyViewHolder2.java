package com.example.cinemaapp.movies;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.R;

public class MyViewHolder2 extends RecyclerView.ViewHolder {


    ImageView imageView;
    TextView nameView, ageView, dateView, timeView, numberOfTicketsView, summView;
    public RelativeLayout relativeLayout;

    private final Context context;

    public MyViewHolder2(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        imageView = itemView.findViewById(R.id.imageView2);
        nameView = itemView.findViewById(R.id.name2);
        ageView = itemView.findViewById(R.id.age2);

        dateView = itemView.findViewById(R.id.date);
        timeView = itemView.findViewById(R.id.time);
        numberOfTicketsView = itemView.findViewById(R.id.numberOfTickets);
        summView = itemView.findViewById(R.id.summ);
        relativeLayout = itemView.findViewById(R.id.main_container2);
    }
}
