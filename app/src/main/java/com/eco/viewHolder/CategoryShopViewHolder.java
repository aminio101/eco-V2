package com.eco.viewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eco.R;

public class CategoryShopViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private RelativeLayout root;

    public CategoryShopViewHolder(@NonNull View itemView) {
        super(itemView);
        root = itemView.findViewById(R.id.root);
        name = itemView.findViewById(R.id.textViewName);
    }
   public void clearWhite(){
        root.setBackgroundResource(R.drawable.background_item_category_shop_white);
    }
    public void setName(String name) {
        this.name.setText(name);
    }

    public TextView getName() {
        return name;
    }

    public RelativeLayout getRoot() {
        return root;
    }
}
