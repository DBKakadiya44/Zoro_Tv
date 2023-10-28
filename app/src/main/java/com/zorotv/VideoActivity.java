package com.zorotv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zorotv.Ads.AdmCommon;
import com.zorotv.Ads.AdsCallBack;
import com.zorotv.Ads.Ads_Preference;
import com.zorotv.R;
import com.zorotv.databinding.ActivityVideoBinding;

public class VideoActivity extends AppCompatActivity {
    ActivityVideoBinding binding;
    Ads_Preference adsDataPrefs;
    int currentProgress = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.appbar));

//        String videoPath = "https://moviescraft.com/Videocallapp/m1.mp4";
        String name = getIntent().getStringExtra("moviename");
        String videoPath = getIntent().getStringExtra("link");

        Log.d("AAASSA", "onCreate: Video = "+videoPath);

        AdmCommon.getInstance().AmNativeLoad(this, binding.llNativeBig, true);
        adsDataPrefs = new Ads_Preference(this);

        binding.textView2.setText(""+name);

        Uri videoUri = Uri.parse(videoPath);
        binding.Videoview.setVideoURI(videoUri);
        binding.Videoview.start();

        binding.ivBack.setOnClickListener(view -> {

            AdmCommon.getInstance().mInterstitialAdBackPressClickCount++;
            AdmCommon.getInstance().loadOrShowAdmInterstial(false,this, new AdsCallBack() {
                @Override
                public void onAdsClose() {
                    onBackPressed();
                }
                @Override
                public void onLoading() {
                    onBackPressed();
                }
            });


        });

        binding.play.setOnClickListener(view -> {
            binding.Videoview.start();
            binding.pause.setVisibility(View.VISIBLE);
            binding.play.setVisibility(View.INVISIBLE);
        });

        binding.pause.setOnClickListener(view -> {
            binding.Videoview.pause();
            binding.pause.setVisibility(View.INVISIBLE);
            binding.play.setVisibility(View.VISIBLE);
        });

        binding.rotate.setOnClickListener(view -> {
            int currentOrientation = getResources().getConfiguration().orientation;
            if (currentOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                updateProgress();
            }
        }, 1000);

    }

    private void updateProgress() {
        if (currentProgress <= 1000000) {
            binding.duration.setProgress(currentProgress, true);
            currentProgress += 1;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateProgress();
                }
            }, 1000); // You can adjust the update interval
        }
    }
}