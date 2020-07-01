package com.example.danjam.api;

import com.example.danjam.data.CreatePost;
import com.example.danjam.data.Refresh;
import com.example.danjam.data.SleepUpdateInfo;
import com.example.danjam.data.TimeAddData;
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

    @FormUrlEncoded
    @POST("auth/refresh")
    Call<Refresh>RefreshToken(
            @Header("Authorization")String token
    );

    @FormUrlEncoded
    @POST("sleep/get")
    Call<ResponseBody>SleepGet(
            @Header("Authorization")String token,
            @Field("type") String[] type,
            @Field("date") String date
    );
//
//
//    @FormUrlEncoded
    @POST("achievements/get")
    Call<ResponseBody>AchievementGet(
            @Header("Authorization")String token
    );

    @FormUrlEncoded
    @POST("post/created")
    Call<CreatePost> CreatedPost(
            @Header("Authorization")String token,
            @Field("title")String title,
            @Field("text")String text
    );

    @POST("post/get")
    Call<ResponseBody>GetPost(
            @Header("Authorization")String token
    );

    @PUT("auth/update_info")
    Call<UpdateInfo> ReviseUser(
            @Header("Authorization")String token,
            @Body UpdateInfo updateInfo
    );

    @PUT("sleep/update")
    Call<SleepUpdateInfo> SleepUpdate(
            @Header("Authorization")String token,
            @Body SleepUpdateInfo sleepUpdateInfo
    );
    @FormUrlEncoded
    @POST("time/add")
    Call<TimeAddData> TimeAdd(
        @Field("time1")String time1,
        @Field("time2")String time2
    );
}
