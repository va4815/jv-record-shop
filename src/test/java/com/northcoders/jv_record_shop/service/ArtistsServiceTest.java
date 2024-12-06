package com.northcoders.jv_record_shop.service;

import com.northcoders.jv_record_shop.dto.request.CreateArtistsRequestDTO;
import com.northcoders.jv_record_shop.model.Artists;
import com.northcoders.jv_record_shop.model.Gender;
import com.northcoders.jv_record_shop.model.Song;
import com.northcoders.jv_record_shop.repository.ArtistsRepository;
import com.northcoders.jv_record_shop.service.impl.ArtistsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Test
    @DisplayName("get artist by ids")
    void testGetArtistByIds() {
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

        when(mockArtistsRepository.findAllArtistsByIdIn(Set.of(1L, 2L))).thenReturn(artists);

        List<Artists> actualResult = artistsServiceImpl.getArtistByIds(Set.of(1L, 2L));

        assertThat(actualResult).hasSize(2);
        assertThat(actualResult).isEqualTo(artists);

    }

    @Test
    @DisplayName("add artist with valid data")
    void testAddArtistWithValidData() {
        Artists artist = Artists.builder()
                .name("Taylor Swift")
                .gender(Gender.F)
                .build();

        CreateArtistsRequestDTO requestDTO = CreateArtistsRequestDTO.builder()
                .name("Taylor Swift")
                .gender(Gender.F)
                .build();

        when(mockArtistsRepository.save(Mockito.any(Artists.class))).thenReturn(artist);

        Artists result = artistsServiceImpl.createArtist(requestDTO);

        assertThat(result).isEqualTo(artist);

    }

}
