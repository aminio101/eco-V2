package com.eco.viewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eco.R;
import com.eco.entitys.ItemEntity;

public class ShowRubbishViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    public ShowRubbishViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
    }

    public void bind(ItemEntity itemEntitiy) {
        name.setText(itemEntitiy.name);
    }
}
