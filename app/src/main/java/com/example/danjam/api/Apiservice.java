package com.example.danjam.api;

import com.example.danjam.data.SleepUpdateInfo;
import com.example.danjam.data.UpdateInfo;
import com.example.danjam.data.UserRevise;
import com.example.danjam.data.Usermodel;
import com.example.danjam.data.signup;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Apiservice {

    @FormUrlEncoded
    @POST("auth/signup")
    Call<signup> SignupPost(
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
