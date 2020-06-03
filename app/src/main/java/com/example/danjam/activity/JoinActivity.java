package com.example.danjam.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.danjam.R;
import com.example.danjam.api.Apiservice;
import com.example.danjam.data.signup;

public class JoinActivity extends AppCompatActivity {
    private EditText ID,PW,Email,Username;
    private Button joinBT;
    private Retrofit retrofit;
    private String id,pw,email,username;
    final static String BASE_URL = "https://unitaemin.run.goorm.io/danzam/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        ID = findViewById(R.id.join_id);
        PW = findViewById(R.id.join_pw);
        Email = findViewById(R.id.join_email);
        Username = findViewById(R.id.join_name);
        joinBT = findViewById(R.id.join_bt);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        joinBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = ID.getText().toString();
                pw = PW.getText().toString();
                email = Email.getText().toString();
                username = Username.getText().toString();

                createPost(id,pw,email,username);


            }
        });
    }
    private void createPost(String st1,String st2,String st3,String st4){

        Apiservice apiservice = retrofit.create(Apiservice.class);
        Call<signup> call =apiservice.SignupPost(st1,st2,st3,st4);

        call.enqueue(new Callback<signup>() {
            @Override
            public void onResponse(Call<signup> call, Response<signup> response) {
                if (!response.isSuccessful()) {
                    Log.e("hi","code: " + response.code());
                    return;
                }
            }

            @Override
            public void onFailure(Call<signup> call, Throwable t) {

            }
        });

    }
}
