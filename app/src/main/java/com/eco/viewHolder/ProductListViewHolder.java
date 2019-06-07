package com.eco.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eco.R;

public class ProductListViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView name;
    public TextView score;

    public ProductListViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView12);
        name = itemView.findViewById(R.id.textView13);
        score = itemView.findViewById(R.id.textView8);
    }
}
