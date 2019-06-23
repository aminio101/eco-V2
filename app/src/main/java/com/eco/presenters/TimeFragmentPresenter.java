package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.interfaces.ITimeFragmentPresenter;
import com.eco.interfaces.ITimeFragmentView;

import java.util.ArrayList;

public class TimeFragmentPresenter extends BasePresenter<ITimeFragmentView> implements ITimeFragmentPresenter {
    public TimeFragmentPresenter(ITimeFragmentView view, Context context,  ProgressBar progressBars,  View views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void getTimes() {

    }
}
