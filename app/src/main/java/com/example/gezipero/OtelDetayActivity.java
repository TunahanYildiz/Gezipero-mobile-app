package com.example.gezipero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class OtelDetayActivity extends AppCompatActivity {
    private ImageView btnBack;
    private TextView textViewImgilizceDetay, textViewOtelTarih, otelIsmi, textViewOtelFiyat, textViewTurAdi;
    private Oteller tur;

    SliderView sliderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otel_detay);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        textViewTurAdi = findViewById(R.id.turName);
        textViewOtelTarih = findViewById(R.id.textViewOtelTarih);
        textViewOtelFiyat = findViewById(R.id.textViewTurFiyat);
        otelIsmi = findViewById(R.id.otelIsmi);

        tur = (Oteller) getIntent().getSerializableExtra("nesne");

        textViewTurAdi.setText(tur.getOtelad());
        textViewOtelFiyat.setText(tur.getFiyat() + " " + tur.getParabirimi());
        textViewOtelTarih.setText(tur.getTarih());
        otelIsmi.setText(tur.getOtelad());

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