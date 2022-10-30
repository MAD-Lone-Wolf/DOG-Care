package com.example.madproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private show activity;
    private List<Model> mList;

    public MyAdapter (show activity, List<Model> mList){
        this.activity = activity;
        this.mList = mList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.dogname.setText(mList.get(position).getDogname());
        holder.dogbreed.setText(mList.get(position).getDogbreed());
        holder.dob.setText(mList.get(position).getDob());
        holder.description.setText(mList.get(position).getDescription());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView dogname , dogbreed, dob, description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dogname = itemView.findViewById(R.id.dogname);
            dogbreed = itemView.findViewById(R.id.dogbreed);
            dob = itemView.findViewById(R.id.dob);
            description = itemView.findViewById(R.id.description);



        }
    }


}
