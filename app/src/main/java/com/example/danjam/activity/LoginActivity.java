package com.example.danjam.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.danjam.R;
import com.example.danjam.Splash;
import com.example.danjam.api.Apiservice;
import com.example.danjam.data.Usermodel;
import com.example.danjam.fragment.FragmentSet;

public class LoginActivity extends AppCompatActivity {

    final static String BASE_URL = "https://unitaemin.run.goorm.io/danzam/";
    private Retrofit retrofit;
    private Button Signup_bt;
    private Button Login_bt;
    private EditText Login_ID;
    private EditText Login_PW;
    private String Login_ID_str;
    private String Login_PW_str;
    private String accessToken,refresh_token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //splash 화면 불러오기
//        Intent intent = new Intent(getApplicationContext(), Splash.class);
//        startActivity(intent);


        //레트로핏 선언부
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Login_ID = findViewById(R.id.login_id_et);
        Login_PW = findViewById(R.id.login_pw_et);
        Signup_bt = findViewById(R.id.signup_bt);
        Login_bt = findViewById(R.id.page_login_bt);


        SharedPreferences token_sf = getSharedPreferences("token",MODE_PRIVATE);

        accessToken = token_sf.getString("access_token","asd");
        refresh_token = token_sf.getString("refresh_token","asd");


        if (accessToken!="asd"){
            Log.e("token",token_sf.getString("access_token",""));
            Intent main_intent_activity = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(main_intent_activity);
        }



        //값보내기
        Login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Login_ID_str = Login_ID.getText().toString();
                Login_PW_str =Login_PW.getText().toString();

                SigninPost(Login_ID_str,Login_PW_str);
            }
        });


        //회원가입으로 이동
        Signup_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign_up_intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(sign_up_intent);
            }
        });


    }

    //값보내기 retrofit
    private void SigninPost(String st1,String st2){

        Apiservice apiservice = retrofit.create(Apiservice.class);
        Call<Usermodel> call =apiservice.SigninPost(st1,st2);

        call.enqueue(new Callback<Usermodel>() {
            @Override
            public void onResponse(Call<Usermodel> call, Response<Usermodel> response) {

                if (response.isSuccessful()) {

                    Usermodel usermodel = response.body();
                    Log.e("access_token",usermodel.getAccessToken());
                    Log.e("refresh_token",usermodel.getRefreshToken());
                    accessToken = usermodel.getAccessToken();
                    refresh_token = usermodel.getRefreshToken();

                    SharedPreferences accessTokenSharedPreference = getSharedPreferences("token",MODE_PRIVATE);
                    SharedPreferences.Editor editor = accessTokenSharedPreference.edit();
                    editor.putString("access_token",accessToken);
                    editor.putString("refresh_token",refresh_token);
                    editor.commit();


                    Intent main_intent= new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(main_intent);

                }else {
                    Toast.makeText(LoginActivity.this, "아이디 비밀번호를 다시 확인해 주세요", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Usermodel> call, Throwable t) {

            }


        });

    }
}
