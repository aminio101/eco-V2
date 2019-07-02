package com.eco.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eco.PV;
import com.eco.PrefManager;
import com.eco.R;
import com.eco.entitys.ProductEntity;
import com.eco.viewHolder.ProductListViewHolder;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListViewHolder> {
    Context context;
    ArrayList<ProductEntity> list;
    View.OnClickListener sellOnclickListener;
    public ProductListAdapter(Context context, View.OnClickListener onClickListener){
        this.context = context;
        this.sellOnclickListener = onClickListener;
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
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(PV.getImage( list.get(i).pic)).into(producListViewHolder.imageView);
        int percent = (PrefManager.getInstance().getUser().score*100) / list.get(i).productPrice;
        if (percent >= 100 || percent<0)
            producListViewHolder.progressBar.setProgress(100);
        else
            producListViewHolder.progressBar.setProgress(percent);
        producListViewHolder.nickName.setText(list.get(i).productName);
        producListViewHolder.btnSell.setTag(this.list.get(i));
        producListViewHolder.btnSell.setOnClickListener(this.sellOnclickListener);
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
