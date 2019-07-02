package com.eco.viewHolder;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.eco.PV;
import com.eco.R;
import com.eco.entitys.DayEntity;

public class DayListViewHolder extends ViewHolder
{
   public TextView name,dayNumber,date;
   public CardView root;
   Context context;
    public DayListViewHolder(@NonNull View itemView,Context context) {
        super(itemView);
        this.context =context;
        name = itemView.findViewById(R.id.dayName);
        date = itemView.findViewById(R.id.date);
        root = itemView.findViewById(R.id.root);
        dayNumber = itemView.findViewById(R.id.dayNumber);
    }
    public void bind(DayEntity dayEntity,int i){
        name.setText(PV.numToDay(dayEntity.list.get(0).runDate));
        try {
            date.setText(PV.convert(PV.dateToString(dayEntity.date)));
        }catch (Exception e){
            e.printStackTrace();
        }
        if (i==0)
            dayNumber.setText("امروز");
        else
            dayNumber.setText(i+"روز دیگر");
    }
    public void setClick(){
        root.setCardBackgroundColor(context.getResources().getColor(R.color.orange));
        name.setTextColor(Color.parseColor("#FFFFFF"));
        dayNumber.setTextColor(Color.parseColor("#FFFFFF"));

    }
    public void setUnClick(){
        root.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        name.setTextColor(context.getResources().getColor(R.color.black));
        dayNumber.setTextColor(context.getResources().getColor(R.color.orange));

    }
}
