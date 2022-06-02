package com.example.gezipero;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TurlarPageActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private String search;
    private Toolbar toolbar;
    private RecyclerView rv;
    private ArrayList<Turlar> turlarListe;
    private TurlarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turlar_page);

        search = (String) getIntent().getSerializableExtra("arama");
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(search);
        setSupportActionBar(toolbar);

        rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        tumArama();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbarmenu,menu);

        MenuItem item = menu.findItem(R.id.action_back);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("gönderilen arama",query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("harf girdikce arama",newText);

        return false;
    }

    public void tumArama() {
        String url = "https://www.imge.imgeweb.net/sozluk/tum_turlar_arama.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("cevap",response);

                turlarListe = new ArrayList<>();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray turListe = jsonObject.getJSONArray("turlar");


                    for(int i = 0 ; i < turListe.length() ; i++ ){
                        JSONObject turListeJson = turListe.getJSONObject(i);

                        int turId = turListeJson.getInt("tur_id");
                        String turAd = turListeJson.getString("turad");
                        String turProgram = turListeJson.getString("turprogram");
                        String resim = turListeJson.getString("resim");
                        String resim2 = turListeJson.getString("resim2");
                        String sliderresim = turListeJson.getString("sliderresim");
                        String sure = turListeJson.getString("sure");
                        String tarih = turListeJson.getString("tarih");
                        String fiyat = turListeJson.getString("fiyat");
                        String parabirimi = turListeJson.getString("parabirimi");
                        String turkategoryad = turListeJson.getString("turkategoryad");


                        Turlar tur = new Turlar(
                                turId,
                                turAd,
                                turProgram,
                                resim,
                                sure,
                                tarih,
                                fiyat,
                                parabirimi,
                                turkategoryad);
                        turlarListe.add(tur);

                    }

                    adapter = new TurlarAdapter(TurlarPageActivity.this,turlarListe);

                    rv.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Erorr",String.valueOf(error));
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("turkategoryad",search);
                return params;

            }
        };
        Volley.newRequestQueue(this).add(istek);

    }


}