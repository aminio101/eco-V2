package com.eco.viewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eco.R;

public class TimeViewHolder extends RecyclerView.ViewHolder {
    public TextView date;
    public CardView root;
    Context context;

    public TimeViewHolder(@NonNull View itemView,Context context) {
        super(itemView);
        this.context = context;
        root = itemView.findViewById(R.id.root);
        date = itemView .findViewById(R.id.hour);
    }
    public void setClick(){
        root.setCardBackgroundColor(context.getResources().getColor(R.color.orange));
        date.setTextColor(Color.parseColor("#FFFFFF"));
    }
    public void setUnClick(){
        root.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        date.setTextColor(context.getResources().getColor(R.color.orange));
    }

    public void disable(){
        root.setCardBackgroundColor(Color.parseColor("#546e7a"));
     }

}
