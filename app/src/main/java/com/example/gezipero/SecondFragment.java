package com.example.gezipero;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SecondFragment extends Fragment {
    private TextView textSale,searchWordOtel,textViewKisiSayiOtel;
    private ConstraintLayout search_btn_hotel_name,search_btn_person;
    private ImageView imageAlanya, imageAbant, imageIstanbul, imageKarOtel, imageTermalOtel;
    private Button search_btn_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        searchWordOtel = view.findViewById(R.id.searchWordOtel);
        search_btn_hotel_name = view.findViewById(R.id.search_btn_hotel_name);
        textViewKisiSayiOtel = view.findViewById(R.id.textViewKisiSayiOtel);

        search_btn_hotel_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOtelName();
            }
        });
        search_btn_person = view.findViewById(R.id.search_btn_person);
        search_btn_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOtelSayi();
            }
        });

        search_btn_search = view.findViewById(R.id.search_btn_search);
        search_btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = (String) searchWordOtel.getText();
                Intent intent = new Intent(getContext(),OtellerPageActivity.class);
                intent.putExtra("aramaOtel",search);
                startActivity(intent);
            }
        });

        imageAlanya = (ImageView) view.findViewById(R.id.imageAlanya);
        imageAbant = (ImageView) view.findViewById(R.id.imageAbant);
        imageIstanbul = (ImageView) view.findViewById(R.id.imageIstanbul);
        imageKarOtel = (ImageView) view.findViewById(R.id.imageKarOtel);
        imageTermalOtel  = (ImageView) view.findViewById(R.id.imageTermalOtel);

        Picasso.get().load("https://www.gezipero.com/image/kategori/alanya-otelleri_5ff9814de73b8.jpg").into(imageAlanya);
        Picasso.get().load("https://www.gezipero.com/image/kategori/abant-otelleri_5ffa93c1d3bf1.jpg").into(imageAbant);
        Picasso.get().load("https://www.gezipero.com/image/kategori/sehir-otelleri-istanbul-otelleri_5ffcaf635f32d.jpg").into(imageIstanbul);
        Picasso.get().load("https://www.gezipero.com/image/kategori/side-otelleri_5ff981592e086.jpg").into(imageKarOtel);
        Picasso.get().load("https://www.gezipero.com/image/kategori/termal-oteller_5ffa943a7a2d8.jpg").into(imageTermalOtel);

        return view;
    }

    private void showOtelName() {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_search_hotel_name);

        ImageView imageCheck = dialog.findViewById(R.id.imageCheck);
        EditText editText = dialog.findViewById(R.id.editTextOtelName);
        imageCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String otel = String.valueOf(editText.getText());
                searchWordOtel.setText(otel);
            }
        });


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    private void showOtelSayi() {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_search_hotel_kisi_sayi);

        TextView textViewSayi = dialog.findViewById(R.id.textViewSayi);
        ImageView imageAdd = dialog.findViewById(R.id.imageAdd);
        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int yetiskinSayi = Integer.valueOf((String) textViewSayi.getText());
                yetiskinSayi = yetiskinSayi +1;
                if(yetiskinSayi==6 || yetiskinSayi>6){
                    yetiskinSayi = 6;
                }
                String a = String.valueOf(yetiskinSayi);
                textViewSayi.setText(a);
            }
        });
        ImageView imageRemove = dialog.findViewById(R.id.imageRemove);
        imageRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int yetiskinSayi = Integer.valueOf((String) textViewSayi.getText());
                yetiskinSayi = yetiskinSayi -1;
                if(yetiskinSayi==0 || yetiskinSayi<0){
                    yetiskinSayi = 1;
                }
                String a = String.valueOf(yetiskinSayi);
                textViewSayi.setText(a);
            }
        });

        ImageView imageAddCocuk = dialog.findViewById(R.id.imageAddCocuk);
        TextView textViewSayiCocuk = dialog.findViewById(R.id.textViewSayiCocuk);
        imageAddCocuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int yetiskinSayi = Integer.valueOf((String) textViewSayiCocuk.getText());
                yetiskinSayi = yetiskinSayi +1;
                if(yetiskinSayi==6 || yetiskinSayi>6){
                    yetiskinSayi = 6;
                }
                String a = String.valueOf(yetiskinSayi);
                textViewSayiCocuk.setText(a);
            }
        });
        ImageView imageRemoveCocuk = dialog.findViewById(R.id.imageRemoveCocuk);
        imageRemoveCocuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int yetiskinSayi = Integer.valueOf((String) textViewSayiCocuk.getText());
                yetiskinSayi = yetiskinSayi -1;
                if(yetiskinSayi<0){
                    yetiskinSayi = 0;
                }
                String a = String.valueOf(yetiskinSayi);
                textViewSayiCocuk.setText(a);
            }
        });
        Button otelKisiButton = dialog.findViewById(R.id.otelKisiButton);
        otelKisiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str;
                if(Integer.valueOf(String.valueOf(textViewSayiCocuk.getText())) == 0){
                    str =  textViewSayi.getText() + " Yetişkin";

                }else{
                    str =  textViewSayi.getText() + " Yetişkin, " + textViewSayiCocuk.getText() + " Çocuk";

                }
                textViewKisiSayiOtel.setText(str);
                dialog.dismiss();

            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}