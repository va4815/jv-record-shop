package com.northcoders.jv_record_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.jv_record_shop.model.Album;
import com.northcoders.jv_record_shop.model.Artists;
import com.northcoders.jv_record_shop.model.Gender;
import com.northcoders.jv_record_shop.model.Genre;
import com.northcoders.jv_record_shop.service.impl.AlbumServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class AlbumControllerTest {

    @Mock
    private AlbumServiceImpl mockAlbumServiceImpl;

    @InjectMocks
    private AlbumController albumController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(albumController).build();
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("GET /albums")
    void getAllAlbums() throws Exception {

        Artists taylorSwift = Artists.builder()
                .name("Taylor Swift")
                .gender(Gender.F)
                .build();

        Album album1 = Album.builder()
                .name("Speak Now")
                .releasedDate("25-10-2010")
                .genre(Genre.POP)
                .artists(Set.of(taylorSwift))
                .build();

        Album album2 = Album.builder()
                .name("Fearless")
                .releasedDate("11-11-2008")
                .genre(Genre.POP)
                .artists(Set.of(taylorSwift))
                .build();

        List<Album> albumList = new ArrayList<>();
        albumList.add(album1);
        albumList.add(album2);

        when(this.mockAlbumServiceImpl.getAllAlbums()).thenReturn(albumList);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/albums"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Speak Now"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Fearless"))
        ;

    }

}
