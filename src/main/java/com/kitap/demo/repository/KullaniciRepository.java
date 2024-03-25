package com.kitap.demo.repository;

import com.kitap.demo.model.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {

    List<Kullanici> findAllByUsernameAndPassword(String username, String password);
}
