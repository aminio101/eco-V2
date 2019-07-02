package com.eco.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eco.R;
import com.eco.entitys.DayEntity;
import com.eco.interfaces.IOnDayClickListener;
import com.eco.viewHolder.DayListViewHolder;

import java.util.ArrayList;

public class DayListAdapter extends RecyclerView.Adapter<DayListViewHolder> {
    ArrayList<DayEntity> list;
    Context context;
    IOnDayClickListener onDayClickListener;
    ArrayList<DayListViewHolder> dayListViewHolders;
    int dayNumber;
    public DayListAdapter(Context context, IOnDayClickListener onDayClickListener) {
        this.context = context;
        this.onDayClickListener = onDayClickListener;
        list = new ArrayList<>();
        dayListViewHolders = new ArrayList<>();
    }

    public void addItem(ArrayList<DayEntity> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DayListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        DayListViewHolder dayListViewHolder = new DayListViewHolder(LayoutInflater.from(context).
                inflate(R.layout.day_item_list, viewGroup, false),context);
        dayListViewHolders.add(dayListViewHolder);
        return dayListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DayListViewHolder dayListViewHolder, int i) {
        dayListViewHolder.bind(list.get(i), i);
        dayListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int j =0 ; j<dayListViewHolders.size();j++)
                    dayListViewHolders.get(j).setUnClick();
                dayListViewHolder.setClick();
                onDayClickListener.onClick(list.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
