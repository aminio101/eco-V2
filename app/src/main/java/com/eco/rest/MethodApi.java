package com.eco.rest;

import android.app.Application;

import com.eco.PV;
import com.eco.PrefManager;
import com.eco.entitys.AdvertisingEntity;
import com.eco.entitys.ErrorEntity;
import com.eco.entitys.FavoriteAddressEntity;
import com.eco.entitys.InviteEntity;
import com.eco.entitys.LocationEntity;
import com.eco.entitys.MobileEntitiy;
import com.eco.entitys.PhoneEntity;
import com.eco.entitys.ProductListEntity;
import com.eco.entitys.RequestEntity;
import com.eco.entitys.RequestGetDayListEntity;
import com.eco.entitys.RunDatePeriodsEntity;
import com.eco.entitys.ScoreToMoneyEntity;
import com.eco.entitys.SendUserEntity;
import com.eco.entitys.StoreCategoryListEntity;
import com.eco.entitys.TimeStampEntity;
import com.eco.entitys.UserEntity;
import com.eco.entitys.UserNumberEntity;
import com.eco.entitys.VerifiCodeEntity;
import com.eco.entitys.VerifyCodeSuccessEntity;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PUT;

public class MethodApi {

    private static MethodApi instance;
    private static SignatureApi signatureApi;

    public static void init(Application application) {
        instance = new MethodApi();
        signatureApi = Api.getClient().create(SignatureApi.class);
    }

    public void inviteFriend(String phone, final IRemoteCallback<InviteEntity> callback) {
        MobileEntitiy mobileEntitiy = new MobileEntitiy(phone);
        final Call<InviteEntity> call = signatureApi.inviteFriend(PV.tokenPrefix+PrefManager.getInstance().getToken(), mobileEntitiy);
        call.enqueue(new Enqueue<>(new IRemoteCallback<InviteEntity>() {
            @Override
            public void onResponse(Boolean answer) {
                callback.onResponse(answer);
            }

            @Override
            public void onSuccess(InviteEntity result) {
                callback.onSuccess(result);
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                callback.onFail(errorObject);
            }

            @Override
            public void onFinish(Boolean answer,boolean connect) {
                callback.onFinish(answer,connect);
            }
        }));
    }


