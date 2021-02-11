package com.galvanize.clothingstore.repository;

import com.galvanize.clothingstore.model.JacketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JacketRepository extends JpaRepository<JacketEntity, Long> {
}
