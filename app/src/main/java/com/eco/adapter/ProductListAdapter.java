package com.eco.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eco.R;
import com.eco.entitys.ProductEntity;
import com.eco.viewHolder.ProductListViewHolder;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListViewHolder> {
    Context context;
    ArrayList<ProductEntity> list;
    public ProductListAdapter(Context context){
        this.context = context;
        list = new ArrayList<>();
    }
    public void addItem(ArrayList<ProductEntity> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ProductListViewHolder producListViewHolder = new ProductListViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_shop, viewGroup, false));
        return producListViewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder producListViewHolder, int i) {
        producListViewHolder.name.setText(list.get(i).productName);
        producListViewHolder.score.setText(String.valueOf(list.get(i).productPrice));
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ecologo);
        requestOptions.error(R.drawable.ecologo);
        Glide.with(context).setDefaultRequestOptions(requestOptions).load("http://185.252.29.12/storage/" + list.get(i).pic).into(producListViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clearItem() {
        this.list.clear();
        notifyDataSetChanged();
    }
}
