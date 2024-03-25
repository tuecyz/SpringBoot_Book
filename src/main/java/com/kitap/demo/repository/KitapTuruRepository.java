package com.kitap.demo.repository;

import com.kitap.demo.model.KitapTuru;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitapTuruRepository extends JpaRepository<KitapTuru, Long> {
}
