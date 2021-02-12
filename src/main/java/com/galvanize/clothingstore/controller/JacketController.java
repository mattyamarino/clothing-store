package com.galvanize.clothingstore.controller;
import com.galvanize.clothingstore.model.*;
import com.galvanize.clothingstore.service.*;
import com.galvanize.clothingstore.service.JacketService;
import com.galvanize.clothingstore.service.ShirtService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class JacketController {
    private JacketService jacketService;
    private ShirtService shirtService;
    private ShoeService shoeService;
    private ProductService productService;

    public JacketController(JacketService jacketService, ShirtService shirtService, ShoeService shoeService,
                            ProductService productService) {
        this.jacketService = jacketService;
        this.shirtService = shirtService;
        this.shoeService = shoeService;
        this.productService = productService;
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

    @PutMapping("/jacket/{productId}")
    public void updateJacket(@PathVariable Long productId,
                              @RequestBody JacketEntity jacketToUpdate) {
        jacketService.updateJacket(productId, jacketToUpdate);
    }

    @PutMapping("/shirt/{productId}")
    public void updateShirt(@PathVariable Long productId,
                              @RequestBody ShirtEntity shirtToUpdate) {
        shirtService.updateShirt(productId, shirtToUpdate);
    }

    @PutMapping("/shoe/{productId}")
    public void updateShoe(@PathVariable Long productId,
                              @RequestBody ShoeEntity shoeToUpdate) {
        shoeService.updateShoe(productId, shoeToUpdate);
    }

    @PostMapping("/jacket")
    @ResponseStatus(HttpStatus.CREATED)
    public JacketEntity addJacket(@RequestBody JacketEntity jacket){
        return jacketService.addJacket(jacket);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<JacketEntity>getJackets(){
        return jacketService.getAllJackets();
    }

    @PostMapping("/shirts")
    @ResponseStatus(HttpStatus.CREATED)
    public ShirtEntity addShirt(@RequestBody ShirtEntity shirtEntity){
        return shirtService.addShirt(shirtEntity);
    }

    @PostMapping("/shoe")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoeEntity addShoe(@RequestBody ShoeEntity shoe){
        return shoeService.addShoe(shoe);
    }

    @DeleteMapping("/jacket/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJacket(@PathVariable Long productId) {
        jacketService.deleteJacket(productId);
    }

    @GetMapping("/shirts")
    public List<ShirtEntity> getAllShirts() {
        return shirtService.getAllShirts();
    }

    @DeleteMapping("/shirt/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShirt(@PathVariable Long productId) {
        shirtService.deleteShirt(productId);
    }

    @DeleteMapping("/shoe/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShoe(@PathVariable Long productId) {
        shoeService.deleteShoe(productId);
    }
    @GetMapping("/shoes")
    @ResponseStatus(HttpStatus.OK)
    public List<ShoeEntity>getShoes(){
        return shoeService.getAllShoes();
    }


    @GetMapping
    public Products getProductsByColor(@RequestParam String color) {
        return productService.getProductsByColor(color);
    }
}


