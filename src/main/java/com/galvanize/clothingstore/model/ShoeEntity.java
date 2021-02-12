package com.galvanize.clothingstore.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ShoeEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Integer size;
    @Enumerated(EnumType.STRING)
    private ShoeType type;
    private String material;
    private String brand;
    private String color;
    private Long price;

    public ShoeEntity() {
    }

    public ShoeEntity(Integer size, ShoeType type, String material, String brand, String color, Long price) {
        this.size = size;
        this.type = type;
        this.material = material;
        this.brand = brand;
        this.color = color;
        this.price = price;
    }
}
