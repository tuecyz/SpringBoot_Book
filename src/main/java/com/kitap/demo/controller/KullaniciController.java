package com.kitap.demo.controller;

import com.kitap.demo.service.KullaniciService;
import com.kitap.demo.viewmodel.KullaniciView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class KullaniciController {

    final KullaniciService kullaniciService;

    public KullaniciController(KullaniciService kullaniciService) {
        this.kullaniciService = kullaniciService;
    }

    @GetMapping("/login")
    public KullaniciView login(@RequestParam String username, @RequestParam String password) {
        return kullaniciService.login(username, password);
    }

}
