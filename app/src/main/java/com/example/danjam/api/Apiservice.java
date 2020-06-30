package com.example.danjam.api;

import com.example.danjam.data.SleepUpdateInfo;
import com.example.danjam.data.UpdateInfo;
import com.example.danjam.data.Usermodel;
import com.example.danjam.data.SignUp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Apiservice {

    @FormUrlEncoded
    @POST("auth/signup")
    Call<SignUp> SignupPost(
            @Field("id") String id,
            @Field("password") String password,
            @Field("email") String email,
            @Field("username") String username
            );

    @FormUrlEncoded
    @POST("auth/signin")
    Call<Usermodel> SigninPost(
            @Field("id")String id,
            @Field("password")String password
    );

    @FormUrlEncoded
    @POST("auth/info")
    Call<ResponseBody>UserUpdate(
            @Header("Authorization")String token,
            @Field("type")String[] type
    );

    @PUT("auth/update_info")
    Call<UpdateInfo> ReviseUser(
            @Header("Authorization")String token,
            @Body UpdateInfo updateInfo
    );

    @PUT("sleep/update")
    Call<SleepUpdateInfo> SleepUpdate(
            @Path("day")String day
    );



}
