package com.capus.securedapi.controllers;


import com.capus.securedapi.entity.Pointeur;
import com.capus.securedapi.repository.PointeurRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PointeurControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PointeurRepository pointeurRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        pointeurRepository.deleteAll();
        pointeurRepository.saveAll(List.of(
                new Pointeur(null,"first",0f,0f,"","",""),
                new Pointeur(null,"second",0f,0f,"","","")
        ));
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnAllPointeurs() throws Exception {
        mockMvc.perform(get("/api/pointeurs/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @WithMockUser(roles = "EDITOR")
    void shouldCreatePointeur() throws Exception {
        Pointeur concert = new Pointeur(null,"new",0f,0f,"","","");

        mockMvc.perform(post("/api/pointeurs/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(concert)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Pointeur created"));
    }

    @Test
    @WithMockUser(roles = "VIEWER")
    void shouldNotCreateConcertBecauseInvalidEntry() throws Exception {


        mockMvc.perform(post("/api/pointeurs/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString("{\"id\":null,\"nom\":\"new\",\"lon\":\"new\"}")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "EDITOR")
    void shouldUpdateConcert() throws Exception {
        Pointeur savedConcert = pointeurRepository.save(new Pointeur(null,"new",0f,0f,"","",""));
        Pointeur updatedConcert = pointeurRepository.save(new Pointeur(null,"new",0f,0f,"","",""));

        mockMvc.perform(put("/api/pointeurs/update/" + savedConcert.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedConcert)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Pointeur updated"))
                .andExpect(jsonPath("$.data.nom").value("new"));
    }

    @Test
    @WithMockUser(roles = "EDITOR")
    void shouldDeleteInformation() throws Exception {
        Pointeur savedCPointeur = pointeurRepository.save(new Pointeur(null,"new",0f,0f,"","",""));

        mockMvc.perform(delete("/api/pointeurs/"+savedCPointeur.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Pointeur deleted"));
    }


}