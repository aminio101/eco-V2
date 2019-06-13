package com.eco.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eco.R;
import com.eco.entitys.FavoriteAddressEntity;
import com.eco.viewHolder.CategoryShopViewHolder;
import com.eco.viewHolder.FavoriteAddressViewHolder;

import java.util.ArrayList;

public class FavoriteAddressAdapter extends RecyclerView.Adapter<FavoriteAddressViewHolder> {
    ArrayList<FavoriteAddressEntity> list;
    Context context;
    View.OnClickListener onClickListener;
    public FavoriteAddressAdapter(Context context, View.OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
        list = new ArrayList<>();
    }

    public void addItem(ArrayList<FavoriteAddressEntity> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public FavoriteAddressViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        FavoriteAddressViewHolder favoriteAddressViewHolder = new FavoriteAddressViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_favorite_location, viewGroup, false));
         return favoriteAddressViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAddressViewHolder favoriteLocationViewHolder, int i) {
        favoriteLocationViewHolder.setAddressName(list.get(i).getName());
        favoriteLocationViewHolder.constraintLayout.setTag(list.get(i));
        favoriteLocationViewHolder.constraintLayout.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
