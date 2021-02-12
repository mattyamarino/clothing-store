package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.model.ShoeEntity;
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
    public void deleteShoe() {
        shoeService.deleteShoe(1L);
        verify(shoeRepository,times(1)).deleteById(1L);
        verifyNoMoreInteractions(shoeRepository);
    }
}