package com.example.gezipero;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

public class SearchAreaFragment extends Fragment {
    private Button search_btn_search;
    private ConstraintLayout search_btn_hotel_name;
    private ConstraintLayout search_btn_date;
    private ConstraintLayout search_btn_person;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_search_area,
                container, false);
        Button button_search = (Button) view.findViewById(R.id.search_btn_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("error", "mesaaje");
            }
        });
        ConstraintLayout buttonHotelName = view.findViewById(R.id.search_btn_hotel_name);
        buttonHotelName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("error","hotel name araması");
            }
        });
        ConstraintLayout buttonDate = view.findViewById(R.id.search_btn_date);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("error","DAte araması");
            }
        });


        return view;
    }
}