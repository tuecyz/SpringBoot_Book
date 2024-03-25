package com.kitap.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name="kitap_turu",schema = "public")
public class KitapTuru {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kitap_turu_id_seq")
    @SequenceGenerator(name = "kitap_turu_id_seq", sequenceName = "kitap_turu_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "aciklama")
    private String aciklama;

    public KitapTuru() {
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
