package com.galvanize.clothingstore.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.clothingstore.model.JacketEntity;
import com.galvanize.clothingstore.model.Season;
import com.galvanize.clothingstore.model.ShirtEntity;
import com.galvanize.clothingstore.model.ShirtType;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
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
        JacketEntity jacket1 = new JacketEntity(Season.FALL, "small", "red", "skinny", false, 30L);
        JacketEntity jacket2 = new JacketEntity(Season.WINTER, "large", "black", "regular", true, 50L);
        JacketEntity jacket3 = new JacketEntity(Season.SPRING, "medium", "white", "wide", true, 70L);
        JacketEntity jacket4 = new JacketEntity(Season.WINTER, "small", "red", "skinny", true, 30L);
        JacketEntity jacket5 = new JacketEntity(Season.WINTER, "large", "black", "regular", true, 50L);
        JacketEntity jacket6 = new JacketEntity(Season.WINTER, "large", "black", "regular", true, 50L);

        jacketRepository.saveAll(List.of(jacket1, jacket2, jacket3, jacket4, jacket5, jacket6));

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
    }      //.andExpect(jsonPath("$[0].color").value("black"));



    @Test
    public void updateJacket_updatesJacket() throws Exception {
        JacketEntity jacketEntity = jacketRepository.save(new JacketEntity(Season.SPRING, "M",
                "Blue", "Awesome", true, 10L));

        JacketEntity jacketToUpdate = new JacketEntity(Season.SUMMER, "L",
                "Blue", "Awesome", true, 10L);

        objectMapper = new ObjectMapper();
        String jacketString = objectMapper.writeValueAsString(jacketToUpdate);

        mockMvc.perform(put("/api/products/jacket/" + jacketEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacketString))
                .andExpect(status().isOk());

        jacketToUpdate.setId(jacketEntity.getId());
        JacketEntity result = jacketRepository.findById(jacketEntity.getId()).get();
        assertEquals(jacketToUpdate, result);
    }



    @Test
    public void addShirt() throws Exception {

        ShirtEntity shirt=new ShirtEntity();
        shirt.setType(ShirtType.dress);
        shirt.setSleeve(20);
        shirt.setNeck(25);
        shirt.setColor("blue");
        shirt.setLongSleeve(true);

        mockMvc.perform(post("/api/products/shirts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(shirt)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type").value("dress"))
                .andExpect(jsonPath("$.sleeve").value(20))
                .andExpect(jsonPath("$.neck").value(25))
                .andExpect(jsonPath("$.color").value("blue"))
                .andExpect(jsonPath("$.longSleeve").value(true));

    }

    @Test
    public void addShirtDressWithSizeSleeveAndNeckThrowsError() throws Exception {

        ShirtEntity shirt=new ShirtEntity();
        shirt.setType(ShirtType.dress);
        shirt.setSleeve(20);
        shirt.setNeck(25);
        shirt.setSize("medium");
        shirt.setColor("blue");
        shirt.setLongSleeve(true);

        mockMvc.perform(post("/api/products/shirts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(shirt)))
                .andExpect(status().isNotAcceptable());
    }


}