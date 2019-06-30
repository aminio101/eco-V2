package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.PV;
import com.eco.PrefManager;
import com.eco.entitys.ErrorEntity;
import com.eco.entitys.MUserEntity;
import com.eco.entitys.UserEntity;
import com.eco.interfaces.IWalletFragmentView;
import com.eco.interfaces.IWalletPresenter;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;

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
                if (isViewAvailable()) {
                    showMsg("امتیاز شما با موفقیت واریز شد");
                    getUser();
                }
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
                }
            }
        });
    }

    @Override
    public void getUser() {
        MethodApi.getInstance().getUser(new IRemoteCallback<MUserEntity>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(MUserEntity result) {
                if (isViewAvailable()) {
                    UserEntity userEntity;
                    userEntity = PrefManager.getInstance().getUser();
                    if (userEntity == null)
                        userEntity = new UserEntity();
                    userEntity.roleId = result.roleId;
                    userEntity.address = result.address;
                    userEntity.cityId = result.cityId;
                    userEntity.email = result.email;
                    userEntity.family = result.family;
                    userEntity.familyNumber = String.valueOf(result.familyCount);
                    userEntity.gender = String.valueOf(result.gender);
                    userEntity.grade = result.grade;
                    userEntity.id = result.id;
                    userEntity.mobileNumber = String.valueOf(result.userName);
                    userEntity.name = result.name;
                    userEntity.provinceId = result.provinceId;
                    userEntity.score = result.citizenScore;
                    userEntity.shabaNumber = result.shabaNumber;
                    userEntity.username = result.userName;
                    PrefManager.getInstance().setUser(userEntity);
                }
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable())
                    showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    if (!answer)
                        mView.get().rGetUser();
                    stopProgress();
                }
            }
        });
    }


    @Override
    public void checkValue(String grade, String shaba) {
        if (!PV.checkText(shaba)) {
            showMsg("شماره شبا را وارد کنید.");
        } else if (!PV.checkText(grade)||grade.equals("0")) {
            showMsg("امتیاز شما کافی نمی باشد.");
        } else {
            mView.get().checkOk();
        }

    }
}
