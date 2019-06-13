package com.eco.viewHolder;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eco.R;

public class FavoriteAddressViewHolder extends RecyclerView.ViewHolder {
    TextView addressName;
    public ConstraintLayout constraintLayout;

    public FavoriteAddressViewHolder(@NonNull View itemView) {
        super(itemView);
        constraintLayout = itemView.findViewById(R.id.root);
        addressName = itemView.findViewById(R.id.addressName);
    }

    public void setAddressName(String name) {
        addressName.setText(name);
    }
}
