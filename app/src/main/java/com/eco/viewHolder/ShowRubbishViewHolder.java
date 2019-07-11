package com.eco.viewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eco.CoustomTextView;
import com.eco.R;
import com.eco.entitys.ItemEntity;

public class ShowRubbishViewHolder extends RecyclerView.ViewHolder {
    CoustomTextView name,number;
    public ShowRubbishViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        number = itemView.findViewById(R.id.number);
    }

    public void bind(ItemEntity itemEntity) {
        name.setText(itemEntity.name);
        number.setText(String.valueOf(itemEntity.number)+"  کیلو  ");
    }
}
