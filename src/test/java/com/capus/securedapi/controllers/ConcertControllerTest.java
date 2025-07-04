package com.capus.securedapi.controllers;

import com.capus.securedapi.entity.Concert;
import com.capus.securedapi.repository.ConcertRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ConcertControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ConcertRepository concertRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        concertRepository.deleteAll();
        concertRepository.saveAll(List.of(
                new Concert(null,"first","","","","","","","",""),
                new Concert(null,"second","","","","","","","","")
        ));
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnAllConcerts() throws Exception {
        mockMvc.perform(get("/api/concerts/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @WithMockUser(roles = "EDITOR")
    void shouldCreateConcert() throws Exception {
        Concert concert = new Concert();

        mockMvc.perform(post("/api/concerts/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(concert)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Concert created"));
    }

    @Test
    @WithMockUser(roles = "VIEWER")
    void shouldNotCreateConcertBecauseUnauthorizedRole() throws Exception {
        Concert concert = new Concert();

        mockMvc.perform(post("/api/concerts/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(concert)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "EDITOR")
    void shouldUpdateConcert() throws Exception {
        Concert savedConcert = concertRepository.save(new Concert(null,"old","","","","","","","",""));
        Concert updatedConcert = concertRepository.save(new Concert(null,"new","","","","","","","",""));

        mockMvc.perform(put("/api/concerts/update/" + savedConcert.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedConcert)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Concert updated"))
                .andExpect(jsonPath("$.data.nom").value("new"));
    }

    @Test
    @WithMockUser(roles = "EDITOR")
    void shouldDeleteInformation() throws Exception {
        Concert savedConcert = concertRepository.save(new Concert(null,"RIP","","","","","","","",""));

        mockMvc.perform(delete("/api/concerts/"+savedConcert.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Concert deleted"));
    }


}