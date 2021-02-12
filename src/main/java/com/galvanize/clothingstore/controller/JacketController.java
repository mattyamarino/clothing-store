package com.galvanize.clothingstore.controller;
import com.galvanize.clothingstore.model.JacketEntity;
import com.galvanize.clothingstore.model.Season;
import com.galvanize.clothingstore.service.JacketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/")
public class JacketController {
    private JacketService jacketService;

    public JacketController(JacketService jacketService){
        this.jacketService=jacketService;
    }

    @GetMapping("/jackets")
    @ResponseStatus(HttpStatus.OK)
    public List<JacketEntity> searchJackets(@RequestParam Season season, @RequestParam String size,
                                            @RequestParam String color, @RequestParam String style,
                                            @RequestParam Boolean adultSize ){
        List<JacketEntity> result=jacketService.searchJackects(season,size,color,style,adultSize);
        result.forEach(System.out::println);
        return result;
    }


    @PutMapping("jacket/{productId}")
    public void updateJacket(@PathVariable Long productId,
                              @RequestBody JacketEntity objectToUpdate) {
        jacketService.updateProduct("jacket", productId, objectToUpdate);
    }
}


