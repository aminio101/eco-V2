package com.eco.viewHolder;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eco.CoustomTextView;
import com.eco.R;

public class FavoriteAddressViewHolder extends RecyclerView.ViewHolder {
    public CoustomTextView addressName;
    public CardView root;
    public ImageView imageView;

    public FavoriteAddressViewHolder(@NonNull View itemView) {
        super(itemView);
        root = itemView.findViewById(R.id.root);
        addressName = itemView.findViewById(R.id.addressName);
        imageView= itemView.findViewById(R.id.image);
    }

    public void setAddressName(String name) {
        addressName.setText(name);
    }
}
