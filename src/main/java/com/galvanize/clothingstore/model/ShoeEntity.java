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
}
