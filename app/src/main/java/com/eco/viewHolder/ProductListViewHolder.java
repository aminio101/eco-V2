package com.eco.viewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dinuscxj.progressbar.CircleProgressBar;
import com.eco.CoustomTextView;
import com.eco.R;

public class ProductListViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public CoustomTextView name;
    public CoustomTextView score,nickName;
    public CircleProgressBar progressBar;
    public RelativeLayout btnSell;
    public ProductListViewHolder(@NonNull View itemView) {
        super(itemView);
        btnSell  = itemView.findViewById(R.id.relative_layout_sell_product_item);
        nickName = itemView.findViewById(R.id.nickName);
        progressBar = itemView.findViewById(R.id.line_progress);
        imageView = itemView.findViewById(R.id.imageView12);
        name = itemView.findViewById(R.id.textView13);
        score = itemView.findViewById(R.id.textView8);
    }
}
