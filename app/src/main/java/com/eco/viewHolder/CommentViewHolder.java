package com.eco.viewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eco.CoustomTextView;
import com.eco.PV;
import com.eco.R;
import com.eco.entitys.ItemEntity;

public class CommentViewHolder extends RecyclerView.ViewHolder {
    CoustomTextView name, number,score;

    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        number = itemView.findViewById(R.id.text_number);
        name = itemView.findViewById(R.id.text_name);
        score = itemView.findViewById(R.id.score);
    }

    public void bind(ItemEntity itemEntity) {
        for (int j = 0; j < PV.rubbishList.size(); j++) {
            if (PV.rubbishList.get(j).id == Integer.valueOf(itemEntity.name)) {
                name.setText(PV.rubbishList.get(j).type);
                score.setText(String.valueOf(PV.rubbishList.get(j).price/3));
            }
        }
        number.setText(itemEntity.number);
    }
}
