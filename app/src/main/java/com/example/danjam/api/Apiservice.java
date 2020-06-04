package com.example.danjam.api;

import com.example.danjam.data.signin;
import com.example.danjam.data.signup;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Apiservice {

    @FormUrlEncoded
    @POST("/auth/signup")
    Call<signup> SignupPost(
            @Field("id") String id,
            @Field("password") String password,
            @Field("email") String email,
            @Field("username") String username
            );
    @FormUrlEncoded
    @POST("auth/signin")
    Call<signin> SigninPost(
            @Field("id")String id,
            @Field("password")String password

    );
}
