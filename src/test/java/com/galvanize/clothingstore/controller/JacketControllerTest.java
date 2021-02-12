package com.galvanize.clothingstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.clothingstore.model.JacketEntity;
import com.galvanize.clothingstore.model.Season;
import com.galvanize.clothingstore.repository.JacketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")
class JacketControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JacketRepository jacketRepository;

    @Autowired
    MockMvc mockMvc;


    @Test
    public void searchJacketWithAttributes() throws Exception {
        //Season season, String size, String color, String style, Boolean adultSize, Long price
        JacketEntity jacket1=new JacketEntity(Season.FALL,"small","red","skinny",false,30L );
        JacketEntity jacket2=new JacketEntity(Season.WINTER,"large","black","regular",true,50L );
        JacketEntity jacket3=new JacketEntity(Season.SPRING,"medium","white","wide",true,70L );
        JacketEntity jacket4=new JacketEntity(Season.WINTER,"small","red","skinny",true,30L );
        JacketEntity jacket5=new JacketEntity(Season.WINTER,"large","black","regular",true,50L );
        JacketEntity jacket6=new JacketEntity(Season.WINTER,"large","black","regular",true,50L );

        jacketRepository.saveAll(List.of(jacket1,jacket2,jacket3,jacket4,jacket5,jacket6));

        //mockMvc.perform(get("/api/products/jackets?season="+&+"&size=" + size + "&color="+color+"&stye="+style))
        mockMvc.perform(get("/api/products/jackets?season=WINTER&size=large&color=black&style=&adultSize=true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].season").value("WINTER"))
                .andExpect(jsonPath("$[0].size").value("large"));
                //.andExpect(jsonPath("$[0].color").value("black"));

        mockMvc.perform(get("/api/products/jackets?season=FALL&size=small&color=red&style=skinny&adultSize=false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].season").value("FALL"))
                .andExpect(jsonPath("$[0].size").value("small"))
                .andExpect(jsonPath("$[0].color").value("red"))
                .andExpect(jsonPath("$[0].style").value("skinny"))
                .andExpect(jsonPath("$[0].adultSize").value("false"));



        //.andExpect(jsonPath("$[0].color").value("black"));


    }
}