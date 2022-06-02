package com.example.gezipero;

import android.content.Context;
import android.content.Intent;
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

public class OtelAdapter extends RecyclerView.Adapter<OtelAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Oteller> kelimelersListe;

    public OtelAdapter(Context mContext, List<Oteller> kelimelersListe) {
        this.mContext = mContext;
        this.kelimelersListe = kelimelersListe;
    }

    @NonNull
    @Override
    public OtelAdapter.CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim_otel,parent,false);
        return new OtelAdapter.CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OtelAdapter.CardTasarimTutucu holder, int position) {
        Oteller otel = kelimelersListe.get(position);
        Picasso.get().load(otel.getResim()).into(holder.imageOtel1);
        holder.textViewIngilizce.setText(otel.getOtelad());
        holder.textViewTurkce.setText(otel.getFiyat());

        holder.kelimeKard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext,OtelDetayActivity.class);
                intent.putExtra("nesne",otel);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return kelimelersListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private TextView textViewIngilizce;
        private TextView textViewTurkce;
        private CardView kelimeKard;
        private ImageView imageOtel1;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);
            textViewIngilizce = itemView.findViewById(R.id.textViewTurAdi);
            imageOtel1 = itemView.findViewById(R.id.imageOtel1);
            textViewTurkce = itemView.findViewById(R.id.textViewTurFiyat);
            kelimeKard = itemView.findViewById(R.id.turKard);


        }
    }
}
