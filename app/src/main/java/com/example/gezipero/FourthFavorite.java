package com.example.gezipero;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gezipero.databinding.FragmentFourthFavoriteBinding;


public class FourthFavorite extends Fragment {

    FragmentFourthFavoriteBinding bF;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       bF = FragmentFourthFavoriteBinding.inflate(getLayoutInflater());

       return bF.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bF = null;
    }
}