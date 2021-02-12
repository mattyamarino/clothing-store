package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.model.ShirtEntity;
import com.galvanize.clothingstore.model.ShirtType;
import com.galvanize.clothingstore.repository.ShirtRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class ShirtServiceTest {
    @Mock
    private ShirtRepository shirtRepository;

    @InjectMocks
    private ShirtService shirtService;

    @Test
    public void updateProduct_callsSaveOnShirtRepo() {
        ShirtEntity shirtEntity = new ShirtEntity();
        ShirtEntity shirtWithId =  new ShirtEntity();
        shirtWithId.setId(1L);
        when(shirtRepository.save(shirtWithId)).thenReturn(null);
        shirtService.updateShirt(1L, shirtEntity);
        verify(shirtRepository, times(1)).save(shirtWithId);
        verifyNoMoreInteractions(shirtRepository);
    }

    @Test
    public void addShirt(){
        ShirtEntity shirt=new ShirtEntity();
        shirt.setType(ShirtType.dress);
        shirt.setSleeve(20);
        shirt.setNeck(25);
        shirt.setColor("blue");
        shirt.setLongSleeve(true);

        when(shirtRepository.save(shirt)).thenReturn(shirt);
        ShirtEntity actual=shirtService.addShirt(shirt);
        verify(shirtRepository,times(1)).save(shirt);

        assertEquals(shirt,actual);

    }

}