    public void scoreToMony(int score, final IRemoteCallback<String> callback) {
        ScoreToMoneyEntity scoreToMony = new ScoreToMoneyEntity(score);
        final Call<String> call = signatureApi.scoreToMoney(PV.tokenPrefix+ PrefManager.getInstance().getToken(),scoreToMony);
        call.enqueue(new Enqueue<String>(new IRemoteCallback<String>() {
            @Override
            public void onResponse(Boolean answer) {
                callback.onResponse(answer);
            }

            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);

            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                callback.onFail(errorObject);
            }

            @Override
            public void onFinish(Boolean answer,boolean connect) {
                callback.onFinish(answer,connect);
            }
        }));
    }

    public static MethodApi getInstance() {
        return instance;
    }
    public void getStoreItems(String id, final IRemoteCallback<ProductListEntity> callback) {

        final Call<ProductListEntity> call = signatureApi.getStoreItems(id, PV.tokenPrefix+PrefManager.getInstance().getToken());
        call.enqueue(new Enqueue<>(new IRemoteCallback<ProductListEntity>() {
            @Override
            public void onResponse(Boolean answer) {
                callback.onResponse(answer);
            }

            @Override
            public void onSuccess(ProductListEntity result) {
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

    public void getFavoriteLocation(final IRemoteCallback<ArrayList<FavoriteAddressEntity>> callback) {
        final Call<ArrayList<FavoriteAddressEntity>> call = signatureApi.getFavoriteLocations(PV.tokenPrefix+PrefManager.getInstance().getToken());
        call.enqueue(new Enqueue<>(new IRemoteCallback<ArrayList<FavoriteAddressEntity>>() {
            @Override
            public void onResponse(Boolean answer) {
                callback.onResponse(answer);
            }

            @Override
            public void onSuccess(ArrayList<FavoriteAddressEntity> result) {
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

    public void getNow(final IRemoteCallback<ArrayList<TimeStampEntity>> callback) {
        final Call<ArrayList<TimeStampEntity>> call = signatureApi.getNow(PV.tokenPrefix+ PrefManager.getInstance().getToken());
        call.enqueue(new Enqueue<>(new IRemoteCallback<ArrayList<TimeStampEntity>>() {
            @Override
            public void onResponse(Boolean answer) {
                callback.onResponse(answer);
            }

            @Override
            public void onSuccess(ArrayList<TimeStampEntity> result) {
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


    public void changeFavoriteLocation(int id, FavoriteAddressEntity addFavorite, final IRemoteCallback<FavoriteAddressEntity> callback) {
        String url = "api/users/favoriteLocation/" + id;
        final Call<FavoriteAddressEntity> call = signatureApi.changeAddress(url, PV.tokenPrefix + PrefManager.getInstance().getToken(), addFavorite);
        call.enqueue(new Enqueue<>(new IRemoteCallback<FavoriteAddressEntity>() {
            @Override
            public void onResponse(Boolean answer) {
                callback.onResponse(answer);
            }

            @Override
            public void onSuccess(FavoriteAddressEntity result) {
                callback.onSuccess(result);
            }

            @Override
            public void onFail(ErrorEntity errorEntity) {
                callback.onFail(errorEntity);
            }

            @Override
            public void onFinish(Boolean answer,boolean connection) {
                callback.onFinish(answer,connection);
            }
        }));
    }
    public void addFavoriteLocation(FavoriteAddressEntity addFavorite, final IRemoteCallback<FavoriteAddressEntity> callback) {

        final Call<FavoriteAddressEntity> call = signatureApi.addAddress(PV.tokenPrefix + PrefManager.getInstance().getToken(), addFavorite);
        call.enqueue(new Enqueue<>(new IRemoteCallback<FavoriteAddressEntity>() {
            @Override
            public void onResponse(Boolean answer) {
                callback.onResponse(answer);
            }

            @Override
            public void onSuccess(FavoriteAddressEntity result) {
                callback.onSuccess(result);
            }

            @Override
            public void onFail(ErrorEntity errorEntity) {
                callback.onFail(errorEntity);
            }

            @Override
            public void onFinish(Boolean answer,boolean connection) {
                callback.onFinish(answer,connection);
            }
        }));
    }


    public void getTimes(LocationEntity locationEntity,final IRemoteCallback<ArrayList<RunDatePeriodsEntity>> callback){
        RequestGetDayListEntity requestGetDayListEntity = new RequestGetDayListEntity();
        requestGetDayListEntity.location.setLng(locationEntity.getLng());
        requestGetDayListEntity.location.setLat(locationEntity.getLat());
        Call <ArrayList<RunDatePeriodsEntity>> call =signatureApi.getRunDatePeriods(PV.tokenPrefix+PrefManager.getInstance().getToken(),requestGetDayListEntity);
        call.enqueue(new Enqueue<>(new IRemoteCallback<ArrayList<RunDatePeriodsEntity>>() {
            @Override
            public void onResponse(Boolean answer) {
                callback.onResponse(answer);
            }

            @Override
            public void onSuccess(ArrayList<RunDatePeriodsEntity> result) {
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
    public void getRequestNumber(LocationEntity location, final IRemoteCallback<ArrayList<UserNumberEntity>> callback) {
        final Call<ArrayList<UserNumberEntity>> call = signatureApi.getUserNumber(PV.tokenPrefix+ PrefManager.getInstance().getToken(),location);
        call.enqueue(new Enqueue<>(new IRemoteCallback<ArrayList<UserNumberEntity>>() {
            @Override
            public void onResponse(Boolean answer) {
                callback.onResponse(answer);
            }

            @Override
            public void onSuccess(ArrayList<UserNumberEntity> result) {
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

    public void addRequest(RequestEntity request, final IRemoteCallback<JsonObject> callback) {

        final Call<JsonObject> call = signatureApi.addRequest(PV.tokenPrefix + PrefManager.getInstance().getToken(), request);
        call.enqueue(new Enqueue<>(new IRemoteCallback<JsonObject>() {
            @Override
            public void onResponse(Boolean answer) {
                callback.onResponse(answer);
            }

            @Override
            public void onSuccess(JsonObject result) {
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

    public void getAdvertising(final IRemoteCallback<ArrayList<AdvertisingEntity>> callback) {
        final Call<ArrayList<AdvertisingEntity>> call = signatureApi.getAdvertise(PV.tokenPrefix+PrefManager.getInstance().getToken());
        call.enqueue(new Enqueue<>(new IRemoteCallback<ArrayList<AdvertisingEntity>>() {
            @Override
            public void onResponse(Boolean answer) {
                callback.onResponse(answer);
            }

            @Override
            public void onSuccess(ArrayList<AdvertisingEntity> result) {
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

    public void addRequestSenf(RequestEntity request, final IRemoteCallback<JsonObject> callback) {
        final Call<JsonObject> call = signatureApi.addRequestSenf(PV.tokenPrefix + PrefManager.getInstance().getToken(), request);
        call.enqueue(new Enqueue<>(new IRemoteCallback<JsonObject>() {
            @Override
            public void onResponse(Boolean answer) {
                callback.onResponse(answer);
            }

            @Override
            public void onSuccess(JsonObject result) {
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
