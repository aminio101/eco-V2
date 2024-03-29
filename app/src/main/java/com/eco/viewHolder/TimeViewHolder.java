package com.eco.viewHolder;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eco.CoustomTextView;
import com.eco.R;

public class TimeViewHolder extends RecyclerView.ViewHolder {
    public CoustomTextView date,header;
    public CardView root;
    Context context;

    public TimeViewHolder(@NonNull View itemView,Context context) {
        super(itemView);
        this.context = context;
        root = itemView.findViewById(R.id.root);
        date = itemView .findViewById(R.id.hour);
        header = itemView .findViewById(R.id.header);
    }
    public void setClick(){
        root.setCardBackgroundColor(context.getResources().getColor(R.color.orange));
        date.setTextColor(Color.parseColor("#FFFFFF"));
    }
    public void setUnClick(){
        root.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        date.setTextColor(context.getResources().getColor(R.color.orange));
    }
    public void setFastMode(){
        header.setText("درخواست آنلاین");
        header.setGravity(Gravity.CENTER);
        date.setVisibility(View.INVISIBLE);
        root.setCardBackgroundColor(Color.parseColor("#f0b142"));
    }

    public void disable(){
        root.setCardBackgroundColor(Color.parseColor("#546e7a"));

     }

}
