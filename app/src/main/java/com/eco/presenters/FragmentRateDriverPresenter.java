package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.entitys.DriverEntity;
import com.eco.entitys.ErrorEntity;
import com.eco.entitys.RequestEntity;
import com.eco.interfaces.IFragmentRateDriverPresenter;
import com.eco.interfaces.IFragmentRateDriverView;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;

public class FragmentRateDriverPresenter extends BasePresenter<IFragmentRateDriverView> implements IFragmentRateDriverPresenter {
    public FragmentRateDriverPresenter(IFragmentRateDriverView view, Context context, ProgressBar progressBars, View views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void detach() {
        detachView();
    }





    @Override
    public void getList(int id) {
        startProgress();
        MethodApi.getInstance().getRequstList(id, new IRemoteCallback<DriverEntity>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(DriverEntity result) {
                if (isViewAvailable()) mView.get().showList(result);
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    if (!answer)
                        mView.get().rGetList(id);
                    stopProgress();
                }
            }
        });
    }
}
