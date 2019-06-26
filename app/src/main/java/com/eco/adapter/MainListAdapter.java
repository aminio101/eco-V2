package com.eco.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eco.PV;
import com.eco.R;
import com.eco.entitys.RubbishEntity;
import com.eco.viewHolder.MainListViewHolder;

import java.util.ArrayList;

public class MainListAdapter extends RecyclerView.Adapter<MainListViewHolder> {
    Context context;
    ArrayList<RubbishEntity> list;

    public MainListAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void addItem(ArrayList<RubbishEntity> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MainListViewHolder mainListViewHolder = new MainListViewHolder(LayoutInflater.from(context).
                inflate(R.layout.main_fragment_item, viewGroup, false));
        return mainListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainListViewHolder mainListViewHolder, int i) {
        mainListViewHolder.name.setText(list.get(i).getType());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ecologo);
        requestOptions.error(R.drawable.ecologo);
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(PV.getImage(list.get(i).picture)).into(mainListViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
