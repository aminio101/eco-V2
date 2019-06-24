package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.interfaces.IMainFragmentPresenter;
import com.eco.interfaces.IMainFragmentView;

import java.util.ArrayList;

public class MainFragmentPresenter extends BasePresenter<IMainFragmentView> implements IMainFragmentPresenter {
    public MainFragmentPresenter(IMainFragmentView view, Context context, ProgressBar progressBars,  View views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void detach() {
        detachView();
    }

    @Override
    public void getList() {

    }
}
