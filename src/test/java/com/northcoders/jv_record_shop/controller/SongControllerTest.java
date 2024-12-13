package com.northcoders.jv_record_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.northcoders.jv_record_shop.dto.request.UpdateSongRequestDTO;
import com.northcoders.jv_record_shop.model.Song;
import com.northcoders.jv_record_shop.service.impl.SongServiceImpl;
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

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class SongControllerTest {

    @Mock
    private SongServiceImpl mockSongServiceImpl;

    @InjectMocks
    private SongController songController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(songController).build();
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("GET /song")
    void getAllSongs() throws Exception {
        List<Song> songs = Arrays.asList(
                Song.builder()
                        .title("Fearless")
                        .writer("Taylor Swift")
                        .songLength(Duration.ofSeconds(241))
                        .build(),
                Song.builder()
                        .title("Fifteen")
                        .writer("Taylor Swift")
                        .songLength(Duration.ofSeconds(294))
                        .build()
        );

        when(this.mockSongServiceImpl.getAllSongs()).thenReturn(songs);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/song"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Fearless"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Fifteen"))
        ;


    }

    @Test
    @DisplayName("GET /song/{id}")
    void getSongById() throws Exception {
        Long songId = 1L;

        Song song = Song.builder()
                .id(songId)
                .title("Fearless")
                .writer("Taylor Swift")
                .songLength(Duration.ofSeconds(241))
                .build();

        when(this.mockSongServiceImpl.getSongById(songId)).thenReturn(song);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/song/{id}", songId)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(songId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Fearless"))
        ;

    }

    @Test
    @DisplayName("PUT /song")
    void updateSong() throws Exception {
        mapper.registerModule(new JavaTimeModule());

        Song song = Song.builder()
                .title("Fearless")
                .writer("Taylor Swift")
                .songLength(Duration.ofSeconds(241))
                .build();

        when(this.mockSongServiceImpl.updateSong(Mockito.any(UpdateSongRequestDTO.class))).thenReturn(song);

        UpdateSongRequestDTO requestDTO = UpdateSongRequestDTO.builder()
                .id(1L)
                .title("Fearless")
                .writer("Taylor Swift")
                .songLength(Duration.ofSeconds(241))
                .build();

        String requestBody = mapper.writeValueAsString(requestDTO);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.put("/api/v1/song").content(requestBody)
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Fearless"))
        ;

    }

    @Test
    @DisplayName("DELETE /song")
    void deleteSongById() throws Exception {
        boolean result = true;

        when(this.mockSongServiceImpl.deleteSongById(1L)).thenReturn(result);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.delete("/api/v1/song/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
        ;
    }

}
