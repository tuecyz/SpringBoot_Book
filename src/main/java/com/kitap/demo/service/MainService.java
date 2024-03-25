package com.kitap.demo.service;

import com.kitap.demo.model.Kitap;
import com.kitap.demo.model.KitapTuru;
import com.kitap.demo.repository.KitapRepository;
import com.kitap.demo.repository.KitapTuruRepository;
import com.kitap.demo.viewmodel.KitapView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {

    final KitapRepository kitapRepository;
    final KitapTuruRepository kitapTuruRepository;

    public MainService(KitapRepository kitapRepository, KitapTuruRepository kitapTuruRepository) {
        this.kitapRepository = kitapRepository;
        this.kitapTuruRepository = kitapTuruRepository;
    }

    public List<Kitap> getMetodu() {
        return kitapRepository.findAll();
    }

    public List<KitapView> getKitaplarByKitapTuru(Long kitapTuruId) {
        List<KitapView> kitapViews = new ArrayList<>();
        List<Kitap> kitaps = kitapRepository.getKitapsByTuruId(kitapTuruId);
        for (Kitap kitap : kitaps) {
            KitapView kitapView = new KitapView();
            kitapView.setId(kitap.getId());
            kitapView.setAdi(kitap.getAdi());
            kitapView.setYazari(kitap.getYazari());

            KitapTuru kitapTuru = kitapTuruRepository.findById(kitap.getTuruId()).orElse(null);
            kitapView.setTuru(kitapTuru != null ? kitapTuru.getAciklama() : "Kitap türü tanımlanmamış");

            kitapViews.add(kitapView);
        }
        return kitapViews;
    }

    public KitapView postMetodu(KitapView kitapView) {
        Kitap newKitap = new Kitap();
        newKitap.setAdi(kitapView.getAdi());
        newKitap.setYazari(kitapView.getYazari());
        newKitap = kitapRepository.save(newKitap);
        kitapView.setId(newKitap.getId());
        return kitapView;
    }

  /*  public KitapView postkitapEkleByTuru(KitapView kitapView) {
        String kitapTuru = kitapView.getTuru();
        Long kitapTuruId = getKitapTuruIdByTur(kitapTuru);

        Kitap kitap = new Kitap();
        kitap.setAdi(kitapView.getAdi());
        kitap.setYazari(kitapView.getYazari());
        kitap.setTuruId(kitapTuruId);
        kitapRepository.save(kitap);
        return kitapView;
    }

    private Long getKitapTuruIdByTur(String kitapTur) {
        // Örnek bir yöntem: Kitap türlerine göre sabit bir eşleme yapılabilir
        if (kitapTur.equals("hikaye")) {
            return 1L;
        } else if (kitapTur.equals("roman")) {
            return 2L;
        } else if (kitapTur.equals("cocukss")) {
            return 3L;
        } else {
            return null;
        }
    } */

    public String putMetodu(Long id, KitapView kitapView) {
        Kitap kitap = kitapRepository.findById(id).orElse(null);
        if (kitap != null) {
            kitap.setAdi(kitapView.getAdi());
            kitap.setYazari(kitapView.getYazari());
            kitapRepository.save(kitap);
            return "OK";
        } else {
            return id + " id li bir kitap bulunamadı!!";
        }
    }


    public String delMetodu(Long id) {
        Kitap kitap = kitapRepository.findById(id).orElse(null);
        if (kitap != null) {
            kitapRepository.delete(kitap);
            return "OK";
        } else {
            return id + " id li bir kitap bulunamadı!!";
        }
    }

    public List<Kitap> tumKitaplar() {
        return kitapRepository.findAll();
    }

}
