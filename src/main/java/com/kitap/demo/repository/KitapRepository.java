package com.kitap.demo.repository;

import com.kitap.demo.model.Kitap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KitapRepository extends JpaRepository<Kitap, Long> {

    List<Kitap> getKitapsByTuruId(Long id);

}
