//package com.example.danjam.adapter;
//
//import android.util.TypedValue;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.example.danjam.R;
//
//import java.util.ArrayList;
//import java.util.Dictionary;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class RankRecyclerAdapter extends RecyclerView.Adapter<RankRecyclerAdapter.CustomViewHolder> {
//
//    private ArrayList<Dictionary> mList;
//
//
//    public class CustomViewHolder extends RecyclerView.ViewHolder {
//        protected TextView id;
//        protected TextView english;
//        protected TextView korean;
//
//        public CustomViewHolder(View view) {
//            super(view);
//            this.id = view.findViewById(R.id.listitem);
//            this.english = view.findViewById(R.id.englishitem);
//            this.korean = view.findViewById(R.id.koreanitem);
//
//        }
//    }
//
//    public RankRecyclerAdapter(ArrayList<Dictionary> list) {
//        this.mList = list;
//    }
//
//
//    @Override
//    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
//
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
//
//        CustomViewHolder viewHolder = new CustomViewHolder(view);
//
//        return viewHolder;
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CustomViewHolder viewHolder, int position) {
//
//        viewHolder.id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
//        viewHolder.english.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
//        viewHolder.korean.setGravity(Gravity.CENTER);
//
//        viewHolder.id.setText(mList.get(position).getId());
//        viewHolder.english.setText(mList.get(position).getEnglish());
//        viewHolder.korean.setText(mList.get(position).getKorean());
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return (null != mList ? mList.size() : 0);
//    }
//}