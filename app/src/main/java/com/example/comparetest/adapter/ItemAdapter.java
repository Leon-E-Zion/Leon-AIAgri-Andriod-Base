package com.example.comparetest.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comparetest.R;
import com.example.comparetest.data.Tian;
import com.example.comparetest.network.HiRetrofit;
import com.example.comparetest.network.Service;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Tian> mTianlist;
    Service service = HiRetrofit.retrofit.create(Service.class);

        static class ViewHolder extends RecyclerView.ViewHolder{
            View fruitView;
            TextView fruitName;
            ImageView fruitImage;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                fruitView = itemView;
                fruitImage = itemView.findViewById(R.id.fruit_image);
                fruitName = itemView.findViewById(R.id.fruit_name);
            }
        }

        public ItemAdapter(List<Tian> fruitList){
            mTianlist = fruitList;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tian_item,parent,false);
            final ViewHolder holder = new ViewHolder(view);
            //处理RecyclerView的点击事件，RecyclerView具有对任意控件的点击事件处理的功能，十分强大
            //fruitView代表当前实例
            holder.fruitView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    Tian tian = mTianlist.get(position);
                    Toast.makeText(view.getContext(),tian.getName(), Toast.LENGTH_SHORT).show();
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Tian tian = mTianlist.get(position);
            holder.fruitImage.setImageResource(R.drawable.login_image);
            holder.fruitName.setText(tian.getName());
        }

        @Override
        public int getItemCount() {
            return mTianlist.size();
        }
    }
