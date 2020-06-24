package com.example.danjam.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.danjam.R;
import com.example.danjam.api.Apiservice;
import com.example.danjam.data.signup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


        Username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();
                if (!UsernameRegex(s)){
                    joinBT.setEnabled(false);
                    Toast.makeText(JoinActivity.this, "유저 이름은 2~20자리 안에 해주세요", Toast.LENGTH_SHORT).show();
                }
                else{
                    joinBT.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        ID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();
                if (!IDRegex(s)){
                    joinBT.setEnabled(false);
                    Toast.makeText(JoinActivity.this, "아이디는 첫 단어는 영어로, 영어와 숫자 3~16자리로 해주세요", Toast.LENGTH_SHORT).show();
                }
                else{
                    joinBT.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        PW.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();
                if (!IDRegex(s)){
                    joinBT.setEnabled(false);
                    Toast.makeText(JoinActivity.this, "비밀번호는 영문과 숫자를 포함하여 6~15자리입니다", Toast.LENGTH_SHORT).show();
                }
                else{
                    joinBT.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();
                if (!EmailRegex(s)){
                    joinBT.setEnabled(false);
                    Toast.makeText(JoinActivity.this, "이메일 형식에 올바르게 작성해주세요", Toast.LENGTH_SHORT).show();
                }
                else{
                    joinBT.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        joinBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = ID.getText().toString();
                pw = PW.getText().toString();
                email = Email.getText().toString();
                username = Username.getText().toString();
                createPost(id,pw,email,username);
                if (id.length()==0||pw.length()==0||email.length()==0||username.length()==0){

                }

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
    private static boolean IDRegex(String input){
        Pattern pattern = Pattern.compile("^[A-Za-z]{1}[A-Za-z0-9]{3,16}$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()){
            return true;
        }
        else{
            return false;
        }
    }
    private static boolean PasswordRegex(String input){
        Pattern pattern = Pattern.compile("^.*(?=^.{6,15}$)(?=.*\\d)(?=.*[a-zA-Z]).*$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()){
            return true;
        }
        else{
            return false;
        }
    }
    private static boolean UsernameRegex(String input){
        Pattern pattern = Pattern.compile("^[\\w\\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,20}$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()){
            return true;
        }
        else{
            return false;
        }
    }
    private static boolean EmailRegex(String input){
        Pattern pattern = Pattern.compile("^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()){
            return true;
        }
        else{
            return false;
        }
    }

}
