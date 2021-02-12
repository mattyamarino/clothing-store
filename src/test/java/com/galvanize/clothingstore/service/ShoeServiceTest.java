package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.model.JacketEntity;
import com.galvanize.clothingstore.model.Season;
import com.galvanize.clothingstore.model.ShoeEntity;
import com.galvanize.clothingstore.model.ShoeType;
import com.galvanize.clothingstore.repository.ShoeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShoeServiceTest {
    @Mock
    private ShoeRepository shoeRepository;

    @InjectMocks
    private ShoeService shoeService;

    @Test
    public void updateShoe_callsSaveOnShoeRepo() {
        ShoeEntity shoeEntity = new ShoeEntity();
        ShoeEntity shoeWithId = new ShoeEntity();
        shoeWithId.setId(1L);
        when(shoeRepository.save(shoeWithId)).thenReturn(null);
        shoeService.updateShoe(1L, shoeEntity);
        verify(shoeRepository, times(1)).save(shoeWithId);
        verifyNoMoreInteractions(shoeRepository);
    }
    @Test
    public void addShoe_callsSaveOnShoeRepo(){
        ShoeEntity shoe=new ShoeEntity(20, ShoeType.boot,"Leather","Nike","Black", 200L);
        when(shoeRepository.save(shoe)).thenReturn(shoe);
        ShoeEntity result=shoeService.addShoe(shoe);
        verify(shoeRepository,times(1)).save(shoe);
        assertEquals(shoe,result);
    }

    @Test
    public void getAllShoes(){
        ShoeEntity shoe1=new ShoeEntity(20, ShoeType.boot,"Leather","Nike","Black", 200L);
        ShoeEntity shoe2=new ShoeEntity(15, ShoeType.sandal,"Leather","Reebok","Black", 100L);
        ShoeEntity shoe3=new ShoeEntity(42, ShoeType.athletic,"Leather","Puma","White", 150L);

        List<ShoeEntity> shoes=new ArrayList<>();
        shoes.add(shoe1);
        shoes.add(shoe2);
        shoes.add(shoe3);

        shoeRepository.save(shoe1);
        shoeRepository.save(shoe2);
        shoeRepository.save(shoe3);

        when(shoeRepository.findAll()).thenReturn(shoes);
        List<ShoeEntity> result=shoeService.getAllShoes();
        verify(shoeRepository,times(1)).findAll();
        assertEquals(shoes,result);


    }


}