package com.zorotv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.zorotv.databinding.ActivityPrivacyBinding;

public class PrivacyActivity extends AppCompatActivity {
ActivityPrivacyBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityPrivacyBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.appbar));

        b.textView.setOnClickListener(v -> {

            if(b.chechbox.isChecked()){
                startActivity(new Intent(this , MainActivity.class));
            }else {
                Toast.makeText(this, "Accept terms", Toast.LENGTH_SHORT).show();
            }

        });
    }
}