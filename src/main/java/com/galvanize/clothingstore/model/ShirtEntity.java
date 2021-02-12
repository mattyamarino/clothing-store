package com.galvanize.clothingstore.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ShirtEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private ShirtType type;
    private Integer sleeve;
    private Integer neck;
    private String size;
    private String color;
    private Boolean longSleeve;
    private Long price;
}
