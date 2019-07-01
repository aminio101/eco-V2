package com.eco.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.eco.R;
import com.eco.entitys.ListeEntity;
import com.eco.entitys.XChangeEntity;
import com.eco.viewHolder.ProductListViewHolder;
import com.eco.viewHolder.XChangeViewHolder;

import java.util.ArrayList;

public class XChangeAdapter extends RecyclerView.Adapter<XChangeViewHolder> {
    ArrayList<XChangeEntity> list;
    Context context;

    public XChangeAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public XChangeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        XChangeViewHolder xChangeViewHolder = new XChangeViewHolder(LayoutInflater.from(context).
                inflate(R.layout.x_change_item, viewGroup, false),context);
        return xChangeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull XChangeViewHolder xChangeViewHolder, int i) {
        xChangeViewHolder.bind(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(ListeEntity<XChangeEntity> result) {
        this.list.addAll(result.data);
        notifyDataSetChanged();
    }
}
