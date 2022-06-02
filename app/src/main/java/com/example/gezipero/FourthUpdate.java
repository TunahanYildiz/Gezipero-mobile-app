package com.example.gezipero;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gezipero.databinding.FragmentFourthUpdateBinding;
import com.example.gezipero.databinding.FragmentFourthUyelikBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class FourthUpdate extends Fragment {

    String adtxt,soyAdtxt,sifretxt,numaratxt;

    FragmentFourthUpdateBinding bU;

    FirebaseAuth myAuth;
    FirebaseFirestore db;
    DatabaseReference myRef;
    FirebaseUser myUser;
    HashMap<String,Object> myData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bU = FragmentFourthUpdateBinding.inflate(getLayoutInflater());

        myAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();
        db = FirebaseFirestore.getInstance();

        return bU.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bU.updateBilgileriguncelleCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adtxt = bU.updateAdEt.getText().toString();
                soyAdtxt = bU.updateSoyadEt.getText().toString();
                sifretxt = bU.updateSifreEt.getText().toString();
                numaratxt = bU.updateNumaraEt.getText().toString();
                myUser = myAuth.getCurrentUser();


                if ((!TextUtils.isEmpty(adtxt)&&!TextUtils.isEmpty(soyAdtxt))||(!TextUtils.isEmpty(adtxt)&&!TextUtils.isEmpty(sifretxt))||(!TextUtils.isEmpty(adtxt)&&!TextUtils.isEmpty(numaratxt))
                        ||(!TextUtils.isEmpty(soyAdtxt)&&!TextUtils.isEmpty(sifretxt))||(!TextUtils.isEmpty(soyAdtxt)&&!TextUtils.isEmpty(numaratxt))
                        ||(!TextUtils.isEmpty(sifretxt)&&!TextUtils.isEmpty(numaratxt))){
                    Toast.makeText(getActivity(), "Aynı anda sadece bir değiştirme işlemi yapılabilir!", Toast.LENGTH_SHORT).show();
                }
                else if (!TextUtils.isEmpty(adtxt)){
                    myData = new HashMap<>();
                    myData.put("Kullanıcı Adı",adtxt);
                    db.collection("Kullanıcılar").document(myUser.getUid()).update(myData)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(getActivity(), "İsim değiştirme başarılı!", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else if (!TextUtils.isEmpty(soyAdtxt)){
                    myData= new HashMap<>();
                    myData.put("Kullanıcı Soy Adı",soyAdtxt);
                    db.collection("Kullanıcılar").document(myUser.getUid()).update(myData)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(getActivity(), "Soy Ad değiştirme başarılı!", Toast.LENGTH_SHORT).show();
                                }
                            });
                }

                else if (!TextUtils.isEmpty(sifretxt)){
                    myData = new HashMap<>();
                    myData.put("Kullanıcı Şifre",sifretxt);
                    db.collection("Kullanıcılar").document(myUser.getUid()).update(myData)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(getActivity(), "Şifre değiştirme başarılı!", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else if (!TextUtils.isEmpty(numaratxt)){
                    myData = new HashMap<>();
                    myData.put("Kullanıcı Numara",numaratxt);
                    db.collection("Kullanıcılar").document(myUser.getUid()).update(myData)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(getActivity(), "Numara değiştirme başarılı!", Toast.LENGTH_SHORT).show();
                                }
                            });
                }


            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bU = null;
    }
}