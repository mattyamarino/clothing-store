package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.model.ShirtEntity;
import com.galvanize.clothingstore.repository.ShirtRepository;
import org.springframework.stereotype.Service;

@Service
public class ShirtService {

    private ShirtRepository shirtRepository;

    public ShirtService(ShirtRepository shirtRepository) {
        this.shirtRepository = shirtRepository;
    }

    public void updateShirt(Long shirtId, ShirtEntity shirtToUpdate) {
        shirtToUpdate.setId(shirtId);
        shirtRepository.save(shirtToUpdate);
    }
}
