package com.galvanize.clothingstore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.clothingstore.model.JacketEntity;
import com.galvanize.clothingstore.model.Season;
import com.galvanize.clothingstore.repository.JacketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")
class JacketControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JacketRepository jacketRepository;

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
    public void addJacket_addsJacket() throws Exception {
      objectMapper=new ObjectMapper();
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

}