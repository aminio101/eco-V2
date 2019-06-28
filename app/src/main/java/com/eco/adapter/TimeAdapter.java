package com.eco.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eco.R;
import com.eco.entitys.RunDatePeriodsEntity;
import com.eco.viewHolder.DayListViewHolder;
import com.eco.viewHolder.TimeViewHolder;

import java.util.ArrayList;

public class TimeAdapter extends RecyclerView.Adapter<TimeViewHolder> {
    ArrayList<TimeViewHolder> viewHolders;
    public TimeAdapter(Context context) {
        this.context = context;
        viewHolders  = new ArrayList<>();
        list = new ArrayList<>();
    }
    public void add(ArrayList<RunDatePeriodsEntity> list){
        this.list.clear();
        this.list.addAll(list);
        viewHolders.clear();
        notifyDataSetChanged();
    }
    Context context;
    ArrayList<RunDatePeriodsEntity> list;
    @NonNull
    @Override
    public TimeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TimeViewHolder timeViewHolder = new TimeViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_time_list, viewGroup, false),context);
        viewHolders.add(timeViewHolder);
        return timeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimeViewHolder timeViewHolder, int i) {
        timeViewHolder.date.setText(list.get(i).startPeriod+" الی "+list.get(i).endPeriod);
        timeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int j =0 ; j<viewHolders.size();j++){
                    viewHolders.get(j).setUnClick();
                }
                timeViewHolder.setClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
