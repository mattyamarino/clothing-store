package com.galvanize.clothingstore.controller;
import com.galvanize.clothingstore.model.JacketEntity;
import com.galvanize.clothingstore.service.JacketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products/")
public class JacketController {
    private JacketService jacketService;

    public JacketController(JacketService jacketService) {
        this.jacketService = jacketService;
    }

    @PutMapping("jacket/{productId}")
    public void updateJacket(@PathVariable Long productId,
                              @RequestBody JacketEntity objectToUpdate) {
        jacketService.updateProduct("jacket", productId, objectToUpdate);
    }
}


