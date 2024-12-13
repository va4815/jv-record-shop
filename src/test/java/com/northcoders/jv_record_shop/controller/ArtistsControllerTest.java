package com.northcoders.jv_record_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.jv_record_shop.dto.request.CreateArtistsRequestDTO;
import com.northcoders.jv_record_shop.dto.request.UpdateArtistsRequestDTO;
import com.northcoders.jv_record_shop.model.Artists;
import com.northcoders.jv_record_shop.model.Gender;
import com.northcoders.jv_record_shop.service.impl.ArtistsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class ArtistsControllerTest {

    @Mock
    private ArtistsServiceImpl mockArtistsServiceImpl;

    @InjectMocks
    private ArtistsController artistsController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(artistsController).build();
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("GET /artists")
    void getAllArtists() throws Exception {
        List<Artists> artists = Arrays.asList(
                Artists.builder()
                        .name("Taylor Swift")
                        .gender(Gender.F)
                        .build(),
                Artists.builder()
                        .name("Yoshiki")
                        .gender(Gender.M)
                        .build()
        );

        when(this.mockArtistsServiceImpl.getAllArtists()).thenReturn(artists);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/artists"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Taylor Swift"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Yoshiki"))
        ;

    }

    @Test
    @DisplayName("GET /artists/{id}")
    void getArtistsById() throws Exception {
        Long artistId = 1L;

        Artists artists = Artists.builder()
                .id(artistId)
                .name("Taylor Swift")
                .gender(Gender.F)
                .build();

        when(this.mockArtistsServiceImpl.getArtistById(artistId)).thenReturn(artists);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/artists/{id}", artistId)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(artistId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Taylor Swift"))
        ;

    }

    @Test
    @DisplayName("POST /artists")
    void createArtists() throws Exception {
        Artists artists = Artists.builder()
                .name("Taylor Swift")
                .gender(Gender.F)
                .build();

        when(this.mockArtistsServiceImpl.createArtist(Mockito.any(CreateArtistsRequestDTO.class))).thenReturn(artists);

        CreateArtistsRequestDTO requestDTO = CreateArtistsRequestDTO.builder()
                .name("Taylor Swift")
                .gender(Gender.F)
                .build();

        String requestBody = mapper.writeValueAsString(requestDTO);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/v1/artists").content(requestBody)
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Taylor Swift"))
        ;

    }

    @Test
    @DisplayName("PUT /artists")
    void updateArtists() throws Exception {
        Artists artists = Artists.builder()
                .name("Taylor Swift")
                .gender(Gender.F)
                .build();

        when(this.mockArtistsServiceImpl.updateArtist(Mockito.any(UpdateArtistsRequestDTO.class))).thenReturn(artists);

        UpdateArtistsRequestDTO requestDTO = UpdateArtistsRequestDTO.builder()
                .name("Taylor Swift")
                .gender(Gender.F)
                .build();

        String requestBody = mapper.writeValueAsString(requestDTO);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.put("/api/v1/artists").content(requestBody)
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Taylor Swift"))
        ;

    }


}
