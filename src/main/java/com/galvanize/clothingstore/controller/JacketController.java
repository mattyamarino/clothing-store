package com.galvanize.clothingstore.controller;
import com.galvanize.clothingstore.model.JacketEntity;
import com.galvanize.clothingstore.model.Season;
import com.galvanize.clothingstore.model.ShirtEntity;
import com.galvanize.clothingstore.service.JacketService;
import com.galvanize.clothingstore.service.ShirtService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/")
public class JacketController {
    private JacketService jacketService;
    private ShirtService shirtService;

    public JacketController(JacketService jacketService, ShirtService shirtService){
        this.jacketService=jacketService;
        this.shirtService=shirtService;
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
        jacketService.updateJacket(productId, objectToUpdate);
    }


    @PostMapping("/shirts")
    @ResponseStatus(HttpStatus.CREATED)
    public ShirtEntity addShirt(@RequestBody ShirtEntity shirtEntity){
        return shirtService.addShirt(shirtEntity);
    }


}


