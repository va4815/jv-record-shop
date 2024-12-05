package com.northcoders.jv_record_shop.repository;

import com.northcoders.jv_record_shop.model.Song;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

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

}
