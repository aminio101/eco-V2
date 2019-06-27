package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.interfaces.ILoginPresenter;
import com.eco.interfaces.ILoginView;
import com.eco.interfaces.IRegisterPresenter;
import com.eco.interfaces.IRegisterView;

public class RegisterPresenter extends BasePresenter<IRegisterView> implements IRegisterPresenter {

    RegisterPresenter(IRegisterView view, Context context, ProgressBar progressBars, View views) {
        super(view, context, progressBars, views);
    }
}
