package com.zorotv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.zorotv.Adapter.AgeAdapteer;
import com.zorotv.Ads.AdmCommon;
import com.zorotv.Ads.AdsCallBack;
import com.zorotv.Ads.Ads_Preference;
import com.zorotv.databinding.ActivityAgeBinding;

public class AgeActivity extends AppCompatActivity {
ActivityAgeBinding b;
int[] number = {17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99};
    Ads_Preference adsDataPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=ActivityAgeBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.appbar));

        AdmCommon.getInstance().AmNativeLoad(this,b.llNativeBig, true);
        adsDataPrefs = new Ads_Preference(this);

        if(SplashActivity.QUREKA_GIF==true){
            b.qureka1.setVisibility(View.VISIBLE);
            b.qureka2.setVisibility(View.VISIBLE);
            b.qureka3.setVisibility(View.VISIBLE);
        }else {
            b.qureka1.setVisibility(View.INVISIBLE);
            b.qureka2.setVisibility(View.INVISIBLE);
            b.qureka3.setVisibility(View.INVISIBLE);
        }

        b.qureka1.setOnClickListener(view -> {
            openchrome();
        });
        b.qureka2.setOnClickListener(view -> {
            openchrome();
        });
        b.qureka3.setOnClickListener(view -> {
            openchrome();
        });

        Glide.with(this)
                .load(R.raw.q1)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(b.qureka1);

        Glide.with(this)
                .load(R.raw.q2)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(b.qureka2);

        Glide.with(this)
                .load(R.raw.q3)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(b.qureka3);

        b.tvNext.setOnClickListener(view -> {

            AdmCommon.getInstance().mInterstitialAdBackPressClickCount++;
            AdmCommon.getInstance().loadOrShowAdmInterstial(false,this, new AdsCallBack() {
                @Override
                public void onAdsClose() {
                    startActivity(new Intent(AgeActivity.this , HomeActivity.class));
                }
                @Override
                public void onLoading() {
                    startActivity(new Intent(AgeActivity.this , HomeActivity.class));
                }
            });
//            startActivity(new Intent(AgeActivity.this , HomeActivity.class));
        });

        AgeAdapteer adapteer = new AgeAdapteer(AgeActivity.this,number);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(AgeActivity.this,RecyclerView.HORIZONTAL,false);
        b.rvAge.setLayoutManager(manager);
        b.rvAge.setAdapter(adapteer);


    }

    private void openchrome()
    {

        String url = SplashActivity.QUREKA_LINK;

        CustomTabsIntent.Builder customIntent = new CustomTabsIntent.Builder();

        customIntent.setToolbarColor(ContextCompat.getColor(AgeActivity.this, R.color.purple_200));

        openCustomTab(AgeActivity.this, customIntent.build(), Uri.parse(url));
    }

    public static void openCustomTab(Activity activity, CustomTabsIntent customTabsIntent, Uri uri) {
        // package name is the default package
        // for our custom chrome tab
        String packageName = "com.android.chrome";
        if (packageName != null) {
            customTabsIntent.intent.setPackage(packageName);
            customTabsIntent.launchUrl(activity, uri);
        } else {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }
}