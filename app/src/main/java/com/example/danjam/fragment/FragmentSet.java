package com.example.danjam.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.danjam.R;
import com.example.danjam.api.Apiservice;
import com.example.danjam.data.UpdateInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSet extends Fragment {
    EditText revise_id,revise_pw,revise_name;
    Button revise_id_button,revise_pw_button,revise_name_button,revise_id_button_check,revise_pw_button_check,revise_name_button_check;
    private String text_revise_id,text_revise_pw,text_revise_name,token;
    private String update_id,update_pw,update_email,update_name;
    private Retrofit retrofit;
    private InputMethodManager imm; //전역변수
    final static String BASE_URL = "https://unitaemin.run.goorm.io/danzam/";
    private int responsecode;

    public FragmentSet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set, container, false);

        revise_id = view.findViewById(R.id.revise_id);
        revise_pw = view.findViewById(R.id.revise_pw);
        revise_name = view.findViewById(R.id.revise_name);
        revise_id_button = view.findViewById(R.id.revise_id_button);
        revise_pw_button = view.findViewById(R.id.revise_pw_button);
        revise_name_button = view.findViewById(R.id.revise_name_button);
        revise_id_button_check = view.findViewById(R.id.revise_id_button_check);
        revise_pw_button_check = view.findViewById(R.id.revise_pw_button_check);
        revise_name_button_check = view.findViewById(R.id.revise_name_button_check);


        revise_id.setClickable(false);
        revise_id.setFocusable(false);

        revise_pw.setClickable(false);
        revise_pw.setFocusable(false);


        revise_name.setClickable(false);
        revise_name.setFocusable(false);

        //정보 갱신
        if (getArguments()!= null){

            update_id = getArguments().getString("update_id","");
            update_pw = getArguments().getString("update_pw","");
            update_name = getArguments().getString("update_name","");

            revise_id.setHint(update_id);
            revise_pw.setHint(update_pw);
            revise_name.setHint(update_name);

        }

        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE); //onCreate 이후,,

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        revise_id_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revise_id.setFocusableInTouchMode(true);
                revise_id.setFocusable(true);
                revise_id.setClickable(true);
                showKeyboard(revise_id);
                revise_id_button.setVisibility(View.INVISIBLE);
                revise_id_button_check.setVisibility(View.VISIBLE);

            }
        });

        revise_id_button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (responsecode == 400){
                    Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                }else{
                    text_revise_id = revise_id.getText().toString();
                    createPut("id",text_revise_id);
                }
                revise_id_button.setVisibility(View.VISIBLE);
                revise_id_button_check.setVisibility(View.INVISIBLE);
                revise_id.setClickable(false);
                revise_id.setFocusable(false);
                hideKeyboard(revise_id);

            }
        });




        revise_pw_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revise_pw.setFocusableInTouchMode(true);
                revise_pw.setFocusable(true);
                revise_pw.setClickable(true);
                showKeyboard(revise_pw);
            }
        });
        revise_pw_button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_revise_pw = revise_pw.getText().toString();
                createPut("password",text_revise_pw);
                revise_pw_button.setVisibility(View.VISIBLE);
                revise_pw_button_check.setVisibility(View.INVISIBLE);
                revise_pw.setClickable(false);
                revise_pw.setFocusable(false);
                hideKeyboard(revise_pw);

            }
        });



        revise_name_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revise_name.setFocusableInTouchMode(true);
                revise_name.setFocusable(true);
                revise_name.setClickable(true);
                showKeyboard(revise_name);
                revise_name_button.setVisibility(View.INVISIBLE);
                revise_name_button_check.setVisibility(View.VISIBLE);

            }
        });

        revise_name_button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_revise_name = revise_name.getText().toString();
                createPut("username",text_revise_name);
                revise_name_button.setVisibility(View.VISIBLE);
                revise_name_button_check.setVisibility(View.INVISIBLE);
                revise_name.setClickable(false);
                revise_name.setFocusable(false);
                showKeyboard(revise_name);
            }
        });


        return view;
    }



    private void createPut(String st1,String st2){

        Apiservice apiservice = retrofit.create(Apiservice.class);
        UpdateInfo updateInfo = new UpdateInfo(st1,st2);

        SharedPreferences token_sf = getActivity().getSharedPreferences("token",Context.MODE_PRIVATE);
        token = token_sf.getString("access_token","");

        Call<UpdateInfo> call =apiservice.ReviseUser("Bearer "+token,updateInfo);

        call.enqueue(new Callback<UpdateInfo>() {
            @Override
            public void onResponse(Call<UpdateInfo> call, Response<UpdateInfo> response) {
                if (response.isSuccessful()) {
                    responsecode = response.code();
                    Log.e("hi",response.code()+"");
                    Log.e("hi",response+"");
                }else { ;
                }
            }
            @Override
            public void onFailure(Call<UpdateInfo> call, Throwable t) {

            }
        });
    }

    private void showKeyboard(EditText editText) {
        imm.showSoftInput(editText, 0);
    }

    private void hideKeyboard(EditText editText) {
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
