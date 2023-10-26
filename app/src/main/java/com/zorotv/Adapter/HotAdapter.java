package com.zorotv.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zorotv.DataModel.MovieData;
import com.zorotv.PlayActivity;
import com.zorotv.R;

import java.util.ArrayList;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder>
{
    PlayActivity playActivity;
    ArrayList<MovieData> data;
    String category;
    public HotAdapter(PlayActivity playActivity, ArrayList<MovieData> data, String category) {
        this.playActivity=playActivity;
        this.data=data;
        this.category=category;
    }

    @NonNull
    @Override
    public HotAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(playActivity).inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotAdapter.ViewHolder holder, int position) {
        holder.textView.setText(""+data.get(position).getName());

        Glide.with(playActivity)
                .load("https://moviescraft.com/Anime-TV/"+data.get(position).getThumbnail())
                .into(holder.imageView);

//        holder.imageView.setImageResource(R.drawable.movie);

        holder.imageView.setOnClickListener(view -> {
            Intent intent = new Intent(playActivity , PlayActivity.class);
            intent.putExtra("image",data.get(position).getBanner());
            intent.putExtra("name",category);
            intent.putExtra("about",data.get(position).getAbout());
            playActivity.startActivity(intent);
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
