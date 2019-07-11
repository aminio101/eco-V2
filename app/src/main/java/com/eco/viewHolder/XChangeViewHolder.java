package com.eco.viewHolder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.eco.PV;
import com.eco.R;
import com.eco.adapter.RequestListAdapter;
import com.eco.adapter.XChangeAdapter;
import com.eco.entitys.ItemEntity;
import com.eco.entitys.XChangeEntity;
import com.eco.interfaces.IXChangePresenter;
import com.eco.presenters.XChangePresenter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class XChangeViewHolder extends RecyclerView.ViewHolder {
    public TextView date, status, name, rate;
    public RecyclerView list;
    public Context context;
    ImageView delete;
    TextView textView;
    CardView cardView;
    ProgressBar progressBar;
    IXChangePresenter presenter;
    RelativeLayout root;
    View line;
    SwipeRevealLayout swipeRevealLayout;
    public RelativeLayout singleItem;
    public ImageView imageView;
    public void startProgress(){
        progressBar.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.INVISIBLE);
    }
    XChangeAdapter adapter;
    public void stopProgress(){
        progressBar.setVisibility(View.INVISIBLE);
        cardView.setVisibility(View.VISIBLE);
    }
    public XChangeViewHolder(@NonNull View itemView, Context context, IXChangePresenter presenter, XChangeAdapter adapter) {
        super(itemView);
        this.adapter = adapter;

        this.presenter = presenter;
        root = itemView.findViewById(R.id.root);
        this.context = context;
        swipeRevealLayout = itemView.findViewById(R.id.sweep);
        date = itemView.findViewById(R.id.date);
        status = itemView.findViewById(R.id.status);
        progressBar = itemView.findViewById(R.id.progress);
        cardView = itemView.findViewById(R.id.cardView);
        name = itemView.findViewById(R.id.name);
        textView = itemView.findViewById(R.id.text);
        delete = itemView.findViewById(R.id.delete);
        list = itemView.findViewById(R.id.list);
        imageView = itemView.findViewById(R.id.status_image);
        rate = itemView.findViewById(R.id.rate);
        line = itemView.findViewById(R.id.view);
        singleItem = itemView.findViewById(R.id.relative);
    }

    public void bind(XChangeEntity xChangeEntity) {
        swipeRevealLayout.close(false);
        stopProgress();
        root.setBackground(null);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.delete(xChangeEntity,XChangeViewHolder.this);
            }
        });
        String date = "";
        try {
            date = PV.convert(PV.dateToString(PV.stringToDate(xChangeEntity.deliverDate)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rate.setText(String.valueOf(xChangeEntity.citizenScore));
        if (xChangeEntity.citizenScore >= 0) {
            Drawable drawableCompat = ContextCompat.getDrawable(context, R.drawable.ic_arrow_up_green);
            imageView.setImageDrawable(drawableCompat);
        } else {
            Drawable drawableCompat = ContextCompat.getDrawable(context, R.drawable.ic_arrow_down_red);
            imageView.setImageDrawable(drawableCompat);
        }
        if (xChangeEntity.status == 1) {
            textView.setVisibility(View.VISIBLE);
            delete.setVisibility(View.GONE);
            Drawable drawableCompat = ContextCompat.getDrawable(context, R.drawable.x_change_bac_green);
            root.setBackground(drawableCompat);
            status.setText("انجام شده ");
            status.setTextColor(Color.parseColor("#16B40A"));
        } else {
            textView.setVisibility(View.GONE);
            delete.setVisibility(View.VISIBLE);
            Drawable drawableCompat = ContextCompat.getDrawable(context, R.drawable.x_change_bac_brown);
            root.setBackground(drawableCompat);
            status.setText(" انجام نشده ");
            status.setTextColor(Color.parseColor("#F30202"));
        }

        if (xChangeEntity.type != 1) {
            singleItem.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        } else {
            singleItem.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
        }


        if (xChangeEntity.type == 1) {
            ArrayList<ItemEntity> list = new ArrayList<>();
            for (Map.Entry<String, String> entry : xChangeEntity.list.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                ItemEntity item = new ItemEntity();
                item.setNumber(value);
                item.setId(Integer.valueOf(key));
                list.add(item);
            }
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < PV.rubbishList.size(); j++) {
                    if (PV.rubbishList.get(j).id == list.get(i).id) {
                        list.get(i).setName(PV.rubbishList.get(j).type);
                    }
                }

            }
            RequestListAdapter requestListAdapter = new RequestListAdapter(context, list);
            this.list.setAdapter(requestListAdapter);
            this.list.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
            this.date.setText(date + "  تحویل پسماند  ");
        } else if (xChangeEntity.type == 2) {
            ItemEntity item = new ItemEntity();
            for (Map.Entry<String, String> entry : xChangeEntity.list.entrySet()) {
                String value = entry.getValue();
                item.setName(value);
            }
            name.setText(item.getName());
            this.date.setText(date + " خرید ");
        } else if (xChangeEntity.type == 3) {
            ItemEntity item = new ItemEntity();
            for (Map.Entry<String, String> entry : xChangeEntity.list.entrySet()) {
                String value = entry.getValue();
                item.setName(value);
            }
            name.setText("واریز " + item.getName() + "تومان به حساب شما");
            this.date.setText(date + " انتقال شبا ");
        } else if (xChangeEntity.type == 4) {
            ItemEntity item = new ItemEntity();
            for (Map.Entry<String, String> entry : xChangeEntity.list.entrySet()) {
                String value = entry.getValue();
                item.setName(value);
            }

            if (xChangeEntity.status == 1)
                name.setText("دریافت " + item.getName() + " امتیاز ");
            else
                name.setText("هزینه  " + item.getName() + "امتیاز");

            this.date.setText(date + " مشاهده فیلم ");
        } else if (xChangeEntity.type == 6) {
            name.setText("دریافت " + xChangeEntity.citizenScore + " امتیاز ");
            this.date.setText(date + "عضویت در سامانه");


        } else if (xChangeEntity.type == 5) {
            name.setText("دریافت " + xChangeEntity.citizenScore + " امتیاز ");
            this.date.setText(date + " ارسال دعوت نامه");
        }

    }
}
