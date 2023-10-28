package com.zorotv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zorotv.Adapter.ActionAdapter;
import com.zorotv.Adapter.AdventureAdapter;
import com.zorotv.Adapter.ComedyAdapter;
import com.zorotv.Adapter.DramaAdapter;
import com.zorotv.Adapter.FantasyAdapter;
import com.zorotv.Adapter.HistoricalAdapter;
import com.zorotv.Adapter.HorrorAdapter;
import com.zorotv.Adapter.MysteryAdapter;
import com.zorotv.Adapter.RomanceAdapter;
import com.zorotv.Adapter.SciFiAdapter;
import com.zorotv.Adapter.ThrillerAdapter;
import com.zorotv.Adapter.TopRatedAdapter;
import com.zorotv.Ads.AdmCommon;
import com.zorotv.Ads.AdsCallBack;
import com.zorotv.Ads.Ads_Preference;
import com.zorotv.DataModel.MovieData;
import com.zorotv.databinding.ActivityStartBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {
    ActivityStartBinding b;
    Ads_Preference adsDataPrefs;
    ArrayList arrayList;
    public static ArrayList<MovieData> Top_Rateddata = new ArrayList<>();
    public static ArrayList<MovieData> Actiondata = new ArrayList<>();
    public static ArrayList<MovieData> Adventuredata = new ArrayList<>();
    public static ArrayList<MovieData> Comedydata = new ArrayList<>();
    public static ArrayList<MovieData> Dramadata = new ArrayList<>();
    public static ArrayList<MovieData> Fantasydata = new ArrayList<>();
    public static ArrayList<MovieData> Historicaldata = new ArrayList<>();
    public static ArrayList<MovieData> Horrordata = new ArrayList<>();
    public static ArrayList<MovieData> Mysterydata = new ArrayList<>();
    public static ArrayList<MovieData> Romancedata = new ArrayList<>();
    public static ArrayList<MovieData> Sci_Fidata = new ArrayList<>();
    public static ArrayList<MovieData> Thrillerdata = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.appbar));

        AdmCommon.getInstance().AmNativeLoad(this,b.llNativeBig, true);
        adsDataPrefs = new Ads_Preference(this);

        b.tvStart.setOnClickListener(view -> {

            AdmCommon.getInstance().mInterstitialAdBackPressClickCount++;
            AdmCommon.getInstance().loadOrShowAdmInterstial(false,this, new AdsCallBack() {
                @Override
                public void onAdsClose() {
                    startActivity(new Intent(StartActivity.this , NameActivity.class));
                }
                @Override
                public void onLoading() {
                    startActivity(new Intent(StartActivity.this , NameActivity.class));
                }
            });

        });

        getData();
        
    }

    private void getData() {
        JSONObject jSONObject = new JSONObject();

        Volley.newRequestQueue(getApplicationContext()).add(new JsonObjectRequest(1, "https://moviescraft.com/Anime-TV/All-data.txt", jSONObject, new Response.Listener<JSONObject>() {
            @SuppressLint("WrongConstant")
            public void onResponse(JSONObject jSONObject) {

                try {
                    for(int i=1 ; i<=jSONObject.getJSONObject("Top_Rated").length() ; i++){
                        arrayList = (ArrayList) new Gson().fromJson(jSONObject.getJSONObject("Top_Rated").getJSONArray(""+i).toString(), new TypeToken<ArrayList<MovieData>>() {
                        }.getType());

                        Top_Rateddata.addAll(arrayList);

//                        Log.d("ASAAS", "onResponse: movie list = " + Top_Rateddata.get(i-1).getName());

                    }

                    for(int i=1 ; i<=jSONObject.getJSONObject("Action").length() ; i++){
                        arrayList = (ArrayList) new Gson().fromJson(jSONObject.getJSONObject("Action").getJSONArray(""+i).toString(), new TypeToken<ArrayList<MovieData>>() {
                        }.getType());

                        Actiondata.addAll(arrayList);

//                        Log.d("ASAAS", "onResponse: ActionData = " + Actiondata.get(i-1).getName());

                    }

                    for(int i=1 ; i<=jSONObject.getJSONObject("Adventure").length() ; i++){
                        arrayList = (ArrayList) new Gson().fromJson(jSONObject.getJSONObject("Adventure").getJSONArray(""+i).toString(), new TypeToken<ArrayList<MovieData>>() {
                        }.getType());

                        Adventuredata.addAll(arrayList);

//                        Log.d("ASAAS", "onResponse: movie list = " + Adventuredata.get(i-1).getName());

                    }

                    for(int i=1 ; i<=jSONObject.getJSONObject("Comedy").length() ; i++){
                        arrayList = (ArrayList) new Gson().fromJson(jSONObject.getJSONObject("Comedy").getJSONArray(""+i).toString(), new TypeToken<ArrayList<MovieData>>() {
                        }.getType());

                        Comedydata.addAll(arrayList);

//                        Log.d("ASAAS", "onResponse: movie list = " + Comedydata.get(i-1).getName());

                    }

                    for(int i=1 ; i<=jSONObject.getJSONObject("Drama").length() ; i++){
                        arrayList = (ArrayList) new Gson().fromJson(jSONObject.getJSONObject("Drama").getJSONArray(""+i).toString(), new TypeToken<ArrayList<MovieData>>() {
                        }.getType());

                        Dramadata.addAll(arrayList);

//                        Log.d("ASAAS", "onResponse: movie list = " + Dramadata.get(i-1).getName());

                    }

                    for(int i=1 ; i<=jSONObject.getJSONObject("Fantasy").length() ; i++){
                        arrayList = (ArrayList) new Gson().fromJson(jSONObject.getJSONObject("Fantasy").getJSONArray(""+i).toString(), new TypeToken<ArrayList<MovieData>>() {
                        }.getType());

                        Fantasydata.addAll(arrayList);

//                        Log.d("ASAAS", "onResponse: movie list = " + Fantasydata.get(i-1).getName());

                    }

                    for(int i=1 ; i<=jSONObject.getJSONObject("Historical").length() ; i++){
                        arrayList = (ArrayList) new Gson().fromJson(jSONObject.getJSONObject("Historical").getJSONArray(""+i).toString(), new TypeToken<ArrayList<MovieData>>() {
                        }.getType());

                        Historicaldata.addAll(arrayList);

//                        Log.d("ASAAS", "onResponse: movie list = " + Historicaldata.get(i-1).getName());

                    }

                    for(int i=1 ; i<=jSONObject.getJSONObject("Horror").length() ; i++){
                        arrayList = (ArrayList) new Gson().fromJson(jSONObject.getJSONObject("Horror").getJSONArray(""+i).toString(), new TypeToken<ArrayList<MovieData>>() {
                        }.getType());

                        Horrordata.addAll(arrayList);

//                        Log.d("ASAAS", "onResponse: movie list = " + Horrordata.get(i-1).getName());

                    }

                    for(int i=1 ; i<=jSONObject.getJSONObject("Mystery").length() ; i++){
                        arrayList = (ArrayList) new Gson().fromJson(jSONObject.getJSONObject("Mystery").getJSONArray(""+i).toString(), new TypeToken<ArrayList<MovieData>>() {
                        }.getType());

                        Mysterydata.addAll(arrayList);

//                        Log.d("ASAAS", "onResponse: movie list = " + Mysterydata.get(i-1).getName());

                    }

                    for(int i=1 ; i<=jSONObject.getJSONObject("Romance").length() ; i++){
                        arrayList = (ArrayList) new Gson().fromJson(jSONObject.getJSONObject("Romance").getJSONArray(""+i).toString(), new TypeToken<ArrayList<MovieData>>() {
                        }.getType());

                        Romancedata.addAll(arrayList);

//                        Log.d("ASAAS", "onResponse: movie list = " + Romancedata.get(i-1).getName());

                    }

                    for(int i=1 ; i<=jSONObject.getJSONObject("Sci-Fi").length() ; i++){
                        arrayList = (ArrayList) new Gson().fromJson(jSONObject.getJSONObject("Sci-Fi").getJSONArray(""+i).toString(), new TypeToken<ArrayList<MovieData>>() {
                        }.getType());

                        Sci_Fidata.addAll(arrayList);

//                        Log.d("ASAAS", "onResponse: movie list = " + Sci_Fidata.get(i-1).getName());

                    }

                    for(int i=1 ; i<=jSONObject.getJSONObject("Thriller").length() ; i++){
                        arrayList = (ArrayList) new Gson().fromJson(jSONObject.getJSONObject("Thriller").getJSONArray(""+i).toString(), new TypeToken<ArrayList<MovieData>>() {
                        }.getType());

                        Thrillerdata.addAll(arrayList);

//                        Log.d("ASAAS", "onResponse: movie list = " + Thrillerdata.get(i-1).getName());

                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));
    }
}