package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.interfaces.IFinalFragmentPresenter;
import com.eco.interfaces.IFinalFragmentView;

import java.util.ArrayList;

public class FinalFragmentPresenter extends BasePresenter<IFinalFragmentView> implements IFinalFragmentPresenter {
    public FinalFragmentPresenter(IFinalFragmentView view, Context context, ProgressBar progressBars, View views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void detach() {
        detachView();
    }

    @Override
    public void discharge() {

    }
}
