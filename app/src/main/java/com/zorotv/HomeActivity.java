package com.zorotv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.zorotv.Adapter.ActionAdapter;
import com.zorotv.Adapter.AdventureAdapter;
import com.zorotv.Adapter.ComedyAdapter;
import com.zorotv.Adapter.DramaAdapter;
import com.zorotv.Adapter.FantasyAdapter;
import com.zorotv.Adapter.HistoricalAdapter;
import com.zorotv.Adapter.HorrorAdapter;
import com.zorotv.Adapter.MysteryAdapter;
import com.zorotv.Adapter.PopularAdapter;
import com.zorotv.Adapter.RomanceAdapter;
import com.zorotv.Adapter.SciFiAdapter;
import com.zorotv.Adapter.ThrillerAdapter;
import com.zorotv.Adapter.TopRatedAdapter;
import com.zorotv.Ads.AdmCommon;
import com.zorotv.Ads.Ads_Preference;
import com.zorotv.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    Ads_Preference adsDataPrefs;
    int[] image = {R.drawable.ani1, R.drawable.ani2, R.drawable.ani3, R.drawable.ani4, R.drawable.ani5, R.drawable.ani6, R.drawable.ani7, R.drawable.ani8, R.drawable.ani9};
    String[] name = {"Mickey", "Rollno.21", "Ben Ten", "Oggy", "Ninja Hattori", "Doremon", "Tom Jerry", "Motu Patlu", "Chhota Bheem"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.appbar));

        AdmCommon.getInstance().AmNativeLoad(this, binding.inc.llNativeBig, true);
        adsDataPrefs = new Ads_Preference(this);

        adsDataPrefs = new Ads_Preference(this);
        AdmCommon.getInstance().loadBanner(this, (ViewGroup) findViewById(R.id.llBanner));

        binding.inc.ivToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    binding.drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        binding.inc.serchicon.setOnClickListener(view -> {

        });

        PopularAdapter adapter = new PopularAdapter(HomeActivity.this, image, name);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false);
        binding.inc.rvPopularStars.setLayoutManager(manager);
        binding.inc.rvPopularStars.setAdapter(adapter);

        binding.menus.navHome.setOnClickListener(view -> {
            binding.drawerLayout.close();
        });
        binding.menus.navMoreApp.setOnClickListener(view -> {
            binding.drawerLayout.close();
        });
        binding.menus.navPrivacy.setOnClickListener(view -> {
            binding.drawerLayout.close();
        });
        binding.menus.navShare.setOnClickListener(view -> {
            binding.drawerLayout.close();
        });
        binding.menus.navFeedback.setOnClickListener(view -> {
            binding.drawerLayout.close();
        });

        TopRatedAdapter topRatedAdapter = new TopRatedAdapter(HomeActivity.this, StartActivity.Top_Rateddata);
        RecyclerView.LayoutManager manager1 = new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false);
        binding.inc.rvTopRated.setLayoutManager(manager1);
        binding.inc.rvTopRated.setAdapter(topRatedAdapter);

        ActionAdapter actionAdapter = new ActionAdapter(HomeActivity.this, StartActivity.Actiondata);
        RecyclerView.LayoutManager manager2 = new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false);
        binding.inc.rvAction.setLayoutManager(manager2);
        binding.inc.rvAction.setAdapter(actionAdapter);

        AdventureAdapter adventureAdapter = new AdventureAdapter(HomeActivity.this, StartActivity.Adventuredata);
        RecyclerView.LayoutManager manager3 = new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false);
        binding.inc.rvAdventure.setLayoutManager(manager3);
        binding.inc.rvAdventure.setAdapter(adventureAdapter);

        ComedyAdapter comedyAdapter = new ComedyAdapter(HomeActivity.this, StartActivity.Comedydata);
        RecyclerView.LayoutManager manager4 = new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false);
        binding.inc.rvComedy.setLayoutManager(manager4);
        binding.inc.rvComedy.setAdapter(comedyAdapter);

        DramaAdapter dramaAdapter = new DramaAdapter(HomeActivity.this, StartActivity.Dramadata);
        RecyclerView.LayoutManager manager5 = new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false);
        binding.inc.rvDrama.setLayoutManager(manager5);
        binding.inc.rvDrama.setAdapter(dramaAdapter);

        FantasyAdapter fantasyAdapter = new FantasyAdapter(HomeActivity.this, StartActivity.Fantasydata);
        RecyclerView.LayoutManager manager6 = new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false);
        binding.inc.rvFantasy.setLayoutManager(manager6);
        binding.inc.rvFantasy.setAdapter(fantasyAdapter);

        HistoricalAdapter historicalAdapter = new HistoricalAdapter(HomeActivity.this, StartActivity.Historicaldata);
        RecyclerView.LayoutManager manager7 = new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false);
        binding.inc.rvHistorical.setLayoutManager(manager7);
        binding.inc.rvHistorical.setAdapter(historicalAdapter);

        HorrorAdapter horrorAdapter = new HorrorAdapter(HomeActivity.this, StartActivity.Horrordata);
        RecyclerView.LayoutManager manager8 = new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false);
        binding.inc.rvHorror.setLayoutManager(manager8);
        binding.inc.rvHorror.setAdapter(horrorAdapter);

        MysteryAdapter mysteryAdapter = new MysteryAdapter(HomeActivity.this, StartActivity.Mysterydata);
        RecyclerView.LayoutManager manager9 = new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false);
        binding.inc.rvMystery.setLayoutManager(manager9);
        binding.inc.rvMystery.setAdapter(mysteryAdapter);

        RomanceAdapter romanceAdapter = new RomanceAdapter(HomeActivity.this, StartActivity.Romancedata);
        RecyclerView.LayoutManager manager10 = new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false);
        binding.inc.rvRomance.setLayoutManager(manager10);
        binding.inc.rvRomance.setAdapter(romanceAdapter);

        SciFiAdapter sciFiAdapter = new SciFiAdapter(HomeActivity.this, StartActivity.Sci_Fidata);
        RecyclerView.LayoutManager manager11 = new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false);
        binding.inc.rvSciFi.setLayoutManager(manager11);
        binding.inc.rvSciFi.setAdapter(sciFiAdapter);

        ThrillerAdapter thrillerAdapter = new ThrillerAdapter(HomeActivity.this, StartActivity.Thrillerdata);
        RecyclerView.LayoutManager manager12 = new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false);
        binding.inc.rvThriller.setLayoutManager(manager12);
        binding.inc.rvThriller.setAdapter(thrillerAdapter);
    }

}