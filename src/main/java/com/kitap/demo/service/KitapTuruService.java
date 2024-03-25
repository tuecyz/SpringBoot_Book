package com.kitap.demo.service;

import com.kitap.demo.model.KitapTuru;
import com.kitap.demo.repository.KitapTuruRepository;
import com.kitap.demo.viewmodel.KitapTuruView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KitapTuruService {

    final KitapTuruRepository kitapTuruRepository;

    public KitapTuruService(KitapTuruRepository kitapTuruRepository) {
        this.kitapTuruRepository = kitapTuruRepository;
    }

    public List<KitapTuruView> getKitapTuru() {
        List<KitapTuruView> kitapTuruViews = new ArrayList<>();
        List<KitapTuru> kitapTurus = kitapTuruRepository.findAll();
        for(KitapTuru kitapTuru : kitapTurus){
            /* uzun yöntem
            KitapTuruView kitapTuruView = new KitapTuruView();
            kitapTuruView.setId(kitapTuru.getId());
            kitapTuruView.setAciklama(kitapTuru.getAciklama());
            kitapTuruViews.add(kitapTuruView);
            */
            kitapTuruViews.add(new KitapTuruView(kitapTuru.getId(),kitapTuru.getAciklama()));
        }
        return kitapTuruViews;
    }

    public KitapTuruView postKitapTuru(KitapTuruView kitapTuruView) {
        KitapTuru newKitapTuru = new KitapTuru();
        newKitapTuru.setAciklama(kitapTuruView.getAciklama());
        newKitapTuru = kitapTuruRepository.save(newKitapTuru);
        kitapTuruView.setId(newKitapTuru.getId());
        return kitapTuruView;
    }

    public String putKitapTuru(Long id, KitapTuruView kitapTuruView) {
        KitapTuru kitapTuru = kitapTuruRepository.findById(id).orElse(null);
        if (kitapTuru != null) {
            kitapTuru.setAciklama(kitapTuruView.getAciklama());
            kitapTuruRepository.save(kitapTuru);
            return "OK";
        } else {
            return id + " id li bir kitap turu bulunamadı!!";
        }
    }

    public String delKitapTuru(Long id) {
        KitapTuru kitapTuru = kitapTuruRepository.findById(id).orElse(null);
        if (kitapTuru != null) {
            kitapTuruRepository.delete(kitapTuru);
            return "OK";
        } else {
            return id + " id li bir kitap turu bulunamadı!!";
        }
    }
    public List<KitapTuru> tumKitapTurleri() {
        return kitapTuruRepository.findAll();
    }

}
