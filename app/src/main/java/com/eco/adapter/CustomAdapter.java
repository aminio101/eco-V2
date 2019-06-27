package com.eco.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eco.PV;
import com.eco.R;
import com.eco.entitys.RubbishEntity;
import com.eco.viewHolder.MainListViewHolder;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    ArrayList<RubbishEntity> result;
    public Context context;
    private static LayoutInflater inflater = null;
    ArrayList<MainListViewHolder> listViewHolders;

    public CustomAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        result = new ArrayList<>();
        listViewHolders = new ArrayList<>();
    }

    public void addItem(ArrayList<RubbishEntity> result,ArrayList<MainListViewHolder> list) {
        this.result.addAll(result);
        this.listViewHolders.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {







//
//        lineBottom.setVisibility(View.VISIBLE);
//        lineRight.setVisibility(View.VISIBLE);
//
//
//        if (position == 0 || position == 1)
//            lineRight.setVisibility(View.GONE);
//        if (position % 2 == 1)
//            lineBottom.setVisibility(View.GONE);
//
//        if (listViewHolders.get(position).haveNumber) {
//            setHaveNumber(root, name, number);
//            Toast.makeText(context, position + " Selecet ", Toast.LENGTH_LONG).show();
//        } else setNoneSelected(root, name);
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listViewHolders.get(position).haveNumber = true;
//                setHaveNumber(root, name, number);
//                Toast.makeText(context, position + "", Toast.LENGTH_LONG).show();
////                setSelected();
////                onClickListener.onClick(i);
//            }
//        });



        return listViewHolders.get(position).itemView;
    }


}