package com.galvanize.clothingstore.repository;

import com.galvanize.clothingstore.model.ShoeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoeRepository extends JpaRepository<ShoeEntity, Long> {
}
