package com.zorotv.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zorotv.DataModel.MovieData;
import com.zorotv.HomeActivity;
import com.zorotv.PlayActivity;
import com.zorotv.R;
import java.util.ArrayList;
public class MysteryAdapter extends RecyclerView.Adapter<MysteryAdapter.ViewHolder>
{
    HomeActivity homeActivity;
    ArrayList<MovieData> data;
    public MysteryAdapter(HomeActivity homeActivity, ArrayList<MovieData> data) {
        this.homeActivity=homeActivity;
        this.data=data;
    }

    @NonNull
    @Override
    public MysteryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(homeActivity).inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MysteryAdapter.ViewHolder holder, int position) {
        holder.textView.setText(""+data.get(position).getName());

        Glide.with(homeActivity)
                .load("https://moviescraft.com/Anime-TV/"+data.get(position).getThumbnail())
                .into(holder.imageView);

//        holder.imageView.setImageResource(R.drawable.movie);

        holder.imageView.setOnClickListener(view -> {
            Intent intent = new Intent(homeActivity , PlayActivity.class);
            intent.putExtra("image",data.get(position).getBanner());
            intent.putExtra("name","Mystery Movie");
            intent.putExtra("about",data.get(position).getAbout());
            homeActivity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemmovieimage);
            textView = itemView.findViewById(R.id.tvname);
        }
    }
}
