package com.eco.viewHolder;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eco.PV;
import com.eco.R;
import com.eco.entitys.RubbishEntity;
import com.eco.interfaces.MainAdapterListener;

public class MainListViewHolder {
    public   ImageView imageView;
    public  TextView name;
    public  TextView number;
    public RelativeLayout root;
    public boolean haveNumber;
    public boolean isSelected;
    public Context context;
    public View lineRight, lineLeft, lineBottom;
    public LayoutInflater inflater;
    public View itemView;
    RubbishEntity rubbishEntity;
    MainAdapterListener mainAdapterListener;
    public int num = 0;
    public MainListViewHolder(Context context, RubbishEntity
            rubbishEntity, int position,MainAdapterListener listener) {

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
        haveNumber = false;
        lineRight = itemView.findViewById(R.id.line_right);


        isSelected = false;
        haveNumber = false;

        name.setText(rubbishEntity.type);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ecologo);
        requestOptions.error(R.drawable.ecologo);
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(PV.getImage(rubbishEntity.picture)).into(imageView);
        number.setText("0");


        if (position == 0 || position == 1)
            lineRight.setVisibility(View.GONE);
        if (position % 2 == 1)
            lineBottom.setVisibility(View.GONE);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected();
                listener.onClick(position, rubbishEntity);
            }
        });

    }

    public int addNum() {
        num++;
        number.setText(String.valueOf(num));
        return num;
    }

    public int mines() {
        num--;
        if (num <= 0) {
            num = 0;
            setNoneSelected();
            isSelected = false;
            haveNumber = false;
        }
        number.setText(String.valueOf(num));
        return num;
    }

    public void setNoneSelected() {
        root.setBackgroundColor(Color.parseColor("#DFDCDC"));
        root.setBackgroundColor(Color.parseColor("#DFDCDC"));
        name.setTextColor(Color.parseColor("#AFADAD"));
        imageView.setColorFilter(Color.parseColor("#AFADAD"));
        number.setVisibility(View.GONE);
    }

private void setSelected() {
    name.setTextColor(Color.parseColor("#F77401"));
    root.setBackgroundColor(Color.parseColor("#FFFFFF"));
    number.setVisibility(View.GONE);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        imageView.setForeground(context.getDrawable(R.drawable.background_column_main_page));
    else
        imageView.setColorFilter(Color.parseColor("#F77401"));
}

    public void setHaveNumber() {
        root.setBackgroundColor(Color.parseColor("#DFDCDC"));
        name.setTextColor(Color.parseColor("#0A3F75"));
        number.setVisibility(View.VISIBLE);
        imageView.setColorFilter(Color.parseColor("#0A3F75"));
    }
}
