package com.example.gezipero;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gezipero.databinding.FragmentFourthGirisBinding;
import com.example.gezipero.databinding.FragmentFourthProfilBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class FourthFragment extends Fragment {

    FragmentFourthGirisBinding bg;
    FragmentFourthProfilBinding bp;
    FirebaseFirestore db;
    DatabaseReference myRef;
    FirebaseAuth myAuth;
    FirebaseUser myUser;
    DocumentReference myDoc;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();
        db = FirebaseFirestore.getInstance();



        if(myAuth.getCurrentUser() == null) {
            bg = FragmentFourthGirisBinding.inflate(getLayoutInflater());
            return bg.getRoot();
        }
        else if (myAuth.getCurrentUser() != null) {
            bp = FragmentFourthProfilBinding.inflate(getLayoutInflater());
            return bp.getRoot();
        }

            return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if(myAuth.getCurrentUser() == null) {

            bg.ffgCvGiris.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String mail,sifre;

                    mail = bg.ffgGirisMail.getText().toString();
                    sifre = bg.ffgGirisSifre.getText().toString();
                    if (TextUtils.isEmpty(mail)){
                        Toast.makeText(getActivity(), "Mail boş bırakılmaz!", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        if(TextUtils.isEmpty(sifre)){
                            Toast.makeText(getActivity(), "Şifre boş bırakılmaz!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            myAuth.signInWithEmailAndPassword(mail, sifre)
                                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {

                                            Intent intent = new Intent(getActivity(), MainActivity.class);
                                            startActivity(intent);


                                            Toast.makeText(getActivity(), "Giriş Başarılı!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }


                }
            });

            bg.ffgCvUyeOl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Fragment fragment = new fourthUyelik();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
            });
        }
        else if (myAuth.getCurrentUser() != null) {
            myUser = FirebaseAuth.getInstance().getCurrentUser();

            String uid = myUser.getUid();

            myDoc = db.collection("Kullanıcılar").document(uid);

            myDoc.get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                  if(documentSnapshot.exists()){

                                      String ad = documentSnapshot.getString("Kullanıcı Adı");

                                      bp.ffpTextView.setText("Hoş Geldin "+ad+".");

                                  }

                                  else{

                                      Toast.makeText(getActivity(), "Böyle bir döküman bulunamadı!", Toast.LENGTH_SHORT).show();

                                  }

                        }
                    });

            bp.ffpHesabimCv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new FourthHesabim();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
            bp.ffpFavorilerimCv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new FourthFavorite();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
            bp.ffpRezervasyonCv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new FourthReservation();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bg = null;
        bp = null;
    }

}
