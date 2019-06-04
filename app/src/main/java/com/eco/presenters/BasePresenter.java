package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.eco.entitys.ErrorEntity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class BasePresenter<T> {
    Context context;
    ArrayList<ProgressBar> progressBars;
    ArrayList<View> views;
    protected WeakReference<T> mView;

    public BasePresenter(T view, Context context, ArrayList<ProgressBar> progressBars, ArrayList<View> views) {
        this.progressBars = progressBars;
        this.views = views;
        this.context = context;
        this.mView = new WeakReference(view);
    }

    public BasePresenter(T view, Context context, ProgressBar progressBars, ArrayList<View> views) {
        this.progressBars = new ArrayList<>();
        this.progressBars.add(progressBars);
        this.views = views;
        this.context = context;
        this.mView = new WeakReference(view);
    }

    public BasePresenter(T view, Context context, ProgressBar progressBars, View views) {
        this.progressBars = new ArrayList<>();
        this.progressBars.add(progressBars);
        this.views = new ArrayList<>();
        this.views.add(views);
        this.context = context;
        this.mView = new WeakReference(view);
        if (progressBars == null) {
            this.progressBars = null;
        }
        if (views==null){
            this.views = null;
        }
    }


    protected boolean isViewAvailable() {
        return this.mView != null && this.mView.get() != null;
    }

    public void detachView() {
        this.mView.clear();
    }


    public void showMsg(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
    public void showMsg(ErrorEntity msg) {
        Toast.makeText(context, msg.getUiErrorMessage(), Toast.LENGTH_LONG).show();
    }

    public void startProgress() {
        if (progressBars != null) {
            for (int i = 0; i < progressBars.size(); i++)
                progressBars.get(i).setVisibility(View.VISIBLE);
        }
        if (views != null) {
            for (int i = 0; i < views.size(); i++)
                views.get(i).setVisibility(View.INVISIBLE);
        }
    }

    public void stoptProgress() {
        if (progressBars != null) {
            for (int i = 0; i < progressBars.size(); i++)
                progressBars.get(i).setVisibility(View.INVISIBLE);
        }
        if (views != null) {
            for (int i = 0; i < views.size(); i++)
                views.get(i).setVisibility(View.VISIBLE);
        }
    }


}
