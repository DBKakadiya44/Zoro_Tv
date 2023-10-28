package com.zorotv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.zorotv.Ads.AdmCommon;
import com.zorotv.Ads.AdsCallBack;
import com.zorotv.Ads.Ads_Preference;
import com.zorotv.databinding.ActivityGenderBinding;

public class GenderActivity extends AppCompatActivity {
ActivityGenderBinding b;
    Ads_Preference adsDataPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=ActivityGenderBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.appbar));

        AdmCommon.getInstance().AmNativeLoad(this,b.llNativeBig, true);
        adsDataPrefs = new Ads_Preference(this);

        b.tvStart.setOnClickListener(v -> {

            AdmCommon.getInstance().mInterstitialAdBackPressClickCount++;
            AdmCommon.getInstance().loadOrShowAdmInterstial(false,this, new AdsCallBack() {
                @Override
                public void onAdsClose() {
                    startActivity(new Intent(GenderActivity.this , AgeActivity.class));
                }
                @Override
                public void onLoading() {
                    startActivity(new Intent(GenderActivity.this , AgeActivity.class));
                }
            });

        });

        b.img11.setOnClickListener(view -> {
            b.img11.setBackgroundResource(R.drawable.round_border);
            b.img12.setBackgroundColor(getColor(R.color.mainbg));
        });

        b.img12.setOnClickListener(view -> {
            b.img12.setBackgroundResource(R.drawable.round_border);
            b.img11.setBackgroundColor(getColor(R.color.mainbg));
        });



    }
}