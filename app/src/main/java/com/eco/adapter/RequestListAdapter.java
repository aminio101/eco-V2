package com.eco.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.eco.R;
import com.eco.entitys.ItemEntity;
import com.eco.viewHolder.ShowRubbishViewHolder;

import java.util.ArrayList;

public class RequestListAdapter extends RecyclerView.Adapter<ShowRubbishViewHolder> {
    Context context;
    public ArrayList<ItemEntity> list;

    public RequestListAdapter(Context context, ArrayList<ItemEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ShowRubbishViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ShowRubbishViewHolder ShowRubbishViewHolder = new ShowRubbishViewHolder(LayoutInflater.from(context).
                inflate(R.layout.rubbish_item, viewGroup, false));
        return ShowRubbishViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowRubbishViewHolder showRubbishViewHolder, int i) {
        showRubbishViewHolder.bind(list.get(i));
    }

    public void addItem(ArrayList<ItemEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
