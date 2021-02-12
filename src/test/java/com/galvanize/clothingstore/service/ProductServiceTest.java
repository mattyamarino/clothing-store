package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.model.*;
import com.galvanize.clothingstore.repository.JacketRepository;
import com.galvanize.clothingstore.repository.ShirtRepository;
import com.galvanize.clothingstore.repository.ShoeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    JacketRepository jacketRepository;

    @Mock
    ShirtRepository shirtRepository;

    @Mock
    ShoeRepository shoeRepository;

    @InjectMocks
    ProductService service;

    @Test
    public void getProductsByColor_returnsListOfProductsMatchingColor() {
        JacketEntity jacket=new JacketEntity(Season.FALL,"L","Blue","Slim",true,35L);
        jacket.setId(1L);
        JacketEntity jacket1=new JacketEntity(Season.FALL,"L","Blue","Fat",false,35L);
        jacket.setId(2L);
        List<JacketEntity> jacketEntities = List.of(jacket, jacket1);

        ShirtEntity shirtEntity = new ShirtEntity(ShirtType.dress, 5, 10, "L",
                "Blue", true, 9900L);
        shirtEntity.setId(1L);
        ShirtEntity shirtEntity1 = new ShirtEntity(ShirtType.dress, 6, 7, "M",
                "Blue", true, 9900L);
        shirtEntity1.setId(2L);
        List<ShirtEntity> shirtEntities = List.of(shirtEntity, shirtEntity1);

        ShoeEntity shoeEntity = new ShoeEntity(11, ShoeType.boot, "leather", "Nike",
                "Blue", 100L);
        shoeEntity.setId(1L);
        List<ShoeEntity> shoeEntities = List.of(shoeEntity);

        Products expected =  Products.builder()
                .jackets(jacketEntities)
                .shirts(shirtEntities)
                .shoes(shoeEntities)
                .build();

        when(jacketRepository.findByColor("Blue")).thenReturn(jacketEntities);
        when(shirtRepository.findByColor("Blue")).thenReturn(shirtEntities);
        when(shoeRepository.findByColor("Blue")).thenReturn(shoeEntities);

        Products result =  service.getProductsByColor("Blue");

        assertEquals(expected, result);
        verify(jacketRepository, times(1)).findByColor("Blue");
        verify(shirtRepository, times(1)).findByColor("Blue");
        verify(shoeRepository, times(1)).findByColor("Blue");

        verifyNoMoreInteractions(jacketRepository);
        verifyNoMoreInteractions(shirtRepository);
        verifyNoMoreInteractions(shoeRepository);
    }

}