package com.kitap.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="kitap",schema = "public")
public class Kitap {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kitap_id_seq")
    @SequenceGenerator(name = "kitap_id_seq", sequenceName = "kitap_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "adi")
    private String adi;

    @Column(name = "yazari")
    private String yazari;

    @Column(name = "turu_id")
    private long turuId;

    public Kitap() {
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

    public long getTuruId() {
        return turuId;
    }

    public void setTuruId(long turuId) {
        this.turuId = turuId;
    }
}
