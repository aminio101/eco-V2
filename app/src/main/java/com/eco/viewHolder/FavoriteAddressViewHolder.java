package com.eco.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eco.R;

public class FavoriteAddressViewHolder extends RecyclerView.ViewHolder {
    TextView addressName;
    public CardView root;

    public FavoriteAddressViewHolder(@NonNull View itemView) {
        super(itemView);
        root = itemView.findViewById(R.id.root);
        addressName = itemView.findViewById(R.id.addressName);
    }

    public void setAddressName(String name) {
        addressName.setText(name);
    }
}
