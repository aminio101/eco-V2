package com.eco.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eco.R;
import com.eco.entitys.StoreCategoryEntity;
import com.eco.interfaces.IGetShopItem;
import com.eco.viewHolder.CategoryShopViewHolder;

import java.util.ArrayList;

public class CategoryShopAdapter extends RecyclerView.Adapter<CategoryShopViewHolder> {
    Context context;
    ArrayList<StoreCategoryEntity> list;
    ArrayList<CategoryShopViewHolder> viewHolders;
    IGetShopItem iGetShopItem;
    public CategoryShopAdapter(Context context,IGetShopItem iGetShopItem) {
        this.context = context;
        this.iGetShopItem = iGetShopItem;
        viewHolders = new ArrayList<>();
        list = new ArrayList<>();
    }

    public void addItem(ArrayList<StoreCategoryEntity> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryShopViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CategoryShopViewHolder categoryShopViewHolder = new CategoryShopViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_category_shop, viewGroup, false));
        viewHolders.add(categoryShopViewHolder);
        return categoryShopViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryShopViewHolder categoryShopViewHolder, final int i) {
        categoryShopViewHolder.setName(list.get(i).categoryName);
        categoryShopViewHolder.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int j = 0; j < viewHolders.size(); j++)
                    viewHolders.get(j).clearWhite();
                viewHolders.get(i)
                        .getRoot().setBackgroundResource(R.drawable.background_item_category_shop);
                iGetShopItem.getShopItem(list.get(i).id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
