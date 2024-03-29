package com.eco.rest;

import com.eco.entitys.CommentEntity;
import com.eco.entitys.DriverEntity;
import com.eco.entitys.FCMEntity;
import com.eco.entitys.FavoriteAddressEntity;
import com.eco.entitys.AdvertisingEntity;
import com.eco.entitys.InviteEntity;
import com.eco.entitys.ListeEntity;
import com.eco.entitys.LocationEntity;
import com.eco.entitys.MUserEntity;
import com.eco.entitys.MobileEntitiy;
import com.eco.entitys.PhoneEntity;
import com.eco.entitys.ProductListEntity;
import com.eco.entitys.RequestGetDayListEntity;
import com.eco.entitys.RequestEntity;
import com.eco.entitys.RubbishEntity;
import com.eco.entitys.RunDatePeriodsEntity;
import com.eco.entitys.ScoreToMoneyEntity;
import com.eco.entitys.SendUserEntity;
import com.eco.entitys.SignupAnswerEntity;
import com.eco.entitys.StoreCategoryListEntity;
import com.eco.entitys.TimeStampEntity;
import com.eco.entitys.UserEntity;
import com.eco.entitys.UserNumberEntity;
import com.eco.entitys.VerifiCodeEntity;
import com.eco.entitys.VerifyCodeSuccessEntity;
import com.eco.entitys.XChangeEntity;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface SignatureApi {
    @Headers({"Content-Type:application/json"})
    @GET("/api/users/randomAdvertise")
    Call<ArrayList<AdvertisingEntity>> getAdvertise(@Header("Authorization") String auth);


    @GET("/api/shared/rubbishPrice")
    Call<ArrayList<RubbishEntity>> getPriceList(@Header("Authorization") String auth);


    @Headers({"Content-Type:application/json"})
    @GET
    Call<ListeEntity<XChangeEntity>> getXchangeList(@Header("Authorization") String auth,@Url String url);

    @Headers({"Content-Type:application/json"})
    @HTTP(method = "DELETE", hasBody = true)
    Call<String> deleteBuy(@Header("Authorization") String auth,@Url String url);


    @Headers({"Content-Type:application/json"})
    @HTTP(method = "DELETE", hasBody = true)
    Call<String> deleteRequest(@Header("Authorization") String auth,@Url String url);


    @Headers({"Content-Type:application/json"})
    @PUT("/api/fcm")
    Call<String> sendToken(@Header("Authorization") String auth,@Body FCMEntity username);


    @Headers({"Content-Type:application/json"})
    @PUT
    Call<CommentEntity> sendComment(@Header("Authorization") String auth,@Url String url, @Body CommentEntity username);


    @Headers({"Content-Type:application/json"})
    @HTTP(method = "DELETE", hasBody = true)
    Call<String> logOut(@Header("Authorization") String auth,@Url String url);


    @Headers({"Content-Type:application/json"})
    @GET
    Call<ArrayList<DriverEntity>> getRequstList(@Header("Authorization") String auth,@Url String url);


    @Headers({"Content-Type:application/json"})
    @GET("/api/shared/userDetail")
    Call<ArrayList<MUserEntity>> getUser(@Header("Authorization") String auth);



    @Headers({"Content-Type:application/json"})
    @PUT("api/users/inviteFriend")
    Call<InviteEntity> inviteFriend(@Header("Authorization") String auth, @Body MobileEntitiy user);

    @Headers({"Content-Type:application/json"})
    @PUT("/api/shared/scoreToMoney")
    Call<String> scoreToMoney(@Header("Authorization") String auth,@Body ScoreToMoneyEntity scoreToMoney);




    @Headers({"Content-Type:application/json"})
    @POST("/api/users/favoriteLocation")
    Call<FavoriteAddressEntity> addAddress(@Header("Authorization") String auth, @Body FavoriteAddressEntity favorite);



    @Headers({"Content-Type:application/json"})
    @PUT
    Call<FavoriteAddressEntity> changeAddress(@Url String url, @Header("Authorization") String auth, @Body FavoriteAddressEntity favorite);


    @Headers({"Content-Type:application/json"})
    @POST("/api/users/request")
    Call<JsonObject> addRequest(@Header("Authorization") String auth,
                                @Body RequestEntity requestModel);


    @Headers({"Content-Type:application/json"})
    @POST("/api/guilds/request")
    Call<JsonObject> addFastRequest(@Header("Authorization") String auth,
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
    Call<UserEntity> getUserFirst(@Body SendUserEntity auth);

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
    @GET("/api/shared/rubbish")
    Call<ArrayList<RubbishEntity>> getRubbishList(@Header("Authorization") String auth);




    @Headers({"Content-Type:application/json"})
    @PUT("/api/shared/neighborRequest")
    Call<ArrayList<UserNumberEntity>> getUserNumber(@Header("Authorization") String auth,@Body LocationEntity username);

    @Headers({"Content-Type:application/json"})
    @PUT("/api/shared/users")
    Call<UserEntity> update(@Header("Authorization") String authorization, @Body RequestBody body);

    @Headers({"Content-Type:application/json"})
    @POST("/api/users/userSignUp")
    Call<SignupAnswerEntity> signUp(@Body UserEntity user);

}
