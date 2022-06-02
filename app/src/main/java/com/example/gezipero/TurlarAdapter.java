package com.example.gezipero;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class TurlarAdapter extends RecyclerView.Adapter<TurlarAdapter.CardTasarimTutucu> {

    private Context mContext;
    private List<Turlar> kelimelersListe;

    public TurlarAdapter(Context mContext, List<Turlar> kelimelersListe) {
        this.mContext = mContext;
        this.kelimelersListe = kelimelersListe;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim,parent,false);
        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        Turlar tur = kelimelersListe.get(position);
        Picasso.get().load(tur.getResim()).into(holder.turImage);
        holder.textViewIngilizce.setText(tur.getTurad());
        holder.textViewTurkce.setText(tur.getFiyat() + " " +tur.getParabirimi());
        holder.textViewTarih.setText(tur.getTarih());

        holder.kelimeKard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext,TurDetayActivity.class);
                intent.putExtra("nesne",tur);
                mContext.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return kelimelersListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private ImageView turImage;
        private TextView textViewIngilizce;
        private TextView textViewTarih;
        private TextView textViewTurkce;
        private CardView kelimeKard;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);
            turImage = itemView.findViewById(R.id.turImage);
            textViewIngilizce = itemView.findViewById(R.id.textViewTurAdi);
            textViewTurkce = itemView.findViewById(R.id.textViewTurFiyat);
            textViewTarih = itemView.findViewById(R.id.textViewTarih);
            kelimeKard = itemView.findViewById(R.id.turKard);
        }
    }

}
