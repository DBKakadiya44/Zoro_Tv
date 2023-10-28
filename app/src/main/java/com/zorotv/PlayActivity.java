package com.zorotv;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.zorotv.Adapter.HotAdapter;
import com.zorotv.Ads.AdmCommon;
import com.zorotv.Ads.AdsCallBack;
import com.zorotv.Ads.Ads_Preference;
import com.zorotv.databinding.ActivityPlayBinding;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    ActivityPlayBinding binding;
    HotAdapter hotadapter;
    Ads_Preference adsDataPrefs;
    String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.appbar));

        AdmCommon.getInstance().AmNativeLoad(this, binding.llNativeSmall1, true);
        adsDataPrefs = new Ads_Preference(this);

        String image = getIntent().getStringExtra("image");
        String name = getIntent().getStringExtra("name");
        String about = getIntent().getStringExtra("about");
        String moviename = getIntent().getStringExtra("moviename");
        link = getIntent().getStringExtra("link");



        binding.playbtn.setOnClickListener(view -> {

            AdmCommon.getInstance().mInterstitialAdBackPressClickCount++;
            AdmCommon.getInstance().loadOrShowAdmInterstial(false,this, new AdsCallBack() {
                @Override
                public void onAdsClose() {

                    if(link.equals(""))
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(PlayActivity.this);

                        builder.setTitle("Alert.!");
                        builder.setMessage("Server Busy..!!\nTry After Sometime...");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }else
                    {
                        Intent intent = new Intent(PlayActivity.this , VideoActivity.class);
                        intent.putExtra("moviename",moviename);
                        intent.putExtra("link",link);
                        startActivity(intent);
                    }
                }
                @Override
                public void onLoading() {
                    if(link.equals(""))
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(PlayActivity.this);

                        builder.setTitle("Alert.!");
                        builder.setMessage("Server Busy..!!\nTry After Sometime...");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();

                    }else
                    {
                        Intent intent = new Intent(PlayActivity.this , VideoActivity.class);
                        intent.putExtra("moviename",moviename);
                        intent.putExtra("link",link);
                        startActivity(intent);
                    }
                }
            });
        });

        binding.ivBack.setOnClickListener(view -> {
            onBackPressed();
        });

        Glide.with(PlayActivity.this)
                .load("https://moviescraft.com/Anime-TV/" + image)
                .into(binding.imageView);

        binding.textView5.setText("" + name);
        binding.textView7.setText("" + about);

        Random random = new Random();
        int r = random.nextInt(12) + 1;


        if (r == 1) {
            hotadapter = new HotAdapter(PlayActivity.this, StartActivity.Top_Rateddata, "Top Rated Movie");
        }
        if (r == 2) {
            hotadapter = new HotAdapter(PlayActivity.this, StartActivity.Actiondata, "Action Movie");
        }
        if (r == 3) {
            hotadapter = new HotAdapter(PlayActivity.this, StartActivity.Adventuredata, "Adventure Movie");
        }
        if (r == 4) {
            hotadapter = new HotAdapter(PlayActivity.this, StartActivity.Comedydata, "Comedy Movie");
        }
        if (r == 5) {
            hotadapter = new HotAdapter(PlayActivity.this, StartActivity.Dramadata, "Drama Movie");
        }
        if (r == 6) {
            hotadapter = new HotAdapter(PlayActivity.this, StartActivity.Fantasydata, "Fantasy Movie");
        }
        if (r == 7) {
            hotadapter = new HotAdapter(PlayActivity.this, StartActivity.Historicaldata, "Historical Movie");
        }
        if (r == 8) {
            hotadapter = new HotAdapter(PlayActivity.this, StartActivity.Horrordata, "Horror Movie");
        }
        if (r == 9) {
            hotadapter = new HotAdapter(PlayActivity.this, StartActivity.Mysterydata, "Mystery Movie");
        }
        if (r == 10) {
            hotadapter = new HotAdapter(PlayActivity.this, StartActivity.Romancedata, "Romance Movie");
        }
        if (r == 11) {
            hotadapter = new HotAdapter(PlayActivity.this, StartActivity.Sci_Fidata, "Sci-Fi Movie");
        }
        if (r == 12) {
            hotadapter = new HotAdapter(PlayActivity.this, StartActivity.Thrillerdata, "Top Rated Movie");
        }

        RecyclerView.LayoutManager manager1 = new LinearLayoutManager(PlayActivity.this, RecyclerView.HORIZONTAL, false);
        binding.rvPlay.setLayoutManager(manager1);
        binding.rvPlay.setAdapter(hotadapter);

        binding.imageView2.setOnClickListener(view -> {
            binding.imageView2.setVisibility(View.INVISIBLE);
            binding.redheart.setVisibility(View.VISIBLE);
        });
        binding.redheart.setOnClickListener(view -> {
            binding.imageView2.setVisibility(View.VISIBLE);
            binding.redheart.setVisibility(View.INVISIBLE);
        });

        binding.imageView4.setOnClickListener(view -> {
            AdmCommon.getInstance().mInterstitialAdBackPressClickCount++;
            AdmCommon.getInstance().loadOrShowAdmInterstial(false,this, new AdsCallBack() {
                @Override
                public void onAdsClose() {

                        AlertDialog.Builder builder = new AlertDialog.Builder(PlayActivity.this);

                        builder.setTitle("Alert.!");
                        builder.setMessage("Server Down..!!\nTry Again...");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                }
                @Override
                public void onLoading() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(PlayActivity.this);

                        builder.setTitle("Alert.!");
                        builder.setMessage("Server Busy..!!\nTry After Sometime...");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                }
            });
        });


        binding.sharebtn.setOnClickListener(view -> {
            shareAppLink();
        });


        binding.imageView3.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(PlayActivity.this);

            builder.setTitle("Details");
            builder.setMessage("MOVIE NAME :- \n"+moviename+"\n\nGENRES :- \n"+name+"\n\nABOUT :- \n"+about);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent = new Intent(PlayActivity.this, HomeActivity.class);
//        startActivity(intent);
    }

    private void shareAppLink() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        String appLink = "https://play.google.com/store/apps/details?id=your.package.name"; // Replace with your app's Play Store link
        String message = "Check out my cool app:\n" + appLink;

        shareIntent.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(Intent.createChooser(shareIntent, "Share App via"));
    }
}