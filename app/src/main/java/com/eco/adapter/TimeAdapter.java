package com.eco.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eco.PV;
import com.eco.R;
import com.eco.entitys.RunDatePeriodsEntity;
import com.eco.interfaces.IOnSetTime;
import com.eco.viewHolder.TimeViewHolder;

import java.text.ParseException;
import java.util.ArrayList;

public class TimeAdapter extends RecyclerView.Adapter<TimeViewHolder> {
    ArrayList<TimeViewHolder> viewHolders;
    IOnSetTime onSetTime;

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String timeStamp;

    public TimeAdapter(Context context, IOnSetTime onSetTime) {
        this.context = context;
        this.onSetTime = onSetTime;
        viewHolders  = new ArrayList<>();
        list = new ArrayList<>();
    }

    public void add(ArrayList<RunDatePeriodsEntity> list){
        this.list.clear();
        notifyDataSetChanged();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    Context context;
    ArrayList<RunDatePeriodsEntity> list;
    @NonNull
    @Override
    public TimeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TimeViewHolder timeViewHolder = new TimeViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_time_list, viewGroup, false),context);
        viewHolders.add(timeViewHolder);
        return timeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimeViewHolder timeViewHolder, int i) {
        timeViewHolder.date.setText(list.get(i).startPeriod+" الی "+list.get(i).endPeriod);

        try {
            if ((PV.getDayNumber(timeStamp, 0) == list.get(i).runDate && PV.getHour(timeStamp) > list.get(i).startPeriod)
                    ||
                    (list.get(i).isEnable == 0) || (list.get(i).status == 0)
            ) {
                timeViewHolder.disable();
            } else
                timeViewHolder.setUnClick();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        timeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (int j = 0; j < viewHolders.size(); j++) {
                    if (list.size() > j) {
                        try {
                            if ((PV.getDayNumber(timeStamp, 0) == list.get(j).runDate && PV.getHour(timeStamp) >= list.get(j).startPeriod)
                                    ||
                                    (list.get(j).isEnable == 0) || (list.get(j).status == 0)
                            )
                                viewHolders.get(j).disable();
                            else
                                viewHolders.get(j).setUnClick();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else
                        viewHolders.get(j).setUnClick();
                }

                for (int j = 0; j < viewHolders.size(); j++) {
                    if (list.size() > j) {
                        try {
                            if (j == i) {
                                if ((PV.getDayNumber(timeStamp, 0) == list.get(i).runDate && PV.getHour(timeStamp) > list.get(i).startPeriod)
                                        ||
                                        (list.get(i).isEnable == 0) || (list.get(i).status == 0)
                                )
                                    Toast.makeText(context, "غیر قابل انتخاب", Toast.LENGTH_SHORT).show();

                                else {
                                    timeViewHolder.setClick();
                                    onSetTime.onClick(list.get(i));
                                }
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
