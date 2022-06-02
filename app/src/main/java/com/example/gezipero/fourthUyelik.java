package com.example.gezipero;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gezipero.databinding.FragmentFourthUyelikBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.concurrent.Executor;


public class fourthUyelik extends Fragment {

    String adtxt,soyAdtxt,mailtxt,sifre1txt,sifre2txt,numaratxt;

    FragmentFourthUyelikBinding bu;

    FirebaseAuth myAuth;
    FirebaseFirestore db;
    DatabaseReference myRef;
    FirebaseUser myUser;
    HashMap<String,Object> myData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();
        db = FirebaseFirestore.getInstance();

        bu = FragmentFourthUyelikBinding.inflate(getLayoutInflater());

        return bu.getRoot();
    }

    @Override
    public void onViewCreated (@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        bu.uyelikUyeOlCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adtxt = bu.uyelikIsim.getText().toString();
                soyAdtxt = bu.uyelikSoyisim.getText().toString();
                mailtxt = bu.uyelikMail.getText().toString();
                sifre1txt = bu.uyelikSifre1.getText().toString();
                sifre2txt = bu.uyelikSifre2.getText().toString();
                numaratxt = bu.uyelikNumara.getText().toString();

                if(TextUtils.isEmpty(adtxt)){
                    Toast.makeText(getActivity(), "Ad alanı boş bırakılamaz!", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(soyAdtxt)){
                    Toast.makeText(getActivity(), "Soy ad alanı boş bırakılamaz!", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(mailtxt)){
                    Toast.makeText(getActivity(), "Mail alanı boş bırakılamaz!", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(sifre1txt)&&TextUtils.isEmpty(sifre2txt)){
                    Toast.makeText(getActivity(), "Şifre alanı boş bırakılamaz!", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(numaratxt)){
                    Toast.makeText(getActivity(), "Telefon numarası alanı boş bırakılamaz!", Toast.LENGTH_SHORT).show();
                }
                else if(!TextUtils.equals(sifre1txt,sifre2txt)){
                    Toast.makeText(getActivity(), "Şifreleriniz uyuşmuyor!", Toast.LENGTH_SHORT).show();
                }
                else{
                    myAuth.createUserWithEmailAndPassword(mailtxt,sifre1txt)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    myUser = FirebaseAuth.getInstance().getCurrentUser();
                                    myData = new HashMap<>();
                                    myData.put("Kullanıcı Adı",adtxt);
                                    myData.put("Kullanıcı Soy Adı",soyAdtxt);
                                    myData.put("Kullanıcı Mail",mailtxt);
                                    myData.put("Kullanıcı Şifre",sifre1txt);
                                    myData.put("Kullanıcı Numara",numaratxt);
                                    db.collection("Kullanıcılar").document(myUser.getUid()).set(myData)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Intent intent = new Intent(getActivity(),MainActivity.class);
                                                    startActivity(intent);

                                                    Toast.makeText(getActivity(), "Üyelik Başarılı", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            })
                            .addOnFailureListener(getActivity(), new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), "Böyle bir hesap zaten mevcut!", Toast.LENGTH_SHORT).show();
                                }
                            });
                }

            }
        });

    }

    @Override
    public void onDestroyView (){
        super.onDestroyView();

        bu = null;
    }





}