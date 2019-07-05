package com.eco.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eco.R;
import com.eco.entitys.FavoriteAddressEntity;
import com.eco.enums.MapAdapterMode;
import com.eco.interfaces.ILocationClick;
import com.eco.viewHolder.FavoriteAddressViewHolder;

import java.util.ArrayList;

import static com.eco.enums.MapAdapterMode.SELECT;

public class FavoriteAddressAdapter extends RecyclerView.Adapter<FavoriteAddressViewHolder> {
    ILocationClick  onClick;
    ArrayList<FavoriteAddressEntity> list;
    Context context;
    ArrayList<FavoriteAddressViewHolder> favoriteAddressViewHolderArrayList;
    MapAdapterMode mapAdapterMode;
    public FavoriteAddressAdapter(Context context, ILocationClick onClickListener) {
        this.context = context;
        this.onClick = onClickListener;
        favoriteAddressViewHolderArrayList = new ArrayList<>();
        mapAdapterMode = SELECT;
        list = new ArrayList<>();
    }
    public void unSelectAll(){
        for (int j=0;j<favoriteAddressViewHolderArrayList.size();j++)
            favoriteAddressViewHolderArrayList.get(j).root.setCardBackgroundColor(Color.parseColor("#686666"));
    }
    public void addItem(ArrayList<FavoriteAddressEntity> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setMode(MapAdapterMode mapAdapterMode) {
        this.mapAdapterMode = mapAdapterMode;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteAddressViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        FavoriteAddressViewHolder favoriteAddressViewHolder = new FavoriteAddressViewHolder(LayoutInflater.from(context).
                inflate(R.layout.favorite_location_item, viewGroup, false));
        favoriteAddressViewHolderArrayList.add(favoriteAddressViewHolder);
         return favoriteAddressViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAddressViewHolder favoriteLocationViewHolder,final int i) {
        favoriteLocationViewHolder.setAddressName(list.get(i).getName());
        if (mapAdapterMode == SELECT) {
            Drawable drawableCompat = ContextCompat.getDrawable(context, R.drawable.ic_location);
            favoriteLocationViewHolder.imageView.setImageDrawable(drawableCompat);
        }else{
            Drawable drawableCompat = ContextCompat.getDrawable(context, R.drawable.ic_pen);
            favoriteLocationViewHolder.imageView.setImageDrawable(drawableCompat);
        }
        favoriteLocationViewHolder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int j=0;j<favoriteAddressViewHolderArrayList.size();j++)
                    favoriteAddressViewHolderArrayList.get(j).root.setCardBackgroundColor(Color.parseColor("#686666"));
                favoriteAddressViewHolderArrayList.get(i).root.setCardBackgroundColor(Color.parseColor("#0097a7"));
                onClick.onClick(list.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }
}
