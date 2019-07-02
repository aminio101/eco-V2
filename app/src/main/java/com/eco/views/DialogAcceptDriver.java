package com.eco.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.eco.R;
import com.eco.entitys.AcceptDriverEntity;

public class DialogAcceptDriver extends Dialog {
    private ImageView imageView;
    private TextView name, number, car, time;
    private AcceptDriverEntity acceptDriverEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_accept);
        init();
        showData();
    }

    private void showData() {
        name.setText(acceptDriverEntity.body);
        number.setText(acceptDriverEntity.title);
    }

    private void init() {
        imageView = findViewById(R.id.imageView);
        number = findViewById(R.id.textView_pelak);
        time = findViewById(R.id.time);
        name = findViewById(R.id.textView_name);
        car = findViewById(R.id.textView_car);
    }

    public DialogAcceptDriver(@NonNull Context context, AcceptDriverEntity acceptDriverEntity) {
        super(context);
        this.acceptDriverEntity = acceptDriverEntity;
    }
}
