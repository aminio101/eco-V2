package com.eco.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eco.R;
import com.eco.entitys.ItemEntity;
import com.eco.viewHolder.CommentViewHolder;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    Context context;
    ArrayList<ItemEntity> list;

    public CommentAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CommentViewHolder commentViewHolder= new CommentViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_requst_list, parent, false));
        return commentViewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
            holder.bind(list.get(position));
    }
    public void addItem(ArrayList<ItemEntity> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
