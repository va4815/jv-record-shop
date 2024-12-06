package com.northcoders.jv_record_shop.repository;

import com.northcoders.jv_record_shop.model.Artists;
import com.northcoders.jv_record_shop.model.Gender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

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

}
