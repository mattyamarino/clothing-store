package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.model.JacketEntity;
import com.galvanize.clothingstore.repository.JacketRepository;
import org.springframework.stereotype.Service;

@Service
public class JacketService {

    private JacketRepository jacketRepository;

    public JacketService(JacketRepository jacketRepository) {
        this.jacketRepository = jacketRepository;
    }

    public void updateJacket(long productId, JacketEntity jacketToUpdate) {
            jacketToUpdate.setId(productId);
            jacketRepository.save(jacketToUpdate);
    }

    public JacketEntity addJacket(JacketEntity jacket) {
        return jacketRepository.save(jacket);
    }
}
