package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.entitys.ErrorEntity;
import com.eco.entitys.RubbishEntity;
import com.eco.interfaces.IMainFragmentPresenter;
import com.eco.interfaces.IMainFragmentView;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;

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
        startProgress();
        MethodApi.getInstance().getRubbishList(new IRemoteCallback<ArrayList<RubbishEntity>>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(ArrayList<RubbishEntity> result) {
                if (isViewAvailable()) mView.get().showList(result);
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    if (!answer) mView.get().rGetList();
                    stopProgress();
                }
            }
        });
    }
}
