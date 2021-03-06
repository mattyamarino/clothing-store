package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.model.JacketEntity;
import com.galvanize.clothingstore.model.Season;
import com.galvanize.clothingstore.model.ShirtEntity;
import com.galvanize.clothingstore.model.ShoeEntity;
import com.galvanize.clothingstore.repository.JacketRepository;
import com.galvanize.clothingstore.repository.ShirtRepository;
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
class JacketServiceTest {
    @Mock
    private JacketRepository jacketRepository;

    @InjectMocks
    private JacketService jacketService;

    @Test
    public void updateJacket_callsSaveOnJacketRepo() {
        JacketEntity jacketEntity = new JacketEntity();
        JacketEntity jacketWithId = new JacketEntity();
        jacketWithId.setId(1L);
        when(jacketRepository.save(jacketWithId)).thenReturn(null);
        jacketService.updateJacket(1L, jacketEntity);
        verify(jacketRepository, times(1)).save(jacketWithId);
        verifyNoMoreInteractions(jacketRepository);
    }

    @Test
    public void deleteJacket() {
        jacketService.deleteJacket(1L);
        verify(jacketRepository,times(1)).deleteById(1L);
        verifyNoMoreInteractions(jacketRepository);
    }



    @Test
    public void addJacket_callsSaveOnJacketRepo(){
        JacketEntity jacket=new JacketEntity(Season.FALL,"L","Blue","Slim",true,35L);
        when(jacketRepository.save(jacket)).thenReturn(jacket);
        JacketEntity result=jacketService.addJacket(jacket);
        verify(jacketRepository,times(1)).save(jacket);
        assertEquals(jacket,result);
    }
    @Test
    public void getAllJackets(){
        JacketEntity jacket1=new JacketEntity(Season.FALL,"L","Blue","Slim",true,35L);
        JacketEntity jacket2=new JacketEntity(Season.WINTER,"M","Black","Skinny",false,50L);
        JacketEntity jacket3=new JacketEntity(Season.SPRING,"S","White","Slim",true,30L);
        List<JacketEntity> jackets=new ArrayList<>();
        jackets.add(jacket1);
        jackets.add(jacket2);
        jackets.add(jacket3);

        jacketRepository.save(jacket1);
        jacketRepository.save(jacket2);
        jacketRepository.save(jacket3);

        when(jacketRepository.findAll()).thenReturn(jackets);
        List<JacketEntity> result=jacketService.getAllJackets();
        verify(jacketRepository,times(1)).findAll();
        assertEquals(jackets,result);


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