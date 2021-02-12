package com.galvanize.clothingstore.repository;

import com.galvanize.clothingstore.model.JacketEntity;
import com.galvanize.clothingstore.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JacketRepository extends JpaRepository<JacketEntity, Long> {
    List<JacketEntity> findBySeason(Season season);

    List<JacketEntity> findBySeasonAndColor(Season season, String color);

    List<JacketEntity> findBySeasonAndSizeAndColorAndStyleAndAdultSize(Season season, String size, String color, String style, Boolean adultSize);

    List<JacketEntity> findBySeasonAndSizeAndColorAndStyle(Season season, String size, String color, String style);

    List<JacketEntity> findBySeasonAndSizeAndColor(Season season, String size, String color);

    List<JacketEntity> findBySeasonAndSize(Season season, String size);
}
