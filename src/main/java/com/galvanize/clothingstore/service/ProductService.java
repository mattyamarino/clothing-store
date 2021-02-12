package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.model.Products;
import com.galvanize.clothingstore.repository.JacketRepository;
import com.galvanize.clothingstore.repository.ShirtRepository;
import com.galvanize.clothingstore.repository.ShoeRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private JacketRepository jacketRepository;
    private ShirtRepository shirtRepository;
    private ShoeRepository shoeRepository;

    public ProductService(JacketRepository jacketRepository, ShirtRepository shirtRepository, ShoeRepository shoeRepository) {
        this.jacketRepository = jacketRepository;
        this.shirtRepository = shirtRepository;
        this.shoeRepository = shoeRepository;
    }

    public Products getProductsByColor(String color) {
        return Products.builder()
                .jackets(jacketRepository.findByColor(color))
                .shirts(shirtRepository.findByColor(color))
                .shoes(shoeRepository.findByColor(color))
                .build();
    }
}
