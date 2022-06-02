package com.example.gezipero;

import java.io.Serializable;

public class Oteller  implements Serializable {
    private int otelId;
    private String otelad;
    private String tipi;
    private String resim;
    private String tarih;
    private String fiyat;
    private String parabirimi;

    public Oteller() {
    }

    public Oteller(int otelId, String otelad, String tipi, String resim, String tarih, String fiyat, String parabirimi) {
        this.otelId = otelId;
        this.otelad = otelad;
        this.tipi = tipi;
        this.resim = resim;
        this.tarih = tarih;
        this.fiyat = fiyat;
        this.parabirimi = parabirimi;
    }

    public int getOtelId() {
        return otelId;
    }

    public void setOtelId(int otelId) {
        this.otelId = otelId;
    }

    public String getOtelad() {
        return otelad;
    }

    public void setOtelad(String otelad) {
        this.otelad = otelad;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
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

}
