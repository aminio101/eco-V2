package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.PrefManager;
import com.eco.entitys.ErrorEntity;
import com.eco.interfaces.IMoreFragmentPresenter;
import com.eco.interfaces.IMoreFragmentView;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;

public class MoreFragmentPresenter extends BasePresenter<IMoreFragmentView> implements IMoreFragmentPresenter {

    public MoreFragmentPresenter(IMoreFragmentView view, Context context, ProgressBar progressBars, View views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void detach() {
        detachView();
    }

    @Override
    public void exit() {
        startProgress();
        MethodApi.getInstance().logOut(new IRemoteCallback<String>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(String result) {
                if (isViewAvailable()) {
                    PrefManager.getInstance().setToken(null);
                    PrefManager.getInstance().setFCM(0);
                    mView.get().successExit();
                }
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    if (!answer) mView.get().rExit();
                    stopProgress();
                }
            }
        });
    }
}
