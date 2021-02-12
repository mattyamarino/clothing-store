package com.galvanize.clothingstore.repository;

import com.galvanize.clothingstore.model.JacketEntity;
import com.galvanize.clothingstore.model.ShoeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoeRepository extends JpaRepository<ShoeEntity, Long> {
    List<ShoeEntity> findByColor(String color);
}
