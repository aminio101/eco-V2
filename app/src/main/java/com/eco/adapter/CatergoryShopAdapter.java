package com.eco.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.eco.R;
import com.eco.entitys.StoreCategoryEntity;
import com.eco.viewHolder.CatergoryShopViewHolder;

import java.util.ArrayList;

public class CatergoryShopAdapter extends RecyclerView.Adapter<CatergoryShopViewHolder> {
    Context context;
    ArrayList<StoreCategoryEntity> list;

    public CatergoryShopAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void addItem(ArrayList<StoreCategoryEntity> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CatergoryShopViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CatergoryShopViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_category_shop, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CatergoryShopViewHolder catergoryShopViewHolder, int i) {
        catergoryShopViewHolder.setName(list.get(i).categoryName);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
