package com.galvanize.clothingstore.repository;

import com.galvanize.clothingstore.model.ShirtEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShirtRepository extends JpaRepository<ShirtEntity, Long> {
}
