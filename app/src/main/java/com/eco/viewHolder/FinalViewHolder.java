package com.eco.viewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eco.CoustomTextView;
import com.eco.R;

public class FinalViewHolder extends RecyclerView.ViewHolder {
    public CoustomTextView name,number,numberRubbish;
    public ImageView delete;
    public FinalViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        number = itemView.findViewById(R.id.number);
        numberRubbish = itemView.findViewById(R.id.numberRubbish);
        delete = itemView.findViewById(R.id.delete);
    }
}
