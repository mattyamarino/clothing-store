package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.model.ShirtEntity;
import com.galvanize.clothingstore.model.ShirtType;
import com.galvanize.clothingstore.repository.ShirtRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.NotAcceptableStatusException;

import java.util.List;

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

    public ShirtEntity addShirt(ShirtEntity shirtEntity) {
        //only dress shirts can have sleeve and neck measurements.
        //Shirts must either have a size or they must have sleeve and neck measurements, but not both.
        if(shirtEntity.getType().equals(ShirtType.dress)) {

            if(shirtEntity.getSize()!=null && (shirtEntity.getSleeve()!=null && shirtEntity.getNeck()!=null)){
                throw new NotAcceptableStatusException("Shirts must either have a size or they must have sleeve and neck measurements, but not both");

            }
           else if(shirtEntity.getSize()!=null ||(shirtEntity.getSleeve()!=null && shirtEntity.getNeck()!=null)){
               return shirtRepository.save(shirtEntity);

            }

        }

        if(!shirtEntity.getType().equals(ShirtType.dress)) {
            if(shirtEntity.getSleeve()!=null && shirtEntity.getNeck()!=null){
                throw new NotAcceptableStatusException("Shirt must not have sleeve or neck measurements");
            }
        }
        return shirtRepository.save(shirtEntity);
    }

    public List<ShirtEntity> getAllShirts() {
        return shirtRepository.findAll();
    }

    public void deleteShirt(long id) {
        shirtRepository.deleteById(id);

    }
}
