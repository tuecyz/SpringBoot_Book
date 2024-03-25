package com.kitap.demo.viewmodel;

public class KitapTuruView {
    private long id;
    private String aciklama;

    public KitapTuruView() {
    }

    public KitapTuruView(long id, String aciklama) {
        this.id = id;
        this.aciklama = aciklama;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAciklama() {
        return aciklama;
    }
    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
}
