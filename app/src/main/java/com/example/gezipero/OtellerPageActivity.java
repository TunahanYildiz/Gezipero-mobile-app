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
import android.widget.TextView;

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

public class OtellerPageActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private String search;
    private RecyclerView rv;
    private ArrayList<Oteller> kelimelerListe;
    private ArrayList<Oteller> otellerListe;
    private OtelAdapter adapter;
    private TextView otelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oteller_page);

        search = (String) getIntent().getSerializableExtra("aramaOtel");
        otelName = findViewById(R.id.otelName);
        otelName.setText(search);

        rv = findViewById(R.id.rvOtel);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

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
        String url = "https://www.imge.imgeweb.net/sozluk/tum_oteller.php";
        StringRequest istek = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("-----cevapppppp",response);

                otellerListe = new ArrayList<>();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray turListe = jsonObject.getJSONArray("otel");


                    for(int i = 0 ; i < turListe.length() ; i++ ){
                        JSONObject turListeJson = turListe.getJSONObject(i);

                        int otelId = turListeJson.getInt("otelid");
                        String otelad = turListeJson.getString("otelad");
                        String tipi = turListeJson.getString("tipi");
                        String resim = turListeJson.getString("resim");
                        String tarih = turListeJson.getString("tarih");
                        String fiyat = turListeJson.getString("fiyat");
                        String parabirimi = turListeJson.getString("parabirimi");
                        Oteller tur = new Oteller(
                                otelId,
                                otelad,
                                tipi,
                                resim,
                                tarih,
                                fiyat,
                                parabirimi);

                        otellerListe.add(tur);

                    }


                    adapter = new OtelAdapter(OtellerPageActivity.this,otellerListe);

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
                params.put("otelad",search);
                return params;

            }
        };
        Volley.newRequestQueue(this).add(istek);


    }

}