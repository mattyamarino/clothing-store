package com.galvanize.clothingstore.model;

import javax.persistence.*;

@Entity
public class JacketEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private Season season;
    private String size;
    private String color;
    private String style;
    private Boolean adultSize;
    private Long price;


    public JacketEntity(Season season, String size, String color, String style, Boolean adultSize, Long price) {
        this.season = season;
        this.size = size;
        this.color = color;
        this.style = style;
        this.adultSize = adultSize;
        this.price = price;
    }

    public JacketEntity() {

    }

    public Season getSeason() {
        return season;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getStyle() {
        return style;
    }

    public Boolean getAdultSize() {
        return adultSize;
    }

    public Long getPrice() {
        return price;
    }
}
