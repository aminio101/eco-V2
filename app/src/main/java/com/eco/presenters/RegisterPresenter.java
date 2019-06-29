package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.entitys.ErrorEntity;
import com.eco.entitys.SignupAnswerEntity;
import com.eco.entitys.UserEntity;
import com.eco.interfaces.IRegisterPresenter;
import com.eco.interfaces.IRegisterView;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;

public class RegisterPresenter extends BasePresenter<IRegisterView> implements IRegisterPresenter {

    public RegisterPresenter(IRegisterView view, Context context, ProgressBar progressBars, View views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void checkedChange(boolean isChecked) {
        if(isChecked){
            mView.get().isChecked();
        }
        else{
            mView.get().notChecked();

        }

    }

    @Override
    public void register(UserEntity user) {
        startProgress();
        MethodApi.getInstance().signUp(user, new IRemoteCallback<SignupAnswerEntity>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(SignupAnswerEntity result) {
                mView.get().success(result);
                stopProgress();
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) {
                    showMsg(errorObject);
                    stopProgress();
                }
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    if (!connection) mView.get().rRefister(user);
                    stopProgress();
                }
            }
        });

    }
}
