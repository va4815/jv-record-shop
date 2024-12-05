package com.northcoders.jv_record_shop.repository;

import com.northcoders.jv_record_shop.model.Album;
import com.northcoders.jv_record_shop.model.Genre;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
public class AlbumRepositoryTest {

    @Autowired
    private AlbumRepository albumRepository;

    @Test
    public void testFindAllAlbums() {
        List<Album> albums = Arrays.asList(
                Album.builder()
                        .name("Speak Now")
                        .releasedDate("25-10-2010")
                        .genre(Genre.POP)
                        .build(),
                Album.builder()
                        .name("Fearless")
                        .releasedDate("11-11-2008")
                        .genre(Genre.POP)
                        .build()
        );

        albumRepository.saveAll(albums);

        Iterable<Album> results = albumRepository.findAll();

        Assertions.assertThat(results)
                .isNotNull()
                .hasSameSizeAs(albums);

    }

}
