package com.eco.viewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eco.PV;
import com.eco.R;
import com.eco.entitys.ItemEntity;

public class CommentViewHolder extends RecyclerView.ViewHolder {
    TextView name, number;

    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        number = itemView.findViewById(R.id.text_number);
        name = itemView.findViewById(R.id.text_name);
    }

    public void bind(ItemEntity itemEntity) {
        for (int j = 0; j < PV.rubbishList.size(); j++) {
            if (PV.rubbishList.get(j).id == Integer.valueOf(itemEntity.name)) {
                name.setText(PV.rubbishList.get(j).type);
            }
        }
        number.setText(itemEntity.number);
    }
}
