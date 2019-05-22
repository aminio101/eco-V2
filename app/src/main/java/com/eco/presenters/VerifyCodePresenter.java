package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.PV;
import com.eco.PrefManager;
import com.eco.entitys.ErrorEntity;
import com.eco.entitys.PhoneEntity;
import com.eco.entitys.SendUserEntity;
import com.eco.entitys.UserEntity;
import com.eco.entitys.VerifiCodeEntity;
import com.eco.entitys.VerifyCodeSuccessEntity;
import com.eco.interfaces.IVerifyCodePresenter;
import com.eco.interfaces.IVerifyCodeView;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;

public class VerifyCodePresenter extends BasePresenter<IVerifyCodeView> implements IVerifyCodePresenter {
    public VerifyCodePresenter(IVerifyCodeView view, Context context, ProgressBar progressBars, View views) {
        super(view, context, progressBars, views);
    }

    boolean newUser = false;

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
                        showMsg("ارسال کد موفقیت آمیز بود");
                        mView.get().onSuccessSendCode();
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

    @Override
    public void verifyCode(final VerifiCodeEntity verifiCodeentity) {
        if (isViewAvailable()) {
            startProgress();
            MethodApi.getInstance().verifyCode(verifiCodeentity, new IRemoteCallback<VerifyCodeSuccessEntity>() {
                @Override
                public void onResponse(Boolean answer) {

                }

                @Override
                public void onSuccess(VerifyCodeSuccessEntity result) {
                    PrefManager.getInstance().setToken(result.getToken());
                    if (result.getCheckUser().equals("0")) {
                        mView.get().goToRegisterActivity();
                        newUser = true;
                    } else {
                        newUser = false;
                        getUserData(verifiCodeentity.getUsername(), result.getToken());
                    }
                }

                @Override
                public void onFail(ErrorEntity errorObject) {
                    if (isViewAvailable()) {
                        stoptProgress();
                        showMsg(errorObject.getUiErrorMessage());
                    }
                }

                @Override
                public void onFinish(Boolean answer, boolean connection) {
                    if (isViewAvailable()) {
                        if (!newUser)
                            stoptProgress();
                        if (!connection)
                            mView.get().rVerifyCode();

                    }
                }
            });
        }
    }

    @Override
    public void getUserData(final String userName, final String token) {
        MethodApi.getInstance().getUser(new SendUserEntity(userName, token), new IRemoteCallback<UserEntity>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(UserEntity result) {
                PrefManager.getInstance().setUser(result);
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                showMsg(errorObject.getUiErrorMessage());
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    stoptProgress();
                    if (!connection)
                        mView.get().rGetUser(userName, token);
                    if (answer)
                        mView.get().goToMainActivity();
                }
            }
        });
    }
}
