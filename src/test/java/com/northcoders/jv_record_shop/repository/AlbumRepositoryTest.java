package com.northcoders.jv_record_shop.repository;

import com.northcoders.jv_record_shop.model.Album;
import com.northcoders.jv_record_shop.model.Genre;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

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

    @Test
    public void testGetAlbumById() {
        Album album = Album.builder()
                .name("Speak Now")
                .releasedDate("25-10-2010")
                .genre(Genre.POP)
                .build();

        albumRepository.save(album);

        Optional<Album> result = albumRepository.findById(1L);

        Assertions.assertThat(result)
                .isPresent()
                .get()
                .isEqualTo(album);
    }

    @Test
    public void testGetAlbumById_invalidId() {
        Assertions.assertThat(albumRepository.findById(12345L))
                .isNotPresent()
                .isEmpty();
    }

}
