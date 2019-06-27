package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.entitys.ErrorEntity;
import com.eco.entitys.InviteEntity;
import com.eco.entitys.MobileEntitiy;
import com.eco.interfaces.IIntroducePresenter;
import com.eco.interfaces.IIntroduceView;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;

public class IntroducePresenter extends BasePresenter<IIntroduceView> implements IIntroducePresenter {
    public IntroducePresenter(IIntroduceView view, Context context, ProgressBar progressBars, View views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void detach() {

        detachView();
    }

    @Override
    public void invite(String phone) {
        if (phone.length() < 10) {
            showMsg("لطفا تلفن همراه خود را کامل وارد کنید ");
            return;
        }
        startProgress();
        MethodApi.getInstance().inviteFriend(phone, new IRemoteCallback<InviteEntity>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(InviteEntity result) {
                if (isViewAvailable()) {
                    mView.get().success();
                    showMsg("دعوت از دوست شما موفقیت آمیز بود");
                }
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    if (!connection) mView.get().rInvite(phone);
                    stopProgress();
                }
            }
        });
    }
}
