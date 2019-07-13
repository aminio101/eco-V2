package com.eco.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eco.PV;
import com.eco.PrefManager;
import com.eco.R;
import com.eco.entitys.RunDatePeriodsEntity;
import com.eco.enums.RequstMode;
import com.eco.interfaces.IOnSetTime;
import com.eco.viewHolder.TimeViewHolder;

import java.text.ParseException;
import java.util.ArrayList;

public class TimeAdapter extends RecyclerView.Adapter<TimeViewHolder> {
    ArrayList<TimeViewHolder> viewHolders;
    IOnSetTime onSetTime;
    int roleId;
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String timeStamp;

    public TimeAdapter(Context context, IOnSetTime onSetTime) {
        this.context = context;
        this.onSetTime = onSetTime;
        roleId = PrefManager.getInstance().getUser().roleId;
        viewHolders  = new ArrayList<>();
        list = new ArrayList<>();
    }

    public void add(ArrayList<RunDatePeriodsEntity> list){
        this.list.clear();
        int d= 0 ;
        notifyDataSetChanged();
        for (int i=0 ; i< list.size();i++){
            d =0 ;
            try {
                if (PV.getDayNumber(timeStamp, 0) == list.get(i).runDate &&PV.getHour(timeStamp) >= list.get(i).startPeriod) {
                    d=  1;
                }
                if (roleId==2&&PV.getDayNumber(timeStamp, 0) == list.get(i).runDate &&PV.getHour(timeStamp) >= list.get(i).startPeriod && PV.getHour(timeStamp) <list.get(i).endPeriod ){
                    this.list.add(list.get(i));
                }else{
                    if (d==0)
                        this.list.add(list.get(i));
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
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
            if ((PV.getDayNumber(timeStamp, 0) == list.get(i).runDate && PV.getHour(timeStamp) >= list.get(i).startPeriod)
                    ||
                    (list.get(i).isEnable == 0) || (list.get(i).status == 0)
            ) {
                if (roleId == 2 && PV.getHour(timeStamp) >= list.get(i).startPeriod && PV.getHour(timeStamp) < list.get(i).endPeriod) {
                    timeViewHolder.setFastMode();
                } else
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
                            ) {
                                if (roleId == 2 && PV.getHour(timeStamp) >= list.get(j).startPeriod && PV.getHour(timeStamp) < list.get(j).endPeriod)
                                    viewHolders.get(j).setFastMode();
                                else
                                    viewHolders.get(j).disable();
                            }
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
                                    if (roleId == 2 && PV.getHour(timeStamp) >= list.get(j).startPeriod && PV.getHour(timeStamp) <= list.get(j).endPeriod){
                                        timeViewHolder.setClick();
                                        onSetTime.onClick(list.get(i));
                                        PV.requstMode = RequstMode.FAST;
                                    }
                                    else
                                        Toast.makeText(context, "غیر قابل انتخاب", Toast.LENGTH_SHORT).show();

                                if (roleId == 2 && PV.getHour(timeStamp) >= list.get(j).startPeriod && PV.getHour(timeStamp) <= list.get(j).endPeriod){
                                    timeViewHolder.setClick();
                                    onSetTime.onClick(list.get(i));
                                    PV.requstMode = RequstMode.FAST;
                                }
                                else {
                                    timeViewHolder.setClick();
                                    onSetTime.onClick(list.get(i));
                                    PV.requstMode = RequstMode.NORMAL;
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
