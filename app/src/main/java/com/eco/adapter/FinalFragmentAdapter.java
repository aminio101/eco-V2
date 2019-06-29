package com.eco.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eco.R;
import com.eco.entitys.RubbishEntity;
import com.eco.viewHolder.FinalViewHolder;

import java.util.ArrayList;

public class FinalFragmentAdapter extends RecyclerView.Adapter<FinalViewHolder> {
    Context context;
    ArrayList<RubbishEntity> list;
    View.OnClickListener onClickListener;
    boolean showDelete = true;

    public FinalFragmentAdapter(Context context, View.OnClickListener onClickListener) {
        this.context = context;
        list = new ArrayList<>();
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public FinalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FinalViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_final_requst, viewGroup, false));
    }

    public void add(ArrayList<RubbishEntity> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull FinalViewHolder finalViewHolder, int i) {
        finalViewHolder.name.setText(list.get(i).type);
        finalViewHolder.numberRubbish.setText(String.valueOf(list.get(i).number));
        finalViewHolder.number.setText(String.valueOf(i));
        finalViewHolder.delete.setTag(list.get(i));
        finalViewHolder.delete.setOnClickListener(onClickListener);
        if (showDelete)
            finalViewHolder.delete.setVisibility(View.VISIBLE);
        else
            finalViewHolder.delete.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void hideDelete() {
        showDelete = false;
        notifyDataSetChanged();
    }
}
