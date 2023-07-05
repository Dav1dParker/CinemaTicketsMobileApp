package com.example.cinemaapp.movies;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.R;

public class MyAdapter2 extends RecyclerView.Adapter<MyViewHolder2> {


    private Context context;
    private List<Item2> items;
    private SelectListener listener;

    public MyAdapter2(Context context, List<Item2> items, SelectListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new MyViewHolder2(LayoutInflater.from(context).inflate(R.layout.item2_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder2 holder, @SuppressLint("RecyclerView") int position) {
        holder.nameView.setText(items.get(position).getName());
        holder.ageView.setText(items.get(position).getAge());
        holder.imageView.setImageResource(items.get(position).getImage());
        holder.dateView.setText("Дата сеанса: " + items.get(position).getDate());
        holder.timeView.setText("Время сеанса: " + items.get(position).getTime());
        holder.numberOfTicketsView.setText("Кол-во билетов: " + items.get(position).getNumberOfTickets());
        holder.summView.setText("Стоимость: " + items.get(position).getSumm());
    }



    @Override
    public int getItemCount() {
        return items.size();
    }
}
