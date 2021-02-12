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

}