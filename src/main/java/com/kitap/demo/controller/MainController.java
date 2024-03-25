package com.kitap.demo.controller;

import com.kitap.demo.model.Kitap;
import com.kitap.demo.service.MainService;
import com.kitap.demo.viewmodel.KitapView;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/kitaplari-getir")
    public List<Kitap> getMetodu() {
        return mainService.getMetodu();
    }

    @GetMapping("/kitaplari-getir-kitap-turu")
    public List<KitapView> getKitaplarByKitapTuru(@RequestParam Long kitapTuru) {
        return mainService.getKitaplarByKitapTuru(kitapTuru);
    }

    @PostMapping("/kitap-kaydet")
    public KitapView postMetodu(@RequestBody KitapView kitapView) throws BadRequestException {
        if (kitapView == null){
            throw new BadRequestException("Kaydedilecek bir kitap bilgisi gönderilmelidir!");
        }

        if (kitapView.getAdi() == null || kitapView.getAdi().equals("")){
            throw new BadRequestException("Kaydedilecek bir kitap bilgisi gönderilmelidir!");
        }

        return mainService.postMetodu(kitapView);
    }

    @PutMapping("/kitap-bilgisi-duzelt")
    public String putMetodu(@RequestParam Long id, @RequestBody KitapView kitapView) throws BadRequestException {
        if (id == null || id <= 0) {
            throw new BadRequestException("Geçersiz kitap ID'si controller!");
        }
        if (id == null){
            throw new BadRequestException("Düzeltilecek bir kitap id'si gönderilmelidir!");
        }

        if (kitapView == null){
            throw new BadRequestException("Düzeltilecek bir kitap bilgisi gönderilmelidir!");
        }

        return mainService.putMetodu(id, kitapView);
    }

    @DeleteMapping("/kitap-sil")
    public String delMetodu(@RequestParam Long id) throws BadRequestException {
        if ( id == null) {
            throw new BadRequestException("Silinecek bir kitap id'si gönderilmelidir!");
        }
        return mainService.delMetodu(id);
    }
}
