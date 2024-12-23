package com.northcoders.jv_record_shop.repository;

import com.northcoders.jv_record_shop.model.Song;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
public class SongRepositoryTest {

    @Autowired
    private SongRepository songRepository;

    @Test
    public void testFindAllSongs() {
        List<Song> songs = Arrays.asList(
                Song.builder()
                        .title("Fearless")
                        .writer("Taylor Swift")
                        .songLength(Duration.ofSeconds(241))
                        .build()
        );

        songRepository.saveAll(songs);

        Iterable<Song> results = songRepository.findAll();

        Assertions.assertThat(results)
                .isNotNull()
                .hasSameSizeAs(songs);

    }

    @Test
    public void testGetSongById() {
        Song song = Song.builder()
                .title("Fearless")
                .writer("Taylor Swift")
                .songLength(Duration.ofSeconds(241))
                .build();

        songRepository.save(song);

        Optional<Song> result = songRepository.findById(1L);

        Assertions.assertThat(result)
                .isPresent()
                .get()
                .isEqualTo(song);

    }

    @Test
    public void testGetSongByTitles() {
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

        songRepository.saveAll(songs);

        List<Song> results = songRepository.findAllByTitleIn(Set.of("Fearless", "Fifteen"));

        Assertions.assertThat(results)
                .isNotNull()
                .hasSameSizeAs(songs);

    }

    @Test
    public void testGetSongById_invalidId() {
        Assertions.assertThat(songRepository.findById(12345L))
                .isNotPresent()
                .isEmpty();
    }

    @Test
    public void testSaveSongWithValidData() {
        Song song = Song.builder()
                .title("Fearless")
                .writer("Taylor Swift")
                .songLength(Duration.ofSeconds(241))
                .build();

        Song result = songRepository.save(song);

        Assertions.assertThat(result)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(song);

    }

}
