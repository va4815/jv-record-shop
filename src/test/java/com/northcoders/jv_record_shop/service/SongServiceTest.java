package com.northcoders.jv_record_shop.service;

import com.northcoders.jv_record_shop.model.Song;
import com.northcoders.jv_record_shop.repository.SongRepository;
import com.northcoders.jv_record_shop.service.impl.SongServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
public class SongServiceTest {

    @Mock
    private SongRepository mockSongRepository;

    @InjectMocks
    private SongServiceImpl songServiceImpl;

    @Test
    @DisplayName("get all songs")
    void testGetAllSongs() {
        List<Song> songs = Arrays.asList(
                Song.builder()
                        .title("Fearless")
                        .writer("Taylor Swift")
                        .songLength(Duration.ofSeconds(241))
                        .build()
        );

        when(mockSongRepository.findAll()).thenReturn(songs);

        List<Song> actualResult = songServiceImpl.getAllSongs();

        assertThat(actualResult).hasSize(1);
        assertThat(actualResult).isEqualTo(songs);

    }

    @Test
    @DisplayName("get song by id")
    void testGetSongById() {
        Song song = Song.builder()
                .title("Fearless")
                .writer("Taylor Swift")
                .songLength(Duration.ofSeconds(241))
                .build();

        when(mockSongRepository.findById(1L)).thenReturn(Optional.ofNullable(song));

        Song result = songServiceImpl.getSongById(1L);

        assertThat(result).isEqualTo(song);

    }


}
