package com.eco.presenters;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.eco.PrefManager;
import com.eco.entitys.ErrorEntity;
import com.eco.entitys.RequstUserUpdateEntity;
import com.eco.entitys.UserEntity;
import com.eco.interfaces.IEditProfilePresenter;
import com.eco.interfaces.IEditProfileView;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;

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
                    UserEntity userEntity = PrefManager.getInstance().getUser();
                    userEntity.provinceId = result.provinceId;
                    userEntity.cityId = result.cityId;
                    userEntity.countryId = result.countryId;
                    userEntity.name = result.name;
                    userEntity.family = result.family;
                    userEntity.email = result.email;
                    userEntity.address = result.address;
                    userEntity.grade = result.grade;
                    userEntity.shabaNumber = result.shabaNumber;
                    userEntity.gender = result.gender;
                    userEntity.familyNumber = result.familyNumber;
                    userEntity.id = result.id;
                    userEntity.mobileNumber = result.mobileNumber;
                    PrefManager.getInstance().setUser(userEntity);
                    showMsg("ویرایش اطلاعات شما با موفقیت انجام شد");

                }
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    if (!connection) mView.get().rupUpdate(requstUserUpdateEntity);
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
