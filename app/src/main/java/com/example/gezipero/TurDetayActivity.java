package com.example.gezipero;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TurDetayActivity extends AppCompatActivity {
    private ImageView btnBack;
    private TextView textViewTurProgram,textViewTurFiyat,textViewTurAdi,textViewTurTarih;
    private Turlar tur;
    private ArrayList<Turlar> turlarListe;
    private TurlarAdapter adapter;
    private Button btnFavorite,buttonSenDeKatil;

    SliderView sliderView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tur_detay);
        btnFavorite = findViewById(R.id.imageFavorite);
        buttonSenDeKatil = findViewById(R.id.buttonSenDeKatil);
        buttonSenDeKatil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TurDetayActivity.this,RezrvasyonBilgilerActivity.class);
                startActivity(intent);
            }
        });
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("mert","favoriler ekleme tuşuna basıldı");
            }
        });

        textViewTurProgram = findViewById(R.id.textViewTurProgram);
        textViewTurTarih = findViewById(R.id.textViewTurTarih);
        textViewTurAdi = findViewById(R.id.turAramaName);
        textViewTurFiyat = findViewById(R.id.textViewTurFiyat);

        tur = (Turlar) getIntent().getSerializableExtra("nesne");

        textViewTurAdi.setText(tur.getTurad());
        textViewTurFiyat.setText(tur.getFiyat());
        textViewTurProgram.setText(tur.getTurprogram());
        textViewTurTarih.setText(tur.getTarih());
        textViewTurFiyat.setText(tur.getFiyat() +" " +tur.getParabirimi());

        sliderView = findViewById(R.id.image_slider);

        String[] images = {
                tur.getResim()
        };


        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

    }

}