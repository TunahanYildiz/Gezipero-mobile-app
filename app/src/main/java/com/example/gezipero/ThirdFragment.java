package com.example.gezipero;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThirdFragment extends Fragment {
    private ConstraintLayout searchTurName;
    private TextView searchWordTur;
    private Button buttonTurAra;
    private RecyclerView rvTurAna;
    private ArrayList<Turlar> turlarListe;
    private TurlarAdapter adapter;
    private RecyclerView rvTurAnaG;
    private ArrayList<Turlar> turlarListeG;
    private CardView btnTurTakvimi, btnKonaklama, btnKaradeniz, btnTermal, btnGunubirlik;
    private ImageView imageTakvim,imageKonaklama,imageGunubirlik,imageKaradeniz,imageTermal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        btnTurTakvimi = view.findViewById(R.id.btnTurTakvimi);
        btnKonaklama = view.findViewById(R.id.btnKonaklama);
        btnGunubirlik = view.findViewById(R.id.btnGunubirlik);
        btnKaradeniz = view.findViewById(R.id.btnKaradeniz);
        btnTermal = view.findViewById(R.id.btnTermal);

        btnKonaklama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),TurlarPageActivity.class);
                intent.putExtra("arama","Konaklamalı Turlar");
                startActivity(intent);
            }
        });
        btnGunubirlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),TurlarPageActivity.class);
                intent.putExtra("arama","Günübirlik Turlar");
                startActivity(intent);
            }
        });
        btnKaradeniz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),TurlarPageActivity.class);
                intent.putExtra("arama","Karadeniz Turlar");
                startActivity(intent);
            }
        });

        searchWordTur = view.findViewById(R.id.searchWordTur);

        tumArama();

        searchTurName = view.findViewById(R.id.searchTurName);
        searchTurName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        buttonTurAra = view.findViewById(R.id.buttonTurAra);
        buttonTurAra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = (String) searchWordTur.getText();
                Intent intent = new Intent(getContext(),TurlarPageActivity.class);
                intent.putExtra("arama",search);
                startActivity(intent);
            }
        });

        //tur arama sayfasında kültür turlarını listelenen yer
        rvTurAna = view.findViewById(R.id.rvTurAna);

        rvTurAna.setHasFixedSize(true);
        rvTurAna.setLayoutManager(new LinearLayoutManager(getContext()));
        tumArama();

        //tur arama sayfasında günibirlik turlarını listelenen yer
        rvTurAnaG = view.findViewById(R.id.rvTurAnaG);

        rvTurAnaG.setHasFixedSize(true);
        rvTurAnaG.setLayoutManager(new LinearLayoutManager(getContext()));

        tumArama2();

        imageTakvim = (ImageView) view.findViewById(R.id.imageTakvim);
        imageKonaklama = (ImageView) view.findViewById(R.id.imageKonaklama);
        imageGunubirlik = (ImageView) view.findViewById(R.id.imageGunubirlik);
        imageKaradeniz = (ImageView) view.findViewById(R.id.imageKaradeniz);
        imageTermal = (ImageView) view.findViewById(R.id.imageTermal);

        Picasso.get().load("https://www.gezipero.com/files/img/hkat/takvim.webp").into(imageTakvim);
        Picasso.get().load("https://www.gezipero.com/files/img/hkat/kn.webp").into(imageKonaklama);
        Picasso.get().load("https://www.gezipero.com/files/img/hkat/gb.webp").into(imageGunubirlik);
        Picasso.get().load("https://www.gezipero.com/files/img/hkat/karadeniz.webp").into(imageKaradeniz);
        Picasso.get().load("https://www.gezipero.com/files/img/hkat/termal.webp").into(imageTermal);

        return view;
    }

    private void showDialog() {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout gunubirlikLayout = dialog.findViewById(R.id.layoutGunubirlik);
        LinearLayout layoutKonaklamali = dialog.findViewById(R.id.layoutKonaklamali);
        LinearLayout layoutGap = dialog.findViewById(R.id.layoutGap);
        LinearLayout layoutBalkan = dialog.findViewById(R.id.layoutBalkan);
        LinearLayout layoutAsya = dialog.findViewById(R.id.layoutAsya);
        LinearLayout layoutGemi = dialog.findViewById(R.id.layoutGemi);

        gunubirlikLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                searchWordTur.setText("Günübirlik Turlar");
            }
        });
        layoutKonaklamali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                searchWordTur.setText("Konaklamalı Turlar");
            }
        });
        layoutGap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                searchWordTur.setText("GAP Turları");
            }
        });
        layoutBalkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                searchWordTur.setText("Balkan Turları");
            }
        });
        layoutAsya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                searchWordTur.setText("Asya ve Uzak Doğu Turları");
            }
        });
        layoutGemi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                searchWordTur.setText("Gemi Turları");
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

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

                    adapter = new TurlarAdapter(getContext(),turlarListe);

                    rvTurAna.setAdapter(adapter);

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
                params.put("turkategoryad","Konaklamalı Turlar");
                return params;

            }
        };
        Volley.newRequestQueue(getContext()).add(istek);

    }

    public void tumArama2() {
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

                    adapter = new TurlarAdapter(getContext(),turlarListe);

                    rvTurAnaG.setAdapter(adapter);

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
                params.put("turkategoryad","Günübirlik Turlar");
                return params;

            }
        };
        Volley.newRequestQueue(getContext()).add(istek);

    }

}