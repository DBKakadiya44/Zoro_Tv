package com.zorotv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zorotv.Ads.AdmCommon;
import com.zorotv.Ads.AdsCallBack;
import com.zorotv.Ads.Ads_Preference;
import com.zorotv.DataModel.MovieData;
import com.zorotv.databinding.ActivityNameBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NameActivity extends AppCompatActivity {
    ActivityNameBinding binding;
    Ads_Preference adsDataPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.appbar));

        AdmCommon.getInstance().AmNativeLoad(this, binding.llNativeBig, true);
        adsDataPrefs = new Ads_Preference(this);

        if(SplashActivity.QUREKA_GIF==true){
            binding.qureka1.setVisibility(View.VISIBLE);
            binding.qureka2.setVisibility(View.VISIBLE);
            binding.qureka3.setVisibility(View.VISIBLE);
        }else {
            binding.qureka1.setVisibility(View.INVISIBLE);
            binding.qureka2.setVisibility(View.INVISIBLE);
            binding.qureka3.setVisibility(View.INVISIBLE);
        }

        binding.qureka1.setOnClickListener(view -> {
            openchrome();
        });
        binding.qureka2.setOnClickListener(view -> {
            openchrome();
        });
        binding.qureka3.setOnClickListener(view -> {
            openchrome();
        });

        Glide.with(this)
                .load(R.raw.q1)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.qureka1);

        Glide.with(this)
                .load(R.raw.q2)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.qureka2);

        Glide.with(this)
                .load(R.raw.q3)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.qureka3);

        binding.tvNext.setOnClickListener(v -> {

            dismissKeyboardShortcutsHelper();

            String name = String.valueOf(binding.etname.getText());
            String password = String.valueOf(binding.etpassword.getText());

            if(name.equals("") || password.equals("")){
                Toast.makeText(NameActivity.this, "Enter All Details", Toast.LENGTH_SHORT).show();
            }else {
                AdmCommon.getInstance().mInterstitialAdBackPressClickCount++;
                AdmCommon.getInstance().loadOrShowAdmInterstial(false,this, new AdsCallBack() {
                    @Override
                    public void onAdsClose() {
                        startActivity(new Intent(NameActivity.this, GenderActivity.class));
                    }
                    @Override
                    public void onLoading() {
                        startActivity(new Intent(NameActivity.this, GenderActivity.class));
                    }
                });

            }



        });

    }

    private void openchrome()
    {

        String url = SplashActivity.QUREKA_LINK;

        CustomTabsIntent.Builder customIntent = new CustomTabsIntent.Builder();

        customIntent.setToolbarColor(ContextCompat.getColor(NameActivity.this, R.color.purple_200));

        openCustomTab(NameActivity.this, customIntent.build(), Uri.parse(url));
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