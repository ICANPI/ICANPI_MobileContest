package com.example.danjam.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.danjam.R;
import com.example.danjam.adapter.RankRecyclerAdapter;
import com.example.danjam.api.Apiservice;
import com.example.danjam.data.Refresh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRank extends Fragment {
    final static String BASE_URL = "https://unitaemin.run.goorm.io/danzam/";
    private Retrofit retrofit;
    RankRecyclerAdapter mAdapter;
    RecyclerView recyclerView;
    TextView rank_count;
    String token;
    String hello[] = {"","","","","","",""};
    boolean booleans[] ={false,false,false,false,false,false,false};
    int count;

    public FragmentRank() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_rank, container, false);

        recyclerView = view.findViewById(R.id.rank_recycler);
        rank_count = view.findViewById(R.id.rank_count);


        if (getArguments()!=null){
            hello = getArguments().getStringArray("hello");
            booleans = getArguments().getBooleanArray("booleans");
            for (int i=0;i<7;i++){

            }
        }

        SharedPreferences token_sf = getActivity().getSharedPreferences("token",Context.MODE_PRIVATE);
        token  = token_sf.getString("access_token","");

        GetRank(token);

        mAdapter = new RankRecyclerAdapter(hello,booleans);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        for (int i =0;i<7;i++){

            if (booleans[i]){
                count+=1;
            }

        }
        rank_count.setText(count+" 개");



        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        SharedPreferences token_sf = getActivity().getSharedPreferences("token",Context.MODE_PRIVATE);
        token  = token_sf.getString("access_token","");
        GetRank(token);

    }

    private void GetRank(String token){
        Apiservice apiservice = retrofit.create(Apiservice.class);
        Call<ResponseBody> call =apiservice.AchievementGet("Bearer "+token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String body = response.body().string();

                    JSONArray data = new JSONObject(body).getJSONArray("result");
                    for (int i=0;i<7;i++){
                        hello[i] = data.getJSONObject(0).getJSONArray("data").getJSONObject(i).getString("title");
                        booleans[i] = data.getJSONObject(0).getJSONArray("data").getJSONObject(i).getBoolean("value");


                    }
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                    Log.e("asd","씨발");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }


}
