package com.eco.rest;

import android.app.Application;

import com.eco.PV;
import com.eco.PrefManager;
import com.eco.entitys.ErrorEntity;
import com.eco.entitys.GifEntity;
import com.eco.entitys.PhoneEntity;
import com.eco.entitys.SendUserEntity;
import com.eco.entitys.StoreCategoryListEntity;
import com.eco.entitys.UserEntity;
import com.eco.entitys.VerifiCodeEntity;
import com.eco.entitys.VerifyCodeSuccessEntity;

import java.util.ArrayList;

import retrofit2.Call;

public class MethodApi {

    private static MethodApi instance;
    private static SignatureApi signatureApi;

    public static void init(Application application) {
        instance = new MethodApi();
        signatureApi = Api.getClient().create(SignatureApi.class);
    }


    public static MethodApi getInstance() {
        return instance;
    }
    public void getStoreCategories(final IRemoteCallback<StoreCategoryListEntity> callback) {

        final Call<StoreCategoryListEntity> call = signatureApi.getStoreCategories(PV.tokenPrefix+ PrefManager.getInstance().getToken());
        call.enqueue(new Enqueue<>(new IRemoteCallback<StoreCategoryListEntity>() {
            @Override
            public void onResponse(Boolean answer) {
                callback.onResponse(answer);
            }

            @Override
            public void onSuccess(StoreCategoryListEntity result) {
                callback.onSuccess(result);
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                callback.onFail(errorObject);
            }

            @Override
            public void onFinish(Boolean answer,boolean connection) {
                callback.onFinish(answer,connection);
            }
        }));
    }
    public void sendCode(PhoneEntity username, final IRemoteCallback<Void> callback) {

        final Call<Void> call = signatureApi.sendCode(username);
        call.enqueue(new Enqueue<>(new IRemoteCallback<Void>() {
            @Override
            public void onResponse(Boolean answer) {
                callback.onResponse(answer);
            }

            @Override
            public void onSuccess(Void result) {
                callback.onSuccess(null);
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                callback.onFail(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                callback.onFinish(answer, connection);
            }
        }));
    }

    public void getGif(final IRemoteCallback<ArrayList<GifEntity>> callback) {
        final Call<ArrayList<GifEntity>> call = signatureApi.getAdvertise();
        call.enqueue(new Enqueue<>(new IRemoteCallback<ArrayList<GifEntity>>() {
            @Override
            public void onResponse(Boolean answer) {
                callback.onResponse(answer);
            }

            @Override
            public void onSuccess(ArrayList<GifEntity> result) {
                callback.onSuccess(result);
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                callback.onFail(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                callback.onFinish(answer, connection);
            }
        }));
    }

    public void verifyCode(VerifiCodeEntity otp, final IRemoteCallback<VerifyCodeSuccessEntity> callback) {

        final Call<VerifyCodeSuccessEntity> call = signatureApi.verifyCode(otp);
        call.enqueue(new Enqueue<>(new IRemoteCallback<VerifyCodeSuccessEntity>() {
            @Override
            public void onResponse(Boolean answer) {
                callback.onResponse(answer);
            }

            @Override
            public void onSuccess(VerifyCodeSuccessEntity result) {
                callback.onSuccess(result);
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                callback.onFail(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connect) {
                callback.onFinish(answer, connect);
            }
        }));
    }
    public void getUser(SendUserEntity sendUserEntity, final IRemoteCallback<UserEntity> callback) {
        final Call<UserEntity> call = signatureApi.getUser(sendUserEntity);
        call.enqueue(new Enqueue<>(new IRemoteCallback<UserEntity>() {
            @Override
            public void onResponse(Boolean answer) {
                callback.onResponse(answer);
            }

            @Override
            public void onSuccess(UserEntity result) {
                callback.onSuccess(result);
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                callback.onFail(errorObject);
            }

            @Override
            public void onFinish(Boolean answer,boolean connection) {
                callback.onFinish(answer,connection);
            }
        }));
    }

}
