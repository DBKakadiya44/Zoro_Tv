package com.zorotv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.zorotv.Adapter.AgeAdapteer;
import com.zorotv.databinding.ActivityAgeBinding;

public class AgeActivity extends AppCompatActivity {
ActivityAgeBinding b;
int[] number = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=ActivityAgeBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.appbar));

        b.tvNext.setOnClickListener(view -> {
            startActivity(new Intent(this , HomeActivity.class));
        });

        AgeAdapteer adapteer = new AgeAdapteer(AgeActivity.this,number);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(AgeActivity.this,RecyclerView.HORIZONTAL,false);
        b.rvAge.setLayoutManager(manager);
        b.rvAge.setAdapter(adapteer);


    }
}