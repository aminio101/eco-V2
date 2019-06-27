package com.eco.viewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eco.R;
import com.eco.entitys.RubbishEntity;

public class MainListViewHolder {
    public   ImageView imageView;
    public  TextView name;
    public  TextView number;
    public RelativeLayout root;
    public boolean selected;
    public boolean isSelected;
    public Context context;
    public View lineRight, lineLeft, lineBottom;
    public LayoutInflater inflater;
    public View itemView;
    RubbishEntity rubbishEntity;
    public MainListViewHolder(Context context,RubbishEntity
                               rubbishEntity) {

        this.rubbishEntity = rubbishEntity;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        itemView = inflater.inflate(R.layout.main_fragment_item, null);

        this.context = context;
        imageView = itemView.findViewById(R.id.image);
        name  =itemView.findViewById(R.id.name);
        number = itemView.findViewById(R.id.number);
        lineBottom = itemView.findViewById(R.id.line_bottom);
        lineLeft = itemView.findViewById(R.id.line_left);
        root = itemView.findViewById(R.id.root);
        selected = false;
        lineRight = itemView.findViewById(R.id.line_right);




        name.setText(rubbishEntity.type);
    }

//    public void setNoneSelected() {
//        root.setBackgroundColor(Color.parseColor("#DFDCDC"));
//        root.setBackgroundColor(Color.parseColor("#DFDCDC"));
//        name.setTextColor(Color.parseColor("#AFADAD"));
//    }
//
//    public void setSelected() {
//        name.setTextColor(Color.parseColor("#F77401"));
//        root.setBackgroundColor(Color.parseColor("#FFFFFF"));
//    }
//
//    public void setHaveNumber() {
//        root.setBackgroundColor(Color.parseColor("#DFDCDC"));
//        name.setTextColor(Color.parseColor("#0A3F75"));
//        number.setVisibility(View.VISIBLE);
//    }
}
