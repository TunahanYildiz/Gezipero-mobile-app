package com.example.gezipero;

import java.io.Serializable;

public class Turlar implements Serializable  {
    private int turId;
    private String turad;
    private String turprogram;
    private String resim;
    private String sure;
    private String tarih;
    private String fiyat;
    private String parabirimi;
    private String turkategory;

    public Turlar() {
    }

    public Turlar(String turad) {
        this.turad = turad;
    }

    public Turlar(int turId, String turad, String turprogram, String resim, String sure, String tarih, String fiyat, String parabirimi, String turkategory) {
        this.turId = turId;
        this.turad = turad;
        this.turprogram = turprogram;
        this.resim = resim;
        this.sure = sure;
        this.tarih = tarih;
        this.fiyat = fiyat;
        this.parabirimi = parabirimi;
        this.turkategory = turkategory;
    }

    public int getTurId() {
        return turId;
    }

    public void setTurId(int turId) {
        this.turId = turId;
    }

    public String getTurad() {
        return turad;
    }

    public void setTurad(String turad) {
        this.turad = turad;
    }

    public String getTurprogram() {
        return turprogram;
    }

    public void setTurprogram(String turprogram) {
        this.turprogram = turprogram;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public String getSure() {
        return sure;
    }

    public void setSure(String sure) {
        this.sure = sure;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public String getParabirimi() {
        return parabirimi;
    }

    public void setParabirimi(String parabirimi) {
        this.parabirimi = parabirimi;
    }

    public String getTurkategory() {
        return turkategory;
    }

    public void setTurkategory(String turkategory) {
        this.turkategory = turkategory;
    }

}
