package com.example.danjam.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danjam.R;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RankRecyclerAdapter extends RecyclerView.Adapter<RankRecyclerAdapter.CustomViewHolder> {

    public String[] mList;
    public boolean[] check;


    public RankRecyclerAdapter(String[] hello, boolean[] booleans) {
        this.mList = hello;
        this.check = booleans;
    }

    @NonNull
    @Override
    public RankRecyclerAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_item,parent,false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RankRecyclerAdapter.CustomViewHolder holder, int position) {

        holder.textView.setText(mList[position]);

        if (check[position]){
            holder.check.setImageResource(R.drawable.icon_check);
        }else{
            holder.check.setImageResource(R.drawable.not_check);
        }

    }

    @Override
    public int getItemCount() {
        if (mList==null){
            return 0;
        }
        return mList.length;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView check;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.achievements_tv);
            this.check = itemView.findViewById(R.id.check);
        }
    }
}