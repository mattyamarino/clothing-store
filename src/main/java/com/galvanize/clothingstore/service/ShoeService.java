package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.model.ShoeEntity;
import com.galvanize.clothingstore.repository.ShoeRepository;
import org.springframework.stereotype.Service;

@Service
public class ShoeService {
    private ShoeRepository shoeRepository;

    public ShoeService(ShoeRepository shoeRepository) {
        this.shoeRepository = shoeRepository;
    }

    public void updateShoe(Long shoeId, ShoeEntity shoeToUpdate) {
        shoeToUpdate.setId(shoeId);
        shoeRepository.save(shoeToUpdate);
    }

    public void deleteShoe(long id) {
        shoeRepository.deleteById(id);
    }

    public ShoeEntity addShoe(ShoeEntity shoe) {
        return shoeRepository.save(shoe);
    }

}
