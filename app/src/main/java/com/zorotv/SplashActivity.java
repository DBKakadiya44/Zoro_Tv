package com.zorotv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.BuildConfig;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.zorotv.Ads.AdmCommon;
import com.zorotv.Ads.AdsCallBack;
import com.zorotv.Ads.Ads_Preference;
import com.zorotv.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

     ActivitySplashBinding binding;
    Ads_Preference adsDataPrefs;
    int timeSplashOpen;
    AdmCommon admCommon;
    public static Boolean QUREKA_GIF;
    public static Boolean isCheckLater = false;
    public static String QUREKA_LINK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.appbar));

        admCommon = new AdmCommon();
        AdmCommon.mInstance = null;
        this.adsDataPrefs = new Ads_Preference(this);
        //check if first time app open (for load ad data)
        if (this.adsDataPrefs.getIsFirstTime() == true) {
            //After first time all time splash intent time
            timeSplashOpen = 3;
        } else {
            //First time open app splash intent time
            timeSplashOpen = 5;
            this.adsDataPrefs.setIsFirstTime(true);
        }

        if (!AdmCommon.getInstance().isOnline(SplashActivity.this)) {//check internet connection off display alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
            builder.setTitle("No internet Connection");
            builder.setMessage("Please turn on internet connection to continue");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setCancelable(false);
            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (AdmCommon.getInstance().isOnline(SplashActivity.this)) {//if net connect
                        dialog.dismiss();//dialog dismiss
                        connectFirebase();//load ad data

                    } else {
                        builder.show();//alert dialog display
                    }
                }
            });
            builder.show();

        } else {
            connectFirebase();//if connect load ad data
        }

    }

    private void connectFirebase() {


//      String s=  FirebaseFirestore.getInstance().collection("Clap to Find my phone").document("Ads").getId();
//        Log.d("ghty", "No such document" +s);
//


        FirebaseFirestore.getInstance().collection(getResources().getString(R.string.app_name_firebase))
                .document("Ads").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot snapshot) {
                        Log.e("vvvvv", "onDataChange: " + snapshot);

                        //qureka gif
                        QUREKA_GIF = snapshot.getBoolean("QUREKA_GIF");
                        QUREKA_LINK = snapshot.get("LINK_QUREKA").toString();

//                        App Open Ad
                        adsDataPrefs.setAppOpenSplashEnable(snapshot.getBoolean("APP_OPEN_ENABLE"));
                        adsDataPrefs.setAdmAppOpenID(snapshot.get("APP_OPEN_ID").toString());

                        //AdMob Ad
                        adsDataPrefs.setAdmBannerID(snapshot.get("AM_BANNER_ID").toString());
                        adsDataPrefs.setAdmInterstitialID(snapshot.get("AM_INTERSTITIAL_ID").toString());
                        adsDataPrefs.setAdmNativeID(snapshot.get("AM_NATIVE_ID").toString());

                        //Facebook Ad
                        adsDataPrefs.setFbBannerID(snapshot.get("FB_BANNER_ID").toString());
                        adsDataPrefs.setFbInterstitialID(snapshot.get("FB_INTERSTITIAL_ID").toString());
                        adsDataPrefs.setFbNativeID(snapshot.get("FB_NATIVE_ID").toString());

                        //Interstitial Ad
                        adsDataPrefs.setInterstitialClickGap(snapshot.getString("INTERSTITIAL_CLICK_MODULE"));
                        adsDataPrefs.setInterstitialBackPressClickGap(snapshot.getString("INTERSTITIAL_BACKPRESS_CLICK_MODULE"));
                        adsDataPrefs.setinterstitial_enable(snapshot.getBoolean("INTERSTITIAL_ENABLE"));
                        adsDataPrefs.setInterstitialEnableBackPress(snapshot.getBoolean("INTERSTITIAL_ENABLE_BACKPRESS"));
                        adsDataPrefs.setInterstitialGapEnable(snapshot.getBoolean("INTERSTITIAL_GAP_ENABLE"));
                        adsDataPrefs.setQurekaEnable(snapshot.getBoolean("INTERSTITIAL_QUREKA_ENABLE"));
                        adsDataPrefs.set_qureka_time(snapshot.get("INTERSTITIAL_QUREKA_TIME").toString());
                        adsDataPrefs.set_Custom_Inter(snapshot.getBoolean("CUSTOM_INTERSTITIAl_ENABLE"));

                        //native Ad
                        adsDataPrefs.setnative_ads_enable(snapshot.getBoolean("NATIVE_ENABLE"));
                        adsDataPrefs.setnative_qureka_enable(snapshot.getBoolean("NATIVE_QUREKA_ENABLE"));

                        //Banner Ad
                        adsDataPrefs.setbanner_enable(snapshot.getBoolean("BANNER_ENABLE"));

                        //Priority
                        adsDataPrefs.set_priority(snapshot.get("PRIORITY").toString());

                        //Link
                        adsDataPrefs.set_privacy_url(snapshot.get("LINK_PRIVACY_POLICY").toString());
                        adsDataPrefs.setQureka_link(snapshot.get("LINK_QUREKA").toString());

                        //Version Check
                        adsDataPrefs.set_new_app_version(snapshot.get("NEW_APP_VERSION_CHECK").toString());
                        adsDataPrefs.set_new_link(snapshot.get("NEW_LINK").toString());

                        //Update Dialog
                        adsDataPrefs.set_Update_dialog_close_enable(snapshot.getBoolean("UPDATE_DIALOG_CLOSE_ENABLE"));

                        //Qureka Webview
                        adsDataPrefs.set_qureka_webview_on_off(snapshot.get("QUREKA_WEBVIEW_ON_OFF").toString());

                        //Hide Bottom Native
                        adsDataPrefs.set_bottom_native_enable(snapshot.getBoolean("BOTTOM_NATIVE_ENABLE"));

                        //VPN
//                        adsDataPrefs.set_VPN_URL(snapshot.get("VPN_BASE_URL").toString());
//                        adsDataPrefs.set_VPN_ID(snapshot.get("VPN_CARRIER_ID").toString());
//                        adsDataPrefs.set_VPN_ON_OFF(snapshot.get("VPN_ON_OFF").toString());

                    }
                });


        FirebaseFirestore.getInstance().collection(getResources().getString(R.string.app_name_firebase))
                .document("Ads").get().addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        appStart();
                    }
                });
        if (!adsDataPrefs.getAppOpenSplashEnable()) {//if false not display app open ad
            //no display custom and qureka interstitial in splash only FB and AM interstitial display

            if (new Ads_Preference(SplashActivity.this).get_priority().equalsIgnoreCase("AM")
                    || new Ads_Preference(SplashActivity.this).get_priority().equalsIgnoreCase("FB")) { //check ad priority

                AdmCommon.getInstance().mInterstitialAdClickCount++;
                AdmCommon.getInstance().loadOrShowAdmInterstial(true, SplashActivity.this, new AdsCallBack() {
                    @Override
                    public void onAdsClose() {
                        appStart();
                    }

                    @Override
                    public void onLoading() {
                        appStart();
                    }
                });

            } else {//if priority value not AM or FB intent next activity
                appStart();
            }

        } else if (adsDataPrefs.getAppOpenSplashEnable()) {//if true

            AdRequest build2 = new AdRequest.Builder().build();
            AppOpenAd.load(SplashActivity.this, adsDataPrefs.getAdmAppOpenID(), build2, 1, new AppOpenAd.AppOpenAdLoadCallback() {
                public void onAdLoaded(AppOpenAd appOpenAd) {//load app open
                    super.onAdLoaded((AppOpenAd) appOpenAd);
                    appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {

                        @Override

                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            appStart();
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {//dismiss app open
                            super.onAdDismissedFullScreenContent();
                            appStart();//intent next activity
                        }
                    });
                    appOpenAd.show(SplashActivity.this);
                }

                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {//error in load app open ad
                    super.onAdFailedToLoad(loadAdError);
                    appStart();//intent next activity
                }
            });
        }

        //**************************************update dialoug**********************************************************//
        if (!adsDataPrefs.get_new_app_version().equalsIgnoreCase(BuildConfig.VERSION_NAME) && !isCheckLater && !adsDataPrefs.get_new_app_version().equals("")) {//check Update in app
            updateAppDialog();

        } else if (!adsDataPrefs.getAppOpenSplashEnable()) {
            //if false not display app open ad
            //no display custom and qureka interstitial in splash only FB and AM interstitial display

            if (new Ads_Preference(SplashActivity.this).get_priority().equalsIgnoreCase("AM")
                    || new Ads_Preference(SplashActivity.this).get_priority().equalsIgnoreCase("FB")) { //check ad priority

                AdmCommon.getInstance().mInterstitialAdClickCount++;
                AdmCommon.getInstance().loadOrShowAdmInterstial(true, SplashActivity.this, new AdsCallBack() {
                    @Override
                    public void onAdsClose() {
                        appStart();
                    }

                    @Override
                    public void onLoading() {
                        appStart();
                    }
                });

            } else {//if priority value not AM or FB intent next activity
                appStart();
            }

        } else if (adsDataPrefs.getAppOpenSplashEnable()) {//if true

            AdRequest build2 = new AdRequest.Builder().build();
            AppOpenAd.load(SplashActivity.this, adsDataPrefs.getAdmAppOpenID(), build2, 1, new AppOpenAd.AppOpenAdLoadCallback() {
                public void onAdLoaded(AppOpenAd appOpenAd) {//load app open
                    super.onAdLoaded((AppOpenAd) appOpenAd);
                    appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {

                        @Override

                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            appStart();
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {//dismiss app open
                            super.onAdDismissedFullScreenContent();
                            appStart();//intent next activity
                        }
                    });
                    appOpenAd.show(SplashActivity.this);
                }

                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {//error in load app open ad
                    super.onAdFailedToLoad(loadAdError);
                    appStart();//intent next activity
                }
            });
        } else {

            appStart();
        }
    }

    private void appStart() {
        Log.e("dhsjhfv", "appStart: ");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this, PrivacyActivity.class));
                finish();
            }

        }, timeSplashOpen);

    }

    private void updateAppDialog() {


        Dialog dialog = new Dialog(SplashActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_update_app);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        TextView tvDesc = dialog.findViewById(R.id.tvDesc);
        String Desc = "App recommends that you update to the latest version. You can keep using this while downloading the update.";
        tvDesc.setText(Desc);
        TextView tvLater = dialog.findViewById(R.id.tvLater);
        TextView tvUpdate = dialog.findViewById(R.id.tvUpdate);
        dialog.show();
        if (adsDataPrefs.get_Update_dialog_close_enable()) {
            tvLater.setVisibility(View.VISIBLE);
        } else {
            tvLater.setVisibility(View.GONE);
        }

        tvLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                isCheckLater = true;
                connectFirebase();
            }
        });

        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String linkUpdate = adsDataPrefs.get_new_link();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkUpdate));
                startActivity(intent);
            }
        });
    }

}