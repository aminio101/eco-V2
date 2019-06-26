package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.entitys.ErrorEntity;
import com.eco.interfaces.IWalletFragmentView;
import com.eco.interfaces.IWalletPresenter;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;

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
    public void pay(int score) {
        startProgress();
        MethodApi.getInstance().scoreToMony(score, new IRemoteCallback<String>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(String result) {
                if (isViewAvailable())
                    showMsg("عملیات با موفقیت انجام شد");
                    mView.get().success();
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    if (!answer)
                        mView.get().rPay(score);
                    stopProgress();
                }
            }
        });
    }
}
