package com.zorotv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.zorotv.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.appbar));

        b.tvEnglish.setOnClickListener(v -> {
            startActivity(new Intent(this , StartActivity.class));
        });
        b.tvHindi.setOnClickListener(v -> {
            startActivity(new Intent(this , StartActivity.class));
        });
    }
}