package com.capus.securedapi.controllers;

import com.capus.securedapi.entity.Information;
import com.capus.securedapi.repository.InformationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@SpringBootTest
@AutoConfigureMockMvc
public class InformationControllerTest {

    protected Long sharedId;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InformationRepository informationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        informationRepository.deleteAll();
        informationRepository.saveAll(List.of(
                new Information(null, "Info 1", false),
                new Information(null, "Info 2", true)
        ));
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnAllInformation() throws Exception {
        mockMvc.perform(get("/api/informations/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @WithMockUser(roles = "EDITOR")
    void shouldCreateInformation() throws Exception {
        Information info = new Information(null, "New Info", true);

        mockMvc.perform(post("/api/informations/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(info)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Information created"));
    }

    @Test
    @WithMockUser(roles = "VIEWER")
    void shouldNotCreateInformationBecauseUnauthorizedRole() throws Exception {
        Information info = new Information(null, "New Info", true);

        mockMvc.perform(post("/api/informations/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(info)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "EDITOR")
    void shouldUpdateInformation() throws Exception {
        Information savedInfo = informationRepository.save(new Information(null, "Old Title", true));
        Information updatedInfo = new Information(null, "Updated Title", false);

        mockMvc.perform(put("/api/informations/update/" + savedInfo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedInfo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Information updated"))
                .andExpect(jsonPath("$.data.important").value(false))
                .andExpect(jsonPath("$.data.message").value("Updated Title"));
    }

    @Test
    @WithMockUser(roles = "EDITOR")
    void shouldDeleteInformation() throws Exception {
        Information savedInfo = informationRepository.save(new Information(null, "RIP", true));
        List<Information> infos = informationRepository.findAll();
        mockMvc.perform(delete("/api/informations/"+savedInfo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Information deleted"));
    }


}