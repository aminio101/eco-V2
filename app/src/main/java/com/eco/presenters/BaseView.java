package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.eco.interfaces.IBaseView;

import java.util.ArrayList;


public class BaseView implements IBaseView {
    ArrayList<ProgressBar> progressBars ;
    Context context;
    public BaseView (ArrayList<ProgressBar> progressBars,Context context){
        this.progressBars = progressBars;
        this.context = context;
    }

    @Override
    public void startProgress() {
        progressBars.get(0).setVisibility(View.GONE);
    }

    @Override
    public void stopProgress() {
        progressBars.get(0).setVisibility(View.VISIBLE);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
