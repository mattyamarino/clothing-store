package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.model.JacketEntity;
import com.galvanize.clothingstore.model.Season;
import com.galvanize.clothingstore.repository.JacketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JacketServiceTest {
    @Mock
    private JacketRepository jacketRepository;

    @InjectMocks
    private JacketService jacketService;

    @Test
    public void updateProduct_callsSaveOnJacketRepo() {
        JacketEntity jacketEntity = new JacketEntity();
        JacketEntity jacketWithId = new JacketEntity();
        jacketWithId.setId(1L);
        when(jacketRepository.save(jacketWithId)).thenReturn(null);
        jacketService.updateProduct("jacket", 1L, jacketEntity);
        verify(jacketRepository, times(1)).save(jacketWithId);
        verifyNoMoreInteractions(jacketRepository);
    }

    @Test
    public void addProduct_callsSaveOnJacketRepo(){
        JacketEntity jacket=new JacketEntity(Season.FALL,"L","Blue","Slim",Boolean.TRUE,35L);
        when(jacketRepository.save(jacket)).thenReturn(jacket);
        JacketEntity result=jacketService.addJacket(jacket);
        verify(jacketRepository,times(1)).save(jacket);
        assertEquals(jacket,result);
    }
}