package com.eco.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.eco.CoustomTextView;
import com.eco.PV;
import com.eco.R;
import com.eco.entitys.AcceptDriverEntity;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Array;

public class DialogAcceptDriver extends DialogFragment {

    public ImageView imageView;
    private CoustomTextView name, car, time,pelak1,pelak2;
    private AcceptDriverEntity acceptDriverEntity;
    View v;

    public DialogAcceptDriver(AcceptDriverEntity acceptDriverEntity){
        this.acceptDriverEntity = acceptDriverEntity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.dialog_accept, container, false);
        init();
        showData();
        return v;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }


    private void showData() {
        name.setText(acceptDriverEntity.name+" "+acceptDriverEntity.family);
        String pelak[]=acceptDriverEntity.carName.split(":");
        car.setText("خودرو"+" "+pelak[0]);
        pelak1.setText(pelak[1]+" "+pelak[2]+" "+pelak[3]);
        pelak2.setText(pelak[4]);
        time.setText("کاربرمحترم زمان تقریبی تا دریافت پسماند ازشما حدود "+acceptDriverEntity.time+" دقیقه خواهد بود. از صبر و شکیبایی شما سپاگزاریم ");
        Glide.with(this)
                .load(PV.getImage(acceptDriverEntity.thumbnail))
                .into(imageView);
    }

    private void init() {
        imageView = v.findViewById(R.id.imageView);
        time = v.findViewById(R.id.time);
        name = v.findViewById(R.id.textView_name);
        car = v.findViewById(R.id.textView_car);
        pelak1 = v.findViewById(R.id.textView_pelak1);
        pelak2 = v.findViewById(R.id.textView_pelak2);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
