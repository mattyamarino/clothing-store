package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.model.ShirtEntity;
import com.galvanize.clothingstore.model.ShirtType;
import com.galvanize.clothingstore.repository.ShirtRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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

    @Test
    public void getAllShirts() {
        ShirtEntity shirt1 = new ShirtEntity(ShirtType.dress, 12, 10,null,
                "Orange", true, 9000L);

        ShirtEntity shirt2 = new ShirtEntity(ShirtType.polo, null, null, "L",
                "Blue", true, 9000L);

        ShirtEntity shirt3 = new ShirtEntity(ShirtType.henley, null, null, "S",
                "Red", true, 9000L);

       // when(shirtRepository.saveAll(List.of(shirt1,shirt2,shirt3))).thenReturn(List.of(shirt1,shirt2,shirt3));
        when(shirtRepository.findAll()).thenReturn(List.of(shirt1,shirt2,shirt3));
        List<ShirtEntity> actual = shirtService.getAllShirts();
        verify(shirtRepository,times(1) ).findAll();
        assertEquals(3, actual.size());
        assertEquals(List.of(shirt1,shirt2,shirt3), actual);
    }



}