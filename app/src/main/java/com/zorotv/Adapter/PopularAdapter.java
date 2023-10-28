package com.zorotv.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zorotv.Ads.AdmCommon;
import com.zorotv.Ads.AdsCallBack;
import com.zorotv.HomeActivity;
import com.zorotv.R;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder>
{
    HomeActivity homeActivity;
    int[] image;
    String[] name;
    public PopularAdapter(HomeActivity homeActivity, int[] image, String[] name) {
        this.homeActivity=homeActivity;
        this.image=image;
        this.name=name;
    }

    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(homeActivity).inflate(R.layout.item_popular_star,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(image[position]);
        holder.textView.setText(""+name[position]);

        holder.imageView.setOnClickListener(view -> {
            AdmCommon.getInstance().mInterstitialAdBackPressClickCount++;
            AdmCommon.getInstance().loadOrShowAdmInterstial(false,homeActivity, new AdsCallBack() {
                @Override
                public void onAdsClose() {

                }
                @Override
                public void onLoading() {

                }
            });
        });

    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView5);
        }
    }
}
