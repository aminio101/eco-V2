package com.eco.presenters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.PV;
import com.eco.activitys.VeryFyCodeActivity;
import com.eco.entitys.ErrorEntity;
import com.eco.entitys.PhoneEntity;
import com.eco.interfaces.ILoginPresenter;
import com.eco.interfaces.ILoginView;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter {
    Context context;

    public LoginPresenter(ILoginView view, Context context, ProgressBar progressBars, View views) {
        super(view, context, progressBars, views);
        this.context = context;
    }

    @Override
    public void sendCode(String phone) {
        if (PV.checkText(phone) && phone.length() >= 11) {
            if (isViewAvailable()) {
                startProgress();
                MethodApi.getInstance().sendCode(new PhoneEntity(phone), new IRemoteCallback<Void>() {
                    @Override
                    public void onResponse(Boolean answer) {

                    }

                    @Override
                    public void onSuccess(Void result) {
                        mView.get().
                        goToVeryFyCodeActivity();
                    }

                    @Override
                    public void onFail(ErrorEntity errorObject) {
                        if (isViewAvailable()) {
                            if (PV.checkTimeOutError(errorObject))
                                mView.get().rSendCode();
                            else
                                showMsg(errorObject.getUiErrorMessage());
                        }
                    }

                    @Override
                    public void onFinish(Boolean answer, boolean connection) {
                        if (isViewAvailable()) {
                            stoptProgress();
                            if (!connection)
                                mView.get().rSendCode();

                        }
                    }
                });
            }
        } else
            showMsg("لطفا شماره خود را صحیح وارد کنید");
    }

}

