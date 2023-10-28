package com.zorotv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.zorotv.Ads.AdmCommon;
import com.zorotv.Ads.AdsCallBack;
import com.zorotv.Ads.Ads_Preference;
import com.zorotv.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding b;
    Ads_Preference adsDataPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());



        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.appbar));

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

        AdmCommon.getInstance().AmNativeLoad(this, b.llNativeBig, true);
        adsDataPrefs = new Ads_Preference(this);

        b.tvEnglish.setOnClickListener(v -> {
            AdmCommon.getInstance().mInterstitialAdBackPressClickCount++;
            AdmCommon.getInstance().loadOrShowAdmInterstial(false, this, new AdsCallBack() {
                @Override
                public void onAdsClose() {
                    startActivity(new Intent(MainActivity.this, StartActivity.class));
                }

                @Override
                public void onLoading() {
                    startActivity(new Intent(MainActivity.this, StartActivity.class));
                }
            });

        });
        b.tvHindi.setOnClickListener(v -> {
            AdmCommon.getInstance().mInterstitialAdBackPressClickCount++;
            AdmCommon.getInstance().loadOrShowAdmInterstial(false, this, new AdsCallBack() {
                @Override
                public void onAdsClose() {
                    startActivity(new Intent(MainActivity.this, StartActivity.class));
                }

                @Override
                public void onLoading() {
                    startActivity(new Intent(MainActivity.this, StartActivity.class));
                }
            });
        });
    }

    private void openchrome()
    {

        String url = SplashActivity.QUREKA_LINK;

        CustomTabsIntent.Builder customIntent = new CustomTabsIntent.Builder();

        customIntent.setToolbarColor(ContextCompat.getColor(MainActivity.this, R.color.purple_200));

        openCustomTab(MainActivity.this, customIntent.build(), Uri.parse(url));
    }

    public static void openCustomTab(Activity activity, CustomTabsIntent customTabsIntent, Uri uri) {
        // package name is the default package
        // for our custom chrome tab
        String packageName = "com.android.chrome";
        if (packageName != null) {

            // we are checking if the package name is not null
            // if package name is not null then we are calling
            // that custom chrome tab with intent by passing its
            // package name.
            customTabsIntent.intent.setPackage(packageName);

            // in that custom tab intent we are passing
            // our url which we have to browse.
            customTabsIntent.launchUrl(activity, uri);
        } else {
            // if the custom tabs fails to load then we are simply
            // redirecting our user to users device default browser.
            activity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }
}