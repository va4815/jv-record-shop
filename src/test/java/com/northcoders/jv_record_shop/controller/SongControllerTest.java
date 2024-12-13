package com.northcoders.jv_record_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.jv_record_shop.service.impl.SongServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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


}
