package com.eco.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eco.R;

public class CatergoryShopViewHolder  extends RecyclerView.ViewHolder {
    public TextView name;
    public CatergoryShopViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.textViewName);
    }
    public void setName(String name){
        this.name.setText(name);
    }
}
