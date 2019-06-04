package com.eco.rest;

import com.eco.entitys.GifEntity;
import com.eco.entitys.PhoneEntity;
import com.eco.entitys.SendUserEntity;
import com.eco.entitys.StoreCategoryListEntity;
import com.eco.entitys.UserEntity;
import com.eco.entitys.VerifiCodeEntity;
import com.eco.entitys.VerifyCodeSuccessEntity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface SignatureApi {
    @Headers({"Content-Type:application/json"})
    @GET("/api/users/randomAdvertise")
    Call<ArrayList<GifEntity>> getAdvertise();

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

}
