package com.kitap.demo.controller;

import com.kitap.demo.service.KitapTuruService;
import com.kitap.demo.viewmodel.KitapTuruView;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitap-turu")
public class KitapTuruController {

    final KitapTuruService kitapTuruService;

    public KitapTuruController(KitapTuruService kitapTuruService) {
        this.kitapTuruService = kitapTuruService;
    }

    @GetMapping("/kitap-turu-getir")
    public List<KitapTuruView> getKitapTuru() {
        return kitapTuruService.getKitapTuru();
    }

    @PostMapping("/kitap-turu-kaydet")
    public KitapTuruView postKitapTuru(@RequestBody KitapTuruView kitapTuruView) throws BadRequestException {
        if (kitapTuruView == null){
            throw new BadRequestException("Kaydedilecek bir kitap turu bilgisi gönderilmelidir!");
        }

        if (kitapTuruView.getAciklama() == null || kitapTuruView.getAciklama().equals("")){
            throw new BadRequestException("Kaydedilecek bir kitap turu bilgisi gönderilmelidir!");
        }

        return kitapTuruService.postKitapTuru(kitapTuruView);
    }

    @PutMapping("/kitap-turu-duzelt")
    public String putKitapTuru(@RequestParam Long id, @RequestBody KitapTuruView kitapTuruView) throws BadRequestException {
        if (id == null){
            throw new BadRequestException("Düzeltilecek bir kitap turu id'si gönderilmelidir!");
        }

        if (kitapTuruView == null){
            throw new BadRequestException("Düzeltilecek bir kitap turu gönderilmelidir!");
        }

        return kitapTuruService.putKitapTuru(id, kitapTuruView);
    }

    @DeleteMapping("/kitap-turu-sil")
    public String delKitapTuru(@RequestParam Long id) throws BadRequestException {
        if ( id == null) {
            throw new BadRequestException("Silinecek bir kitap turu id'si gönderilmelidir!");
        }
        return kitapTuruService.delKitapTuru(id);
    }
}
