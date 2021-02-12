package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.model.JacketEntity;
import com.galvanize.clothingstore.model.Season;
import com.galvanize.clothingstore.repository.JacketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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


    @Test
    public void searchWithAttributes(){
        JacketEntity jacket1=new JacketEntity(Season.FALL,"L","Blue","Slim",Boolean.TRUE,35L);
        JacketEntity jacket2=new JacketEntity(Season.FALL,"L","Blue","Slim",Boolean.TRUE,35L);
        JacketEntity jacket3=new JacketEntity(Season.WINTER,"L","Blue","Slim",Boolean.TRUE,35L);

        when(jacketRepository.findBySeasonAndSizeAndColorAndStyleAndAdultSize(Season.FALL,"L","Blue","Slim",true))
                .thenReturn(List.of(jacket1,jacket2));

        List<JacketEntity> jackets=jacketService.searchJackects(Season.FALL,"L","Blue","Slim",true);
        verify(jacketRepository,times(1)).findBySeasonAndSizeAndColorAndStyleAndAdultSize(Season.FALL,"L","Blue","Slim",true);

        assertEquals(2,jackets.size());
    }
}