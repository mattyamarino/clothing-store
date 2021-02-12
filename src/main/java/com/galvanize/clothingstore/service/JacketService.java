package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.model.JacketEntity;
import com.galvanize.clothingstore.model.Season;
import com.galvanize.clothingstore.repository.JacketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JacketService {

    private JacketRepository jacketRepository;

    public JacketService (JacketRepository jacketRepository){
        this.jacketRepository=jacketRepository;
    }

    public List<JacketEntity> searchJackects(Season season) {
        return jacketRepository.findBySeason(season);
    }

    public List<JacketEntity> searchJackects(Season season, String size, String color, String style, Boolean adultSize) {
       if(season!=null && !size.isEmpty() && !color.isEmpty() && !style.isEmpty() && adultSize!=null){
            return  jacketRepository.findBySeasonAndSizeAndColorAndStyleAndAdultSize(season,size,color,style,adultSize);
       }
       else if(season!=null && !size.isEmpty() && !color.isEmpty() && !style.isEmpty() ){
           return jacketRepository.findBySeasonAndSizeAndColorAndStyle(season,size,color,style);
       }

       else if(season!=null && !size.isEmpty() && !color.isEmpty()){
           return jacketRepository.findBySeasonAndSizeAndColor(season,size,color);
       }

       else if(season!=null && !size.isEmpty()){
           return jacketRepository.findBySeasonAndSize(season,size);
       }


       return jacketRepository.findBySeason(season);

    }
}
