package com.eco.rest;

import com.eco.entitys.GifEntity;
import com.eco.entitys.PhoneEntity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;

public interface SignatureApi {
    @Headers({"Content-Type:application/json"})
    @GET("/api/users/randomAdvertise")
    Call<ArrayList<GifEntity>> getAdvertise();

    @Headers({"Content-Type:application/json"})
    @PUT("/api/otp/userSendOtp")
    Call<Void> sendCode(@Body PhoneEntity username);


}
