package com.eco.viewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eco.PV;
import com.eco.R;
import com.eco.adapter.RequestListAdapter;
import com.eco.entitys.ItemEntity;
import com.eco.entitys.XChangeEntity;

import java.util.ArrayList;
import java.util.Map;

public class XChangeViewHolder extends RecyclerView.ViewHolder {
    public TextView date, status, name;
    public RecyclerView list;
    public Context context;
    CardView root;
    View line;
    public RelativeLayout singleItem;
    public XChangeViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        root = itemView.findViewById(R.id.root);
        this.context = context;
        date = itemView.findViewById(R.id.date);
        status = itemView.findViewById(R.id.status);
        name = itemView.findViewById(R.id.name);
        list = itemView.findViewById(R.id.list);
        line = itemView.findViewById(R.id.view);
        singleItem = itemView.findViewById(R.id.relative);
    }

    public void bind(XChangeEntity xChangeEntity) {
        try {
            if (xChangeEntity.status == 1) {
                status.setText("انحام شده ");
                status.setTextColor(Color.parseColor("#16B40A"));
                date.setText(" تاریخ انجام تبادل " + PV.convert(PV.dateToString(PV.stringToDate(xChangeEntity.deliverDate))));
            } else {
                status.setText(" انجام نشده ");
                status.setTextColor(Color.parseColor("#F30202"));
                date.setText(PV.convert(" تاریخ ثبت درخواست " + PV.dateToString(PV.stringToDate(xChangeEntity.requestDate))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (xChangeEntity.type == 1) {
            singleItem.setVisibility(View.INVISIBLE);
            ArrayList<ItemEntity> list = new ArrayList<>();
            for (Map.Entry<String, String> entry : xChangeEntity.list.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                ItemEntity item = new ItemEntity();
                item.setId(Integer.valueOf(value));
                item.setNumber(key);
                list.add(item);
            }
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < PV.rubbishList.size(); j++) {
                    if (PV.rubbishList.get(j).id == list.get(i).id) {
                        list.get(i).setName(PV.rubbishList.get(i).type);
                        break;
                    }
                }
            }
            RequestListAdapter requestListAdapter = new RequestListAdapter(context, list);
            this.list.setAdapter(requestListAdapter);
            this.list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        }else{
            singleItem.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
            name.setText("ست نشده");
        }

    }
}
