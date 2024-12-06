package com.northcoders.jv_record_shop.service;

import com.northcoders.jv_record_shop.dto.request.CreateAlbumRequestDTO;
import com.northcoders.jv_record_shop.dto.request.CreateSongRequestDTO;
import com.northcoders.jv_record_shop.dto.request.UpdateAlbumRequestDTO;
import com.northcoders.jv_record_shop.model.*;
import com.northcoders.jv_record_shop.repository.AlbumRepository;
import com.northcoders.jv_record_shop.service.impl.AlbumServiceImpl;
import com.northcoders.jv_record_shop.service.impl.ArtistsServiceImpl;
import com.northcoders.jv_record_shop.service.impl.SongServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Duration;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
public class AlbumServiceTest {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumServiceImpl albumServiceImpl;

    @Mock
    private ArtistsServiceImpl artistsServiceImpl;

    @Mock
    private SongServiceImpl songServiceImpl;

    @Test
    @DisplayName("get all albums")
    void testGetAllAlbums() {

        Artists taylorSwift = Artists.builder()
                .name("Taylor Swift")
                .gender(Gender.F)
                .build();

        Album album1 = Album.builder()
                .name("Speak Now")
                .releasedDate("25-10-2010")
                .genre(Genre.POP)
                .artists(List.of(taylorSwift))
                .build();

        Album album2 = Album.builder()
                .name("Fearless")
                .releasedDate("11-11-2008")
                .genre(Genre.POP)
                .artists(List.of(taylorSwift))
                .build();

        List<Album> albumList = new ArrayList<>();
        albumList.add(album1);
        albumList.add(album2);

        when(mockAlbumRepository.findAll()).thenReturn(albumList);

        List<Album> actualResult = albumServiceImpl.getAllAlbums();

        assertThat(actualResult).hasSize(2);
        assertThat(actualResult).isEqualTo(albumList);

    }

    @Test
    @DisplayName("get album by id")
    void testGetAlbumById() {
        Artists taylorSwift = Artists.builder()
                .name("Taylor Swift")
                .gender(Gender.F)
                .build();

        Album speakNow = Album.builder()
                .id(1L)
                .name("Speak Now")
                .releasedDate("25-10-2010")
                .genre(Genre.POP)
                .artists(List.of(taylorSwift))
                .build();


        when(mockAlbumRepository.findById(1L)).thenReturn(Optional.ofNullable(speakNow));

        Album result = albumServiceImpl.getAlbumById(1L);

        assertThat(result).isEqualTo(speakNow);

    }

    @Test
    @DisplayName("add album with valid data")
    void testAddAlbumWithValidData() {
        List<Song> songs = List.of(
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

        Artists taylorSwift = Artists.builder()
                .id(1L)
                .name("Taylor Swift")
                .gender(Gender.F)
                .build();

        Album album = Album.builder()
                .id(1L)
                .name("Speak Now")
                .releasedDate("25-10-2010")
                .genre(Genre.POP)
                .artists(List.of(taylorSwift))
                .songs(songs)
                .build();


        Set<CreateSongRequestDTO> createSongDTOs = Set.of(
                CreateSongRequestDTO.builder()
                        .title("Fearless")
                        .writer("Taylor Swift")
                        .songLength(Duration.ofSeconds(241))
                        .build(),
                CreateSongRequestDTO.builder()
                        .title("Fifteen")
                        .writer("Taylor Swift")
                        .songLength(Duration.ofSeconds(241))
                        .build()
        );

        CreateAlbumRequestDTO requestDTO = CreateAlbumRequestDTO.builder()
                .name("Speak Now")
                .releasedDate("25-10-2010")
                .genre(Genre.POP)
                .songs(createSongDTOs)
                .build();

        when(mockAlbumRepository.save(Mockito.any(Album.class))).thenReturn(album);

        Album result = albumServiceImpl.createAlbum(requestDTO);

        assertThat(result).isEqualTo(album);

    }

    @Test
    @DisplayName("update album with valid data")
    void testUpdateAlbumWithValidData() {
        Album album = Album.builder()
                .name("Speak Now")
                .releasedDate("25-10-2010")
                .genre(Genre.POP)
                .build();

        UpdateAlbumRequestDTO requestDTO = UpdateAlbumRequestDTO.builder()
                .id(1L)
                .name("Speak Now")
                .releasedDate("25-10-2010")
                .genre(Genre.POP)
                .build();

        when(mockAlbumRepository.save(Mockito.any(Album.class))).thenReturn(album);
        when(mockAlbumRepository.findById(1L)).thenReturn(Optional.ofNullable(album));
        album.setId(1L);

        Album result = albumServiceImpl.updateAlbum(requestDTO);

        assertThat(result).isEqualTo(album);

    }

    @Test
    @DisplayName("delete album by id")
    void testDeleteAlbumById() {
        Album speakNow = Album.builder()
                .id(1L)
                .name("Speak Now")
                .releasedDate("25-10-2010")
                .genre(Genre.POP)
                .build();

        when(mockAlbumRepository.findById(1L)).thenReturn(Optional.ofNullable(speakNow));

        boolean result = albumServiceImpl.deleteAlbumById(1L);

        assertThat(result).isEqualTo(true);
    }

}
