package com.example.gezipero;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.gezipero.databinding.FragmentFourthHesabimBinding;
import com.example.gezipero.databinding.FragmentFourthUpdateBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class FourthHesabim extends Fragment {

    FragmentFourthHesabimBinding bh;
    FirebaseFirestore db;
    DatabaseReference myRef;
    FirebaseAuth myAuth;
    FirebaseUser myUser;
    DocumentReference myDoc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bh = FragmentFourthHesabimBinding.inflate(getLayoutInflater());
        myAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();
        db = FirebaseFirestore.getInstance();

        return bh.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myUser = myAuth.getCurrentUser();
        myDoc = db.collection("Kullanıcılar").document(myUser.getUid());

        myDoc.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        String ad = "Ad/Soy Ad: "+documentSnapshot.getString("Kullanıcı Adı")+" "+ documentSnapshot.getString("Kullanıcı Soy Adı");
                        String mail = "e-Mail: "+documentSnapshot.getString("Kullanıcı Mail");
                        String numara = "Tlfn No: "+"0"+documentSnapshot.getString("Kullanıcı Numara");

                        bh.hesabimAdsoyadTv.setText(ad);
                        bh.hesabimMailTv.setText(mail);
                        bh.hesabimTelnoTv.setText(numara);
                    }
                });

        bh.hesabimBilgileriguncelleCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FourthUpdate();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });




        bh.hesabimCikisCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAuth.signOut();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);


                Toast.makeText(getActivity(), "Çıkış Başarılı!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bh = null;

    }
}