package com.eco.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.eco.R;

public class MainListViewHolder extends RecyclerView.ViewHolder {
    public   ImageView imageView;
    public  TextView name;
    public  TextView number;
    public MainListViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image);
        name  =itemView.findViewById(R.id.name);
        number = itemView.findViewById(R.id.number);
    }
}
