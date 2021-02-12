package com.galvanize.clothingstore.repository;

import com.galvanize.clothingstore.model.JacketEntity;
import com.galvanize.clothingstore.model.ShirtEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShirtRepository extends JpaRepository<ShirtEntity, Long> {
    List<ShirtEntity> findByColor(String color);
}
