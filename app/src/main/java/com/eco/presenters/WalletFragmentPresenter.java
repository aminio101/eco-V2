package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.interfaces.IWalletFragmentView;
import com.eco.interfaces.IWalletPresenter;

import java.util.ArrayList;

public class WalletFragmentPresenter extends BasePresenter<IWalletFragmentView> implements IWalletPresenter {
    public WalletFragmentPresenter(IWalletFragmentView view, Context context, ProgressBar progressBars, View views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void detach() {
        detachView();
    }

    @Override
    public void pay() {

    }
}
