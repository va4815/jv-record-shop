package com.northcoders.jv_record_shop.service;

import com.northcoders.jv_record_shop.model.Artists;
import com.northcoders.jv_record_shop.model.Gender;
import com.northcoders.jv_record_shop.repository.ArtistsRepository;
import com.northcoders.jv_record_shop.service.impl.ArtistsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
public class ArtistsServiceTest {

    @Mock
    private ArtistsRepository mockArtistsRepository;

    @InjectMocks
    private ArtistsServiceImpl artistsServiceImpl;

    @Test
    @DisplayName("get all artists")
    void testGetAllArtists() {
        List<Artists> artists = Arrays.asList(
                Artists.builder()
                        .name("Taylor Swift")
                        .gender(Gender.F)
                        .build()
        );

        when(mockArtistsRepository.findAll()).thenReturn(artists);

        List<Artists> actualResult = artistsServiceImpl.getAllArtists();

        assertThat(actualResult).hasSize(1);
        assertThat(actualResult).isEqualTo(artists);

    }

    @Test
    @DisplayName("get artist by id")
    void testGetArtistById() {
        Artists artist = Artists.builder()
                .name("Taylor Swift")
                .gender(Gender.F)
                .build();

        when(mockArtistsRepository.findById(1L)).thenReturn(Optional.ofNullable(artist));

        Artists result = artistsServiceImpl.getArtistById(1L);

        assertThat(result).isEqualTo(artist);

    }

}
