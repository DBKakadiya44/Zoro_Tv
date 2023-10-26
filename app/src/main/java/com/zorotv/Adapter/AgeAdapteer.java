package com.zorotv.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zorotv.AgeActivity;
import com.zorotv.R;

public class AgeAdapteer extends RecyclerView.Adapter<AgeAdapteer.ViewHolder> {
    AgeActivity ageActivity;
    int[] number;

    public AgeAdapteer(AgeActivity ageActivity, int[] number) {
        this.ageActivity = ageActivity;
        this.number = number;
    }

    @NonNull
    @Override
    public AgeAdapteer.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ageActivity).inflate(R.layout.item_age, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgeAdapteer.ViewHolder holder, int position) {
        holder.textView.setText("" + number[position]);
    }

    @Override
    public int getItemCount() {
        return number.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvNumber);
        }

    }
}
