package com.kitap.demo.service;

import com.kitap.demo.model.Kullanici;
import com.kitap.demo.repository.KullaniciRepository;
import com.kitap.demo.viewmodel.KullaniciView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KullaniciService {

    final KullaniciRepository kullaniciRepository;

    public KullaniciService(KullaniciRepository kullaniciRepository) {
        this.kullaniciRepository = kullaniciRepository;
    }

    public KullaniciView login(String username, String password) {
        List<Kullanici> lstKull = kullaniciRepository.findAllByUsernameAndPassword(username, password);
        if (lstKull.size() > 0){
            KullaniciView kullaniciView = new KullaniciView();
            kullaniciView.setId(lstKull.get(0).getId());
            kullaniciView.setFirstname(lstKull.get(0).getFirstname());
            kullaniciView.setSurname(lstKull.get(0).getSurname());
            kullaniciView.setUsername(lstKull.get(0).getUsername());
            return kullaniciView;
        } else {
            return null;
        }
    }
}
