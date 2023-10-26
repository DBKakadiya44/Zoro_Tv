package com.zorotv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.zorotv.Adapter.HotAdapter;
import com.zorotv.databinding.ActivityPlayBinding;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    ActivityPlayBinding binding;
    HotAdapter hotadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.appbar));

        String image = getIntent().getStringExtra("image");
        String name = getIntent().getStringExtra("name");
        String about = getIntent().getStringExtra("about");

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

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent = new Intent(PlayActivity.this, HomeActivity.class);
//        startActivity(intent);
    }
}