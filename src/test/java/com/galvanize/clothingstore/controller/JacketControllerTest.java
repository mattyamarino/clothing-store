package com.galvanize.clothingstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.clothingstore.model.JacketEntity;
import com.galvanize.clothingstore.model.Season;
import com.galvanize.clothingstore.model.ShirtEntity;
import com.galvanize.clothingstore.model.ShirtType;
import com.galvanize.clothingstore.model.*;
import com.galvanize.clothingstore.repository.JacketRepository;
import com.galvanize.clothingstore.repository.ShirtRepository;
import com.galvanize.clothingstore.repository.ShoeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")
@Transactional
class JacketControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JacketRepository jacketRepository;

    @Autowired
    ShirtRepository shirtRepository;

    @Autowired
    ShoeRepository shoeRepository;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

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
    public void updateJacket() throws Exception {
        JacketEntity jacketEntity = jacketRepository.save(new JacketEntity(Season.SPRING, "M",
                "Blue", "Awesome", true, 10L));

        JacketEntity jacketToUpdate = new JacketEntity(Season.SUMMER, "L",
                "Blue", "Awesome", true, 10L);

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
    public void deleteJacket() throws Exception {
        JacketEntity jacketEntity = jacketRepository.save(new JacketEntity(Season.SPRING, "M",
                "Blue", "Awesome", true, 10L));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/jacket/" + jacketEntity.getId()))
                .andExpect(status().isNoContent());

        List<JacketEntity> result = jacketRepository.findAll();

        assertEquals(0,result.size());
    }

    @Test
    public void deleteShirt() throws Exception {
        ShirtEntity shirtEntity = shirtRepository.save((new ShirtEntity(ShirtType.dress, 5, 10, "L",
                "Orange", true, 9900L)));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/shirt/" + shirtEntity.getId()))
                .andExpect(status().isNoContent());

        List<ShirtEntity> result = shirtRepository.findAll();

        assertEquals(0,result.size());
    }

    @Test
    public void deleteShoe() throws Exception {
        ShoeEntity shoeEntity = shoeRepository.save(new ShoeEntity(11, ShoeType.boot, "leather", "Nike",
                "periwinkle", 100L));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/shoe/" + shoeEntity.getId()))
                .andExpect(status().isNoContent());

        List<ShoeEntity> result = shoeRepository.findAll();

        assertEquals(0,result.size());
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


    @Test
    public void updateShirt() throws Exception {
        ShirtEntity shirtEntity = shirtRepository.save((new ShirtEntity(ShirtType.dress, 5, 10, "L",
                "Orange", true, 9900L)));

        Long shirtId = shirtEntity.getId();

        ShirtEntity shirtToUpdate = new ShirtEntity(ShirtType.dress, 12, 10, "M",
                "Orange", true, 9000L);

        String shirtString = objectMapper.writeValueAsString(shirtToUpdate);

        mockMvc.perform(put("/api/products/shirt/" + shirtId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(shirtString))
                .andExpect(status().isOk());

        shirtToUpdate.setId(shirtId);
        ShirtEntity result = shirtRepository.findById(shirtId).get();
        assertEquals(shirtToUpdate, result);
    }

    @Test
    public void updateShoe() throws Exception {
        ShoeEntity shoeEntity = shoeRepository.save(new ShoeEntity(11, ShoeType.boot, "leather", "Nike",
                "periwinkle", 100L));

        Long shoeId = shoeEntity.getId();

        ShoeEntity shoeToUpdate = new ShoeEntity(10, ShoeType.boot, "leather", "Nike",
                "white", 10000L);

        String shoeString = objectMapper.writeValueAsString(shoeToUpdate);

        mockMvc.perform(put("/api/products/shoe/" + shoeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(shoeString))
                .andExpect(status().isOk());

        shoeToUpdate.setId(shoeId);
        ShoeEntity result = shoeRepository.findById(shoeId).get();
        assertEquals(shoeToUpdate, result);
    }


    @Test
    public void addJacket_addsJacket() throws Exception {
        JacketEntity jacket=new JacketEntity(Season.FALL,"L","Blue","Slim",true,35L);
        String jacketString = objectMapper.writeValueAsString(jacket);
        mockMvc.perform(post("/api/products/jacket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacketString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.season").value("FALL"))
                .andExpect(jsonPath("$.size").value("L"))
                .andExpect(jsonPath("$.color").value("Blue"))
                .andExpect(jsonPath("$.style").value("Slim"))
                .andExpect(jsonPath("$.adultSize").value("true"))
                .andExpect(jsonPath("$.price").value("35"));
    }

    @Test
    public void getAllJackets() throws Exception {
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
        String actualList=mockMvc.perform(get("/api/products/all"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        String expected= objectMapper.writeValueAsString(jackets);
        assertThat(expected).isEqualTo(actualList);
    }

    @Test
    public void addShoe_addsShoe() throws Exception {
        ShoeEntity shoe=new ShoeEntity(20,ShoeType.boot,"Leather","Nike","Black", 200L);
        String shoeString = objectMapper.writeValueAsString(shoe);
        mockMvc.perform(post("/api/products/shoe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(shoeString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.size").value("20"))
                .andExpect(jsonPath("$.type").value("boot"))
                .andExpect(jsonPath("$.material").value("Leather"))
                .andExpect(jsonPath("$.brand").value("Nike"))
                .andExpect(jsonPath("$.color").value("Black"))
                .andExpect(jsonPath("$.price").value("200"));
    }

    @Test
    public void getProductsByColor() throws Exception {
        JacketEntity jacket1= jacketRepository.save(new JacketEntity(Season.FALL,"L","Blue","Slim",true,35L));
        jacketRepository.save(new JacketEntity(Season.WINTER,"M","Black","Skinny",false,50L));
        JacketEntity jacket3= jacketRepository.save(new JacketEntity(Season.SPRING,"S","Blue","Slim",true,30L));

        shoeRepository.save(new ShoeEntity(11, ShoeType.boot, "leather", "Nike",
                "Periwinkle", 100L));
        ShoeEntity shoeEntity2 = shoeRepository.save(new ShoeEntity(11, ShoeType.boot, "leather", "Nike",
                "Blue", 100L));

        shirtRepository.save((new ShirtEntity(ShirtType.dress, 5, 10, "L",
                "Orange", true, 9900L)));
        ShirtEntity shirtEntity2 = shirtRepository.save((new ShirtEntity(ShirtType.dress, 5, 10, "L",
                "Blue", true, 9900L)));
        shirtRepository.save((new ShirtEntity(ShirtType.dress, 5, 10, "L",
                "Orange", true, 9900L)));

        Products expected = Products.builder()
                .jackets(List.of(jacket1, jacket3))
                .shirts(List.of(shirtEntity2))
                .shoes(List.of(shoeEntity2))
                .build();

        String expectedString = objectMapper.writeValueAsString(expected);

        mockMvc.perform(get("/api/products?color=Blue"))
                .andExpect(content().string(expectedString))
                .andExpect(status().isOk());
    }

}