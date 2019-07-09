package com.eco.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.eco.R;
import com.eco.entitys.ListeEntity;
import com.eco.entitys.XChangeEntity;
import com.eco.interfaces.IXChangePresenter;
import com.eco.presenters.XChangePresenter;
import com.eco.viewHolder.XChangeViewHolder;

import java.util.ArrayList;

public class XChangeAdapter extends RecyclerView.Adapter<XChangeViewHolder> {
    ArrayList<XChangeEntity> list;
    Context context;
    IXChangePresenter presenter;

    public XChangeAdapter(Context context, IXChangePresenter presenter) {
        this.context = context;
        list = new ArrayList<>();
        this.presenter = presenter;
    }

    public void removeItem(XChangeEntity xChangeEntity) {
        int position = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).id == xChangeEntity.id)
                position = i;
        }
        this.list.remove(position);
        notifyItemRemoved(position);
//        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public XChangeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        XChangeViewHolder xChangeViewHolder = new XChangeViewHolder(LayoutInflater.from(context).
                inflate(R.layout.x_change_item, viewGroup, false), context, presenter);
        return xChangeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull XChangeViewHolder xChangeViewHolder, int i) {
        xChangeViewHolder.bind(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(ListeEntity<XChangeEntity> result) {
        this.list.addAll(result.data);
        notifyDataSetChanged();
    }
}
