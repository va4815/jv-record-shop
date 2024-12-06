package com.northcoders.jv_record_shop.repository;

import com.northcoders.jv_record_shop.model.Artists;
import com.northcoders.jv_record_shop.model.Gender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ArtistsRepositoryTest {

    @Autowired
    private ArtistsRepository artistsRepository;

    @Test
    public void testFindAllArtists() {
        List<Artists> artists = Arrays.asList(
                Artists.builder()
                        .name("Taylor Swift")
                        .gender(Gender.F)
                        .build()
        );

        artistsRepository.saveAll(artists);

        Iterable<Artists> results = artistsRepository.findAll();

        Assertions.assertThat(results)
                .isNotNull()
                .hasSameSizeAs(artists);
    }

    @Test
    public void testGetArtistById() {
        Artists artist = Artists.builder()
                .name("Taylor Swift")
                .gender(Gender.F)
                .build();

        artistsRepository.save(artist);

        Optional<Artists> result = artistsRepository.findById(1L);

        Assertions.assertThat(result)
                .isPresent()
                .get()
                .isEqualTo(artist);
    }

    @Test
    public void testGetArtistById_invalidId() {
        Assertions.assertThat(artistsRepository.findById(12345L))
                .isNotPresent()
                .isEmpty();
    }

    @Test
    public void testSaveArtistWithValidData() {
        Artists artist = Artists.builder()
                .name("Taylor Swift")
                .gender(Gender.F)
                .build();

        Artists result = artistsRepository.save(artist);

        Assertions.assertThat(result)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(artist);
    }

}
