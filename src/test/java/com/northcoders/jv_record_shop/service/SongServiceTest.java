package com.northcoders.jv_record_shop.service;

import com.northcoders.jv_record_shop.dto.request.CreateSongRequestDTO;
import com.northcoders.jv_record_shop.model.Album;
import com.northcoders.jv_record_shop.model.Genre;
import com.northcoders.jv_record_shop.model.Song;
import com.northcoders.jv_record_shop.repository.SongRepository;
import com.northcoders.jv_record_shop.service.impl.SongServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Duration;
import java.util.*;

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

    @Test
    @DisplayName("get song by titles")
    void testGetSongByTitles() {
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

        when(mockSongRepository.findAllByTitleIn(Set.of("Fearless", "Fifteen"))).thenReturn(songs);

        List<Song> actualResult = songServiceImpl.getSongsByTitle(Set.of("Fearless", "Fifteen"));

        assertThat(actualResult).hasSize(2);
        assertThat(actualResult).isEqualTo(songs);

    }

    @Test
    @DisplayName("add song with valid data")
    void testAddSongWithValidData() {
        Song song = Song.builder()
                .title("Fearless")
                .writer("Taylor Swift")
                .songLength(Duration.ofSeconds(241))
                .build();

        CreateSongRequestDTO requestDTO = CreateSongRequestDTO.builder()
                .title("Fearless")
                .writer("Taylor Swift")
                .songLength(Duration.ofSeconds(241))
                .build();

        when(mockSongRepository.save(Mockito.any(Song.class))).thenReturn(song);

        Song result = songServiceImpl.addSong(requestDTO);

        assertThat(result).isEqualTo(song);

    }

}
