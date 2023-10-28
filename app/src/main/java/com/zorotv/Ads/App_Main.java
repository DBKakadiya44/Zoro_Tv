package com.zorotv.Ads;

import android.content.SharedPreferences;

import com.facebook.ads.AudienceNetworkAds;


public class App_Main extends PreferenceManager {
    public static SharedPreferences sharedPreferences;
    private static final String CHANNEL_ID = "vpn";
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences(getPackageName(), 0);
        new AppOpenManager(this);
        AudienceNetworkAds.initialize(this);
//        initHydraSdk();
    }


//    public void initHydraSdk() {
//        createNotificationChannel();
//
//        List<TransportConfig> transportConfigList = new ArrayList<>();
//        transportConfigList.add(HydraTransportConfig.create());
//        transportConfigList.add(OpenVpnTransportConfig.tcp());
//        transportConfigList.add(OpenVpnTransportConfig.udp());
//        UnifiedSdk.update(transportConfigList, CompletableCallback.EMPTY);
//
//        SdkNotificationConfig notificationConfig = SdkNotificationConfig.newBuilder()
//                .title(getResources().getString(R.string.app_name))
//                .channelId(CHANNEL_ID)
//                .build();
//        UnifiedSdk.update(notificationConfig);
//
//        UnifiedSdk.setLoggingLevel(Log.VERBOSE);
//    }
//
//    private void createNotificationChannel() {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = "Sample VPN";
//            String description = "VPN notification";
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
//            // Register the channel with the system; you can't change the importance
//            // or other notification behaviors after this
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
//    }
}
