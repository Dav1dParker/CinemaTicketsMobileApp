package com.example.cinemaapp.movies;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.FilmScreen;
import com.example.cinemaapp.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView nameView, emailView;
    public RelativeLayout relativeLayout;

    private final Context context;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        imageView = itemView.findViewById(R.id.imageView);
        nameView = itemView.findViewById(R.id.name);
        emailView = itemView.findViewById(R.id.age);
        relativeLayout = itemView.findViewById(R.id.main_container);
    }

    public void onClick(View v) {

        final Intent intent;

        intent = new Intent(context, FilmScreen.class);

        context.startActivity(intent);
    }
}
