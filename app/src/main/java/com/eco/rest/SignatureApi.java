package com.eco.rest;

import com.eco.entitys.FavoriteAddressEntity;
import com.eco.entitys.AdvertisingEntity;
import com.eco.entitys.LocationEntity;
import com.eco.entitys.PhoneEntity;
import com.eco.entitys.ProductListEntity;
import com.eco.entitys.RequestGetDayListEntity;
import com.eco.entitys.RequestEntity;
import com.eco.entitys.RunDatePeriodsEntity;
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
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SignatureApi {
    @Headers({"Content-Type:application/json"})
    @GET("/api/users/randomAdvertise")
    Call<ArrayList<AdvertisingEntity>> getAdvertise(@Header("Authorization") String auth);



    @Headers({"Content-Type:application/json"})
    @POST("/api/users/request")
    Call<JsonObject> addRequest(@Header("Authorization") String auth,
                                @Body RequestEntity requestModel);


    @Headers({"Content-Type:application/json"})
    @POST("/api/guilds/request")
    Call<JsonObject> addRequestSenf(@Header("Authorization") String auth,
                                    @Body RequestEntity requestModel);

    @Headers({"Content-Type:application/json"})
    @GET("api/store/categories/{id}?pageSize=100&pageNumber=1")
    Call<ProductListEntity> getStoreItems(@Path("id") String id, @Header("Authorization") String auth);

    @Headers({"Content-Type:application/json"})
    @PUT("/api/otp/userSendOtp")
    Call<Void> sendCode(@Body PhoneEntity username);

    @Headers({"Content-Type:application/json"})
    @PUT("/api/otp/userVerifyOtp")
    Call<VerifyCodeSuccessEntity> verifyCode(@Body VerifiCodeEntity verifiCodeentity);
    @Headers({"Content-Type:application/json"})
    @POST("/api/auth/user")
    Call<UserEntity> getUser(@Body SendUserEntity auth);

    @Headers({"Content-Type:application/json"})
    @GET("/api/store/categories?pageSize=100&pageNumber=1")
    Call<StoreCategoryListEntity> getStoreCategories(@Header("Authorization") String auth);


    @Headers({"Content-Type:application/json"})
    @GET("/api/shared/timestamp")
    Call<ArrayList<TimeStampEntity>> getNow(@Header("Authorization") String auth);


    @Headers({"Content-Type:application/json"})
    @GET("/api/users/favoriteLocation")
    Call<ArrayList<FavoriteAddressEntity>> getFavoriteLocations(@Header("Authorization") String auth);


    @Headers({"Content-Type:application/json"})
    @PUT("/api/shared/runDatePeriods")
    Call<ArrayList<RunDatePeriodsEntity>> getRunDatePeriods(@Header("Authorization") String auth,@Body RequestGetDayListEntity username);




    @Headers({"Content-Type:application/json"})
    @PUT("/api/shared/neighborRequest")
    Call<ArrayList<UserNumberEntity>> getUserNumber(@Header("Authorization") String auth,@Body LocationEntity username);

}
