package com.kitap.demo.viewmodel;

public class KitapView {
    private long id;
    private String adi;
    private String yazari;
    private String turu;
    private String aciklama;


    public KitapView(long id, String adi, String yazari, String turu, String aciklama) {
        this.id = id;
        this.adi = adi;
        this.yazari = yazari;
        this.turu = turu;
        this.aciklama = aciklama;
    }

    public KitapView() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getYazari() {
        return yazari;
    }

    public void setYazari(String yazari) {
        this.yazari = yazari;
    }

    public String getTuru() {
        return turu;
    }

    public void setTuru(String turu) {
        this.turu = turu;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
}
