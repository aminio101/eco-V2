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

import com.eco.CoustomTextView;
import com.eco.R;
import com.eco.entitys.AcceptDriverEntity;
import com.google.gson.Gson;

import org.json.JSONObject;

public class DialogAcceptDriver extends DialogFragment {

    private CoustomTextView name, number, car, time;
    private AcceptDriverEntity acceptDriverEntity;
    ImageView ic_delete;
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
        name.setText(acceptDriverEntity.body);
        number.setText(acceptDriverEntity.title);
        JSONObject obj;
        try {
             obj = new JSONObject(acceptDriverEntity.time);
            time.setText("زمان تقریبی رسیدن راننده "+obj.getString("time")+" دقیقه ");
        }catch (Exception e){
            time.setText("زمان تقریبی رسیدن راننده نا معلوم است");
            e.printStackTrace();
        }
    }

    private void init() {

        number = v.findViewById(R.id.textView_pelak);
        time = v.findViewById(R.id.time);
        name = v.findViewById(R.id.textView_name);
        car = v.findViewById(R.id.textView_car);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
