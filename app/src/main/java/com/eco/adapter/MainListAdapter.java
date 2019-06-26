package com.eco.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eco.PV;
import com.eco.R;
import com.eco.entitys.RubbishEntity;
import com.eco.interfaces.MainAdapterListener;
import com.eco.viewHolder.MainListViewHolder;

import java.util.ArrayList;

public class MainListAdapter extends RecyclerView.Adapter<MainListViewHolder> {
    Context context;
    ArrayList<RubbishEntity> list;
    int selcetItem;
    ArrayList<MainListViewHolder> listViewHolders;
    MainAdapterListener onClickListener ;
    public MainListAdapter(Context context,MainAdapterListener mainAdapterListener) {
        this.context = context;
        this.onClickListener = mainAdapterListener;
        selcetItem = -1;
        list = new ArrayList<>();
        listViewHolders = new ArrayList<>();
    }

    public void addItem(ArrayList<RubbishEntity> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MainListViewHolder mainListViewHolder = new MainListViewHolder(LayoutInflater.from(context).
                inflate(R.layout.main_fragment_item, viewGroup, false), context);
        listViewHolders.add(mainListViewHolder);
        return mainListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainListViewHolder mainListViewHolder,final int i) {
        mainListViewHolder.name.setText(list.get(i).getType());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ecologo);
        requestOptions.error(R.drawable.ecologo);
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(PV.getImage(list.get(i).picture)).into(mainListViewHolder.imageView);

        mainListViewHolder.lineBottom.setVisibility(View.VISIBLE);
        mainListViewHolder.lineRight.setVisibility(View.VISIBLE);


        if (i == 0 || i == 1)
            mainListViewHolder.lineRight.setVisibility(View.GONE);
        if (i % 2 == 1)
            mainListViewHolder.lineBottom.setVisibility(View.GONE);

        if (mainListViewHolder.selected){
            mainListViewHolder.setHaveNumber();
            Toast.makeText(context,i+" Selecet ",Toast.LENGTH_LONG).show();

        }
        else mainListViewHolder.setNoneSelected();

        mainListViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainListViewHolder.selected=true;
                mainListViewHolder.setHaveNumber();
                Toast.makeText(context,i+"",Toast.LENGTH_LONG).show();
//                mainListViewHolder.setSelected();
//                onClickListener.onClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
