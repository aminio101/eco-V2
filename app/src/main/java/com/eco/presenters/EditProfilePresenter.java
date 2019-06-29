package com.eco.presenters;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.eco.PrefManager;
import com.eco.entitys.ErrorEntity;
import com.eco.entitys.RequstUserUpdateEntity;
import com.eco.entitys.UserEntity;
import com.eco.interfaces.IEditProfilePresenter;
import com.eco.interfaces.IEditProfileView;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;

import java.util.ArrayList;

import static com.eco.MyApplication.getContext;

public class EditProfilePresenter extends BasePresenter<IEditProfileView> implements IEditProfilePresenter {

    public EditProfilePresenter(IEditProfileView view, Context context, ProgressBar progressBars, View views) {
        super(view, context, progressBars, views);
    }


    @Override
    public void update(RequstUserUpdateEntity requstUserUpdateEntity) {
        startProgress();
        MethodApi.getInstance().update(requstUserUpdateEntity, new IRemoteCallback<UserEntity>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(UserEntity result) {
                if (isViewAvailable()) {
                    mView.get().success(result);
                    stopProgress();
                    mView.get().callHome();
                    showMsg("ویرایش اطلاعات شما با موفقیت انجام شد");}
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                  if (!connection) mView.get().rupdate(requstUserUpdateEntity);
                    stopProgress();
                }
            }
        });
    }

    public void checkValue(EditText editTextAddress, EditText editTextName, EditText editTextFamily) {
        if (!TextUtils.isEmpty(editTextAddress.getText().toString()) &&
                !TextUtils.isEmpty(editTextName.getText()) &&
                !TextUtils.isEmpty(editTextFamily.getText()))
        {
            mView.get().checkedOk();
        }
        else {
            showMsg("فیلد های ضروری خالی است");
        }
    }
}